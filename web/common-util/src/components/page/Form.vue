<template>
    <el-form :inline="inline" size="mini" :model="form" ref="dynamicValidateForm" label-width="100px">
        <el-form-item
                v-for="(item, index) in formItems"
                :label="item.element.label"
                :key="index"
                :prop="getFieldName(item)"
                :rules="getRules(item)"
        >
            <el-input v-if="item.element.type == 'text'"
                      v-model="form[getFieldName(item)]" clearable>
            </el-input>
            <SelectDict v-else-if="item.element.type == 'selectDict'"
                        :group-code="item.element.selectDict.groupCode"
                        v-model="form[getFieldName(item)]">
            </SelectDict>
            <template v-else-if="item.element.type == 'button'">
                <el-button v-if="isObject(item.element.button)"
                           :type="item.element.button.buttonType || 'primary'"
                           @click="buttonClick(item.element.button)"
                           :loading="buttonLoading[item.element.button.action] || item.element.button.loading"
                >{{item.element.button.label}}</el-button>
                <template v-else-if="isArray(item.element.button)">
                    <el-button v-for="(buttonItem,index) in item.element.button" :key="index"
                               :type="buttonItem.buttonType || 'primary'"
                               @click="buttonClick(buttonItem)"
                               :loading="buttonLoading[buttonItem.action] || buttonItem.loading"
                    >{{buttonItem.label}}</el-button>
                </template>
            </template>
            <!-- 默认是文本类型 -->
            <el-input v-else
                      v-model="form[getFieldName(item)]" clearable>
            </el-input>
        </el-form-item>
    </el-form>
</template>

