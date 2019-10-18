<template>
    <Container type="simple" class="g-width100 g-height100">
        <template slot="header"></template>
        <div slot="main" class="table-wrapper">
            <el-table
                      size="mini"
                      :data="tableData"
                      class="g-width100"
                      v-loading="localLoading"
            >
                <el-table-column
                        v-for="(column,index) in columns"
                        :key="index"
                        :prop="column.prop"
                        :label="column.label"
                        :width="column.width"
                        :formatter="formatter"
                >
                </el-table-column>
            </el-table>
            <pagination
                        v-if="pagination.showPage"
                        :current="pagination.current"
                        :page-size="pagination.size"
                        :total="pagination.total"
                        v-on:sizeChange="pageSizeChange"
                        v-on:currentChange="pageNoChange"
            ></pagination>
        </div>
    </Container>
</template>

<script>

    import Container from './Container'
    import Pagination from './Pagination'
    import {isArray} from '../../tools/ArrayTools.js'
    import {isObject,getValue} from '../../tools/ObjectTools.js'
    import {upperFirst} from '../../tools/StringTools.js'
    export default {
        name: "Table",
        components:{
            Container,
            Pagination
        },
        props: {
            // 列定义
            /*
            columns 说明
            {
                prop:'',// 属性名
                label:'',// 表头名
                width:'',// 宽度
            }
             */
            columns: {
                type: Array,
                required: true
            },
            // 表格数据
            // 如果是一个字符串则表示是一个url
            // 如果是一个数据组，则表示是数据本身
            // 如果是一个对象，则表示是分页的数据，分页对象格式固定
            data:{

            },
            // data为url有效
            queryParams:{
                type:Object,
                default: function () {
                    return {}
                }
            },
            loading: {
                type: Boolean,
                default: false
            },
            // 事件总线的key前缀，配置form表单提交绑定事件
            busKeyPrefix: {
                type: String,
                default: null
            },
            // 配合 busKeyPrefix 绑定事件后，接收到的数据的属性名，如果不指定那数据就是本身
            busDataProp: {
                type: String,
                default: null
            }
        },
        mounted(){
            this.init(this.localData)
            this.busInit()
        },
        data() {
            return {
                localData: this.data,
                tableData:[],
                localLoading: this.loading,
                // 分页
                pagination:{
                    showPage: false,//是否显示分页组件
                    current:1,
                    size:10,
                    total: 0
                },
                localQueryParams:this.queryParams
            }
        },
        methods:{
            init(data,queryParams){
                if(data == null){
                    this.tableData = []
                }
                // 如果是字符串，直接请求
                else if(typeof data == 'string'){
                    if(queryParams){
                        this.localQueryParams = queryParams
                    }
                    this.requestData(data,false,queryParams)
                }
                // 如果是数组 直接渲染
                else if(isArray(data)){
                    this.tableData = data
                }
                // 如果是对象 当做分页对象处理
                else if (isObject(data)){
                    this.tableData = data.records
                    this.pagination.total = data.total
                    this.pagination.current = data.current
                    this.pagination.size = data.size
                    this.pagination.showPage = true
                }
            },
            // 事件总线相关
            busInit(){
                if(this.busKeyPrefix){
                    this.busOn(this.busKeyPrefix,'buttonClickLoading',this.handleBusLoading)
                    this.busOn(this.busKeyPrefix,'buttonClickData',this.handleBusData)
                    this.busOn(this.busKeyPrefix,'buttonClickForm',this.handleBusForm)
                }
            },
            // 事件绑定
            busOn(prefix,suffix,fn){
                this.$bus.$on(this.busKeyPrefix + upperFirst(suffix),fn)
            },
            // 处理加载动画
            handleBusLoading(loading){
                if(this.busDataProp){
                    this.localLoading = loading[this.busDataProp]
                }else {
                    this.localLoading = loading
                }
            },
            // 处理请求数据
            handleBusData(busData){
                if(this.busDataProp){
                    this.init(busData[this.busDataProp])
                }else {
                    this.init(busData)
                }
            },
            // 处理提交的表单
            handleBusForm(busDataQueryForm){
                if(typeof this.data == 'string'){
                    if(this.busDataProp){
                        this.init(this.data,busDataQueryForm[this.busDataProp])
                    }else {
                        this.init(this.data,busDataQueryForm)
                    }
                }else {
                   throw '只有表格data为字符串类型才可用'
                }

            },
            // 事件派发
            emit(keySuffix,value){
                let keyPrefix = this.busKeyPrefix
                // 触发事件总线，目前是配合table完成请求与加载
                if(keyPrefix && this.$bus){
                    let emitValue = value
                    if(this.busDataProp){
                        let temp = {}
                        temp[this.busDataProp] = value
                        emitValue = temp
                    }
                    this.$bus.$emit(keyPrefix + upperFirst(keySuffix) ,emitValue)
                }
                this.$emit(keySuffix,value)
            },
            // 每面大小改变重新查询数据
            pageSizeChange (val) {
                this.emit('sizeChange', val)
                // 如果是url处理下一页逻辑
                if(typeof this.data == 'string'){
                    this.pagination.size = val
                    this.requestData(this.data,true,this.localQueryParams)
                }
            },
            // 页码改变加载对应页码数据
            pageNoChange (val) {
                this.emit('currentChange', val)
                // 如果是url处理下一页逻辑
                if(typeof this.data == 'string'){
                    this.pagination.current = val
                    this.requestData(this.data,true,this.localQueryParams)
                }
            },
            // 请求后台数据
            requestData(url,page = false,queryParams = {}){
                if(this.localLoading){
                    this.$message.info('数据加载中请耐心等待')
                    return
                }
                this.localLoading = true
                this.emit('tableRequestLoading',this.localLoading)
                let pageParam = queryParams
                if(page){
                    pageParam.current = this.pagination.current
                    pageParam.size = this.pagination.size
                }
                this.axios.get(url,{params:pageParam}).then(res => {
                    this.localData = res.data.data
                    this.init(this.localData)
                }).catch(() =>{
                    this.localData = []
                    this.init(this.localData)
                }).finally(() => {
                    this.localLoading = false
                    this.emit('tableRequestLoading',this.localLoading)
                })
            },
            formatter(row, column, cellValue){
                if(typeof cellValue == 'boolean'){
                    return cellValue ? '是' : '否'
                }else if(cellValue == null){
                    return '-'
                }
                return cellValue
            }
        },
        watch:{
            loading(val){
                this.localLoading = val
            },
            data(val){
                this.localData = val
                this.init(this.localData)
            }
        }
    }
</script>

<style scoped>

</style>