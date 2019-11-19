<template>
    <Container type="simple" class="g-width100 g-height100">
        <div slot="header" class="g-width100 g-height100">
            <!-- 表格工具 -->
            <el-button-group>
                <!-- 用来显示用来查询条件 -->
                <el-button size="mini" icon="el-icon-search" title="显示/隐藏搜索" @click="$emit('toolbarSearchClick')"></el-button>
                <!-- 刷新当前数据 -->
                <el-button  size="mini" icon="el-icon-refresh" title="刷新当前数据" :loading="localLoading" @click="toolbarRefreshClick"></el-button>
                <template>
                <!-- 表格的列，可选择性显示隐藏列 -->
                <el-button size="mini" icon="el-icon-s-grid"  title="显示/隐藏列" v-popover:selectPopover></el-button>
                <el-popover
                        ref="selectPopover"
                        placement="bottom"
                        trigger="click">
                    <el-scrollbar wrapStyle="max-height:500px;">
                        <el-checkbox-group v-model="toolbarMore.checkedItems">
                            <template v-for="(column,index) in columns">
                                <div style="margin-top: 15px;" :key="index">
                                <el-checkbox  :label="column.prop" class="g-block"
                                             :disabled="toolbarMore.checkedItems.length == 1 && toolbarMore.checkedItems.indexOf(column.prop) >=0"
                                >{{column.label}}</el-checkbox>
                                </div>
                            </template>

                        </el-checkbox-group>
                    </el-scrollbar>
                </el-popover>
                </template>
            </el-button-group>
            <!-- 自定义按钮工具 -->
            <el-button-group v-if="operations">
                <template v-if="groupOperations.length > 0">
                    <el-button v-for="(button,index) in groupOperations"
                               :disabled="isButtonDisabled(button)"
                               @click="handleOperations(button)"
                               :type="button.buttonType || aiButtonStyle(button).type || 'primary'"
                               :icon="button.buttonIcon || aiButtonStyle(button).icon"
                               size="mini"
                               :loading="buttonLoading[button.code]"
                               :key="index">{{operationButtonLabel(button)}}</el-button>
                </template>

                <el-dropdown size="mini" v-if="moreOperations.length > 0" @command="handleOperations">
                    <el-button :type="operationMoreButtonType"
                               :icon="operationMoreButtonIcon"
                               size="mini"
                    >
                        {{operationMoreButtonText}}<i class="el-icon-arrow-down el-icon--right"></i>
                    </el-button>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item
                                v-for="(button,index) in moreOperations"
                                :disabled="isButtonDisabled(button)"
                                :command="button"
                                :loading="localButtonLoading[button.code]"
                                :key="index"
                        >{{operationButtonLabel(button)}}</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-button-group>
        </div>
        <div slot="main" class="table-wrapper">
            <el-table
                    border
                      size="mini"
                      :data="tableData"
                      class="g-width100"
                      v-loading="localLoading"
                    :empty-text="localEmptyText"
                    highlight-current-row
                    @current-change="handleCurrentChange"
                    @selection-change="handleSelectionChange"
                    ref="table"
            >
                <template v-for="(column,index) in columns">
                    <!-- 加一个默认全部显示的 toolbarMore.checkedItems.length == 0 条件为避免闪屏-->
                    <el-table-column
                            v-if="toolbarMore.checkedItems.length == 0 || toolbarMore.checkedItems.indexOf(column.prop) >= 0"
                            :key="index"
                            :prop="column.prop"
                            :label="column.label"
                            :width="column.width"
                            :formatter="formatter"
                            :show-overflow-tooltip="!!column.tooltip"
                            :type="column.type"
                    >
                    </el-table-column>
                </template>

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
    import {isObject} from '../../tools/ObjectTools.js'
    import {getPathVar,putPathVar} from '../../tools/StringTools.js'

    import {aiButtonStyle} from "../../tools/StyleTools.js"
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
            // 主键属性
            keyProp:{
                type: String,
                default: 'id'
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
            // 事件总线的key，配置form表单提交绑定事件
            formSubmitBusKey: {
                type: String,
                default: null
            },
            emptyText:{
                type: String,
                default: '暂无数据'
            },
            // 可操作项定义
            /*
            {
                action:'' // nav delete
                url:''// 对应action的url
                label: '' //按钮的标签
                disabled: false
                disabledOnMissingSelect: true //如果不选中禁用
            }
            */
            operations:{
                type: Array,
                default: null
            },
            // 自定义按钮的loading状态，key为按钮的code,值为Boolean类型
            buttonLoading:{
                type: Object,
                default:function () {
                    return {}
                }
            },
            operationMoreButtonType:{
                type: String,
                default: 'primary'
            },
            operationMoreButtonIcon:{
                type: String,
                default: ''
            },
            operationMoreButtonText:{
                type: String,
                default: '更多'
            },
            // 唯一标识一条数据的一个文本或属性，主要用来做确定提示用
            //
            uniqueLabel:{
                type:[String,Function],
                default: null
            },
            // 多选的默认选中值
            checkedKeys:{
                type: Array,
                default: function () {
                    return []
                }
            },
            // 也可以指定一个默认选中的url，一般情况下两个只能选一种
            checkedKeysUrl:{
                type: String
            }
        },
        computed:{
            groupOperations(){
                let r = []
                if(this.operations){
                    for (let index in this.operations) {
                        let item = this.operations[index]
                        if(item.position == undefined || item.position == 'default'){
                            r.push(item)
                        }
                    }
                }
                return r
            },
            moreOperations(){
                let r = []
                if(this.operations){
                    for (let index in this.operations) {
                        let item = this.operations[index]
                        if(item.position == 'more'){
                            r.push(item)
                        }
                    }
                }
                return r
            }
        },
        mounted(){
            this.toolbarMoreCheckAll()
            if(this.localData){
                this.init(this.localData)
            }
            // 加载选中的值

            this.getCheckedData()

            this.busInit()
        },
        data() {
            return {
                localEmptyText: this.emptyText,
                localData: this.data,
                // 真正的数据表格数据
                tableData:[],
                localLoading: this.loading,
                // 分页
                pagination:{
                    showPage: false,//是否显示分页组件
                    current:1,
                    size:10,
                    total: 0
                },
                localButtonLoading: this.buttonLoading,
                localQueryParams:this.queryParams,
                // 当前选中的行
                currentRow: null,
                // 多选选中的行
                multipleSelection: null,
                localCheckedKeys: this.checkedKeys,
                toolbarMore:{
                    isIndeterminate: false,
                    checkAll: false,
                    checkedItems:[]
                }
            }
        },
        methods:{
            init(data,queryParams){
                if(data == null){
                    this.tableData = []
                    this.pagination.showPage = false
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
                    this.pagination.showPage = false
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
                if(this.formSubmitBusKey){
                    this.$bus.$on(this.formSubmitBusKey,this.handleFormSubmitBus)
                }
            },
            // 多选设置选中
            setChecked(checkedKeys){
                this.$nextTick(() => {
                    this.tableData.forEach(row => {
                        if(checkedKeys.indexOf(row[this.keyProp]) >=0){
                            this.$refs.table.toggleRowSelection(row,true);
                        }
                    });
                })
            },
            // 处理表单请求数据
            handleFormSubmitBus(obj){
                if(obj){
                    if(obj.dataType == 'loading'){
                        this.localLoading = obj.loading
                    }else if(obj.dataType == 'data'){
                        this.init(obj.data)
                    }
                }
            },
            // 每面大小改变重新查询数据
            pageSizeChange (val) {
                this.pagination.size = val
                this.$emit('sizeChange', val)
                this.handlePageChange()
            },
            // 页码改变加载对应页码数据
            pageNoChange (val) {
                this.pagination.current = val
                this.$emit('currentChange', val)
                this.handlePageChange()
            },
            handlePageChange(){
                // 如果是url处理下一页逻辑
                if(typeof this.data == 'string'){
                    this.requestData(this.data,true,this.localQueryParams)
                }else {
                    if(this.formSubmitBusKey){
                        this.$bus.$emit(this.formSubmitBusKey,{dataType:'page',page:{
                                current: this.pagination.current,
                                size:this.pagination.size
                            }})
                    }
                }
            },
            refreshData(){
                // 如果是url处理下一页逻辑
                if(typeof this.data == 'string'){
                    this.requestData(this.data,false,this.localQueryParams)
                }else {
                    if(this.formSubmitBusKey){
                        this.$bus.$emit(this.formSubmitBusKey,{dataType:'refresh'})
                    }
                }
            },
            // 请求后台数据
            requestData(url,page = false,queryParams = {}){
                if(this.localLoading){
                    this.$message.info('数据加载中请耐心等待')
                    return
                }
                this.localLoading = true
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
                })
            },
            formatter(row, column, cellValue){
                if(typeof cellValue == 'boolean'){
                    return cellValue ? '是' : '否'
                }else if(cellValue == null){
                    return '-'
                }
                return cellValue
            },
            handleCurrentChange(val) {
                this.currentRow = val
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            toolbarRefreshClick(){
                // 刷新默认加载当前页数据，如果有分页的话，如果没有分页只是多个分页参数，也不影响
                this.handlePageChange()
            },
            operationButtonLabel(button){
                if(typeof button.label == 'function'){
                    return button.label(this.currentRow)
                }
                if(button.disabled_label !== undefined){
                    if(this.isButtonDisabled(button)){
                        return button.disabled_label
                    }
                }
                return button.label
            },
            isButtonDisabled(button){
                if(button.disabled == true){
                    return true
                }else if(button.disabledOnMissingSelect == true){
                    if(!this.currentRow){
                        return true
                    }
                }
                return false
            },
            handleOperations(button){
                let action = button.action
                if(typeof action == 'function'){
                    action(this.currentRow)
                    return
                }

                let url = button.url
                let prop = getPathVar(url)
                if(this.currentRow){
                    url = putPathVar(url,prop,this.currentRow[prop])
                }
                switch (action) {
                    case 'nav':{
                        if(button.routeQuery){

                            let query = {}
                            if (typeof button.routeQuery == 'string') {
                                if(this.currentRow){
                                    query[button.routeQuery] = this.currentRow[button.routeQuery]
                                }

                            }else if (typeof button.routeQuery == 'function') {
                                query = button.routeQuery(this.currentRow)
                            }else if (isArray(button.routeQuery)) {
                                if(this.currentRow){
                                    button.routeQuery.forEach(item=>{
                                        query[item] = this.currentRow[item]
                                    })
                                }
                            }else if (isObject(button.routeQuery)) {
                                query = button.routeQuery
                            }
                            this.$router.push({
                                path: url,
                                query:query
                            })
                        }else {
                            this.$router.push(url)
                        }
                        break
                    }
                    case 'delete':{
                        this.doDelete(url,button)
                        break
                    }
                    case 'exportTableExcel':{
                        break
                    }
                    case 'exportTableCsv':{
                        break
                    }
                }
            },
            getUniqueLabel(){
              let r = ''
              if(typeof this.uniqueLabel == 'string'){
                  r = this.uniqueLabel
                  if(this.currentRow){
                      r = this.currentRow[this.uniqueLabel]
                  }
              }  else if(typeof this.uniqueLabel == 'function'){
                  r = typeof this.uniqueLabel(this.currentRow)
              }
              return r
            },
            doDelete(url,button){
                this.$confirm('确定要删除'+ this.getUniqueLabel() +'吗, 是否继续?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.$set(this.localButtonLoading,button.code,true)
                    this.axios.delete(url)
                        .then(res => {
                            this.$message.success('删除成功')
                            // 重新加载数据
                            this.refreshData()
                        })
                        .catch(error => {
                            if(error.response){
                                if(error.response.status == 404){
                                    this.$message.error('数据不存在，请刷新数据再试')
                                }else {
                                    this.$message.error(error.response.data.msg)
                                }
                            }else {
                                this.$message.error('删除失败，请刷新数据再试')
                            }
                        }).finally(()=>{
                        this.$set(this.localButtonLoading,button.code,true)
                    })
                })
            },
            toolbarMoreCheckAll(){
                let r = []
                this.columns.forEach(item =>{
                    r.push(item.prop)
                })
                this.toolbarMore.checkedItems = r
            },
            aiButtonStyle(button){
                return aiButtonStyle(this.operationButtonLabel(button))
            },
            // 多选，获取选中的行
            getCheckedRows(){
                return this.multipleSelection
            },
            getUncheckedRows(){
                let checkedKeys = this.getCheckedKeys()
                return this.tableData.filter(item => checkedKeys.indexOf(item[this.keyProp]) < 0)
            },
            getCheckedKeys(){
                return this.multipleSelection.map(item => item[this.keyProp])
            },
            getUncheckedKeys(){
                let checkedKeys = this.getCheckedKeys()
               return this.tableData.filter(item => checkedKeys.indexOf(item[this.keyProp])< 0).map(item => item[this.keyProp])
            },
            clearSelection(){
                this.$refs.table.clearSelection()
            },
            // 加载多选选中的值
            getCheckedData(){
                if(!this.checkedKeysUrl) return
                this.axios.get(this.checkedKeysUrl)
                    .then((res) => {
                        this.localCheckedKeys = res.data.data
                    }).catch(error=>{
                    this.localCheckedKeys = []
                })
            },
        },
        watch:{
            loading(val){
                this.localLoading = val
            },
            data(val){
                this.localData = val
                this.init(this.localData)
            },
            tableData(val){
                this.setChecked(this.localCheckedKeys)
            },
            localCheckedKeys(val){
                this.setChecked(val)
            },
            checkedKeys(val){
                this.localCheckedKeys = val
            },
            // 这里没有处理每一个属性的loading状态，但一般都是一个按钮操作不太影响
            buttonLoading(val){
                this.localButtonLoading = val
            }
        }
    }
</script>

<style scoped>

</style>