<script>
    /* formItems 元素说明
        {
        // 表单对象
        field: {
            name: 'test1',
            value: ''
        },
        // 页面元素
        element:{
            type: 'text', // 表单项类型可选择值 text=文本输入，selectDict=字典下拉选择,button=按钮
            // 字典下拉选择时存在该对象
            selectDict:{
                groupCode: 'gender',
                showEmpty: true //是否显示请选择 选填
            },
            // 按钮时存在该对象,可以是一个以该对象为结构的数组
            button:{
                action: 'submit',// submit=表单提交，reset=重置表单，nav=导航到其它页面
                // submit时可用
                requestMethod:'get',
                // submit或nav时可用
                actionUrl:'',
                // 数组有效
                label: '测试',
                // 事件总线前缀，如果指定把按钮点击情况上报到这里，主要解决表单提交与table表格数据通信
                busKeyPrefix:''
            },
            label: '测试1',
        },
        // 验证规则
        rules:[
            {required: true, message: '测试1不能为空', trigger: 'blur'}
        ]
    }
    */
    import SelectDict from '../../components/common/SelectDict.vue'
    import {isArray,itemHasProp} from '../../tools/ArrayTools.js'
    import {isObject,cloneSimple,copyPropSimple,getValue,hasPropSimple} from '../../tools/ObjectTools.js'
    import {upperFirst} from '../../tools/StringTools.js'
    export default {
        name: "Form",
        components:{
            SelectDict
        },
        props:{
            inline:{
                type: Boolean,
                default: false
            },
            // 表单项定义信息
            formItems:{
                type: Array,
                default: function () {
                    return []
                }
            }
        },
        data(){
            let form = {}
            this.formItems.forEach(item =>{
                if(item.field){
                    form[item.field.name] = item.field.value || null
                }
            })
            return {
                form,
                // 按钮loading 状态 submit为formItems.element.button.action的值，标识是哪个按钮
                buttonLoading:{
                    //submit: false
                },
                // 分页对象,只针对submit
                pageQuery:null
            }
        },
        mounted(){
            this.busInit()
        },
        methods:{
            isArray(array){
                let b = isArray(array)
                return b;
            },
            isObject(obj){
                let b = isObject(obj)
                return b;
            },
            getFieldName(item){
                if(item.field){
                    return item.field.name
                }
                return null
            },
            getRules(item){
                let rules = item.rules
                if(!rules){
                    rules = []
                }
                if (hasPropSimple(item.element, 'required') && item.element.required == true) {
                    rules.push({required: true, message: item.element.label + '不能为空', trigger: 'blur'})
                }
                return rules
            },
            // 事件总线相关初始化
            busInit(){
                let self = this
                this.formItems.forEach(item => {
                    if(isObject(item.element.button)){
                        if(item.element.button.busKeyPrefix){
                            self.busOn(item.element.button.busKeyPrefix,'tableRequestLoading',self.handleBusLoading)
                            self.busOn(item.element.button.busKeyPrefix,'sizeChange',self.handleSizeChange)
                            self.busOn(item.element.button.busKeyPrefix,'currentChange',this.handleCurrentChange)
                        }
                    }else if(isArray(item.element.button)){
                        item.element.button.forEach(button => {
                            if(button.busKeyPrefix){
                                self.busOn(button.busKeyPrefix,'tableRequestLoading',self.handleBusLoading)
                                self.busOn(button.busKeyPrefix,'sizeChange',self.handleSizeChange)
                                self.busOn(button.busKeyPrefix,'currentChange',self.handleCurrentChange)
                            }
                        })
                    }
                })

            },
            // 事件绑定
            busOn(prefix,suffix,fn){
                this.$bus.$on(prefix + upperFirst(suffix),fn)
            },
            // 表格自己请求loading事件
            handleBusLoading (loading){
                for(let action in loading){
                    this.$set(this.buttonLoading,action,loading[action])
                }
            },
            // 表格每页大小变化
            handleSizeChange(size){
                if(!this.pageQuery){
                    this.pageQuery = {}
                }
                this.pageQuery['size'] = size.submit
                // 只有submit按钮才能提交请求
                let button = this.getButton('submit')
                if(button && button.actionUrl){
                    this.submitForm(button)
                }
            },
            // 表格页码变化
            handleCurrentChange(current){
                if(!this.pageQuery){
                    this.pageQuery = {}
                }
                this.pageQuery['current'] = current.submit
                // 只有submit按钮才能提交请求
                let button = this.getButton('submit')
                if(button && button.actionUrl){
                    this.submitForm(button)
                }
            },
            getButton(action){
                let self = this
                let buttonR = null
                this.formItems.forEach(item => {
                    let isBreak = false
                    if(isObject(item.element.button)){
                        if(action == item.element.button.action){
                            buttonR = item.element.button
                            isBreak = true
                        }
                    }else if(isArray(item.element.button)){
                        item.element.button.forEach(button => {
                            if(action == button.action){
                                buttonR = button
                                isBreak = true
                                return false
                            }
                        })
                    }
                    if(isBreak){
                        return false
                    }
                })
                return buttonR
            },
            buttonClick(button){
                switch (button.action) {
                    case 'submit':
                        this.submitForm(button)
                        break;
                    case 'reset':
                        this.resetForm()
                        break;
                    case 'nav':
                        this.$router.push(button.actionUrl)
                        break;
                }
            },
            submitForm(button) {
                this.$refs['dynamicValidateForm'].validate((valid) => {
                    if (valid) {
                        // 如果提供url自己提交然后上报数据
                        if(button.actionUrl){
                            let tempForm = cloneSimple(this.form,true)
                            if(this.pageQuery){
                                tempForm = copyPropSimple(tempForm,this.pageQuery)
                            }
                            this.$set(this.buttonLoading,button.action,true)
                            this.emit(button.busKeyPrefix,'buttonClickLoading',this.buttonLoading)
                            let method =  button.requestMethod || 'get'
                            let options = {
                                method:method,
                                url: button.actionUrl
                            }
                            if('get' == method){
                                options.params = tempForm
                            }else {
                                options.data = tempForm
                            }
                            tempForm.code = null
                            this.axios(options).then(res =>{
                                let data = {}
                                data[button.action] = res.data.data
                                this.emit(button.busKeyPrefix,'buttonClickData',data)
                            }).catch(error => {
                                let data = {}
                                data[button.action] = null
                                this.emit(button.busKeyPrefix,'buttonClickData',data)
                            }).finally(()=>{
                                this.$set(this.buttonLoading,button.action,false)
                                this.emit(button.busKeyPrefix,'buttonClickLoading',this.buttonLoading)
                            })
                        }else {
                            // 如果没有提供url，直接上报查询条件对象
                            let data = {}
                            data[[button.action]] = this.form
                            this.emit(button.busKeyPrefix,'buttonClickForm',data)
                        }
                    } else {
                        return false;
                    }
                });
            },
            emit(keyPrefix,keySuffix,value){
                // 触发事件总线，目前是配合table完成请求与加载
                if(keyPrefix && this.$bus){
                    this.$bus.$emit(keyPrefix + upperFirst(keySuffix) ,value)
                }
                this.$emit(keySuffix,value)
            },
            resetForm() {
                this.$refs['dynamicValidateForm'].resetFields();
            }
        },
        watch:{
        }
    }
</script>

<style scoped>

</style>