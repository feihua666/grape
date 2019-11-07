<template>
    <el-form :inline="inline"
             size="mini"
             :model="form"
             ref="dynamicValidateForm"
             label-width="100px"
             v-loading="loading"
             @submit.native.prevent
    >
        <template v-for="(item, index) in formItems">
            <el-form-item
                    v-if="item.element"
                    :label="item.element.label"
                    :key="index"
                    :prop="getFieldName(item)"
                    :rules="getRules(item)"
            >
                <el-input v-if="item.element.type == 'text'"
                          v-model="form[getFieldName(item)]"
                          :placeholder="item.element.placeholder"
                          :disabled="item.element.disabled"
                          :readonly="item.element.readonly"
                          clearable>
                </el-input>
                <el-input v-else-if="item.element.type == 'textarea'"
                          type="textarea"
                          :placeholder="item.element.placeholder"
                          :disabled="item.element.disabled"
                          :readonly="item.element.readonly"
                          v-model="form[getFieldName(item)]" clearable>
                </el-input>
                <el-input-number v-else-if="item.element.type == 'inputNumber'"
                                 v-model="form[getFieldName(item)]" controls-position="right"
                                 :min="getInputNumber(item).min"
                                 :max="getInputNumber(item).max"
                                 :disabled="item.element.disabled"
                                 :readonly="item.element.readonly"
                ></el-input-number>
                <SelectDict v-else-if="item.element.type == 'selectDict'"
                            :group-code="item.element.selectDict.groupCode"
                            :placeholder="item.element.placeholder"
                            v-on:dictCode="(dictCode)=> {form[item.field.name+'__code'] = dictCode}"
                            :disabled="item.element.disabled"
                            :readonly="item.element.readonly"
                            v-model="form[getFieldName(item)]">
                </SelectDict>
                <InputSelectTree v-else-if="item.element.type == 'inputSelectTree'"
                                 v-model="form[getFieldName(item)]"
                                 :label-text="form[getFieldName(item) + '__label']"
                                 :placeholder="item.element.placeholder"
                                 :disabled="item.element.disabled"
                                 :readonly="item.element.readonly"
                                 :tree-data-url="item.element.inputSelectTree.dataUrl">

                </InputSelectTree>
                <InputSelectIcon v-else-if="item.element.type == 'inputSelectIcon'"
                                 v-model="form[getFieldName(item)]"
                                 :disabled="item.element.disabled"
                                 :readonly="item.element.readonly"
                                 :placeholder="item.element.placeholder">

                </InputSelectIcon>
                <el-switch v-else-if="item.element.type == 'switch'"
                           v-model="form[getFieldName(item)]"
                           :disabled="item.element.disabled"
                           :active-text="item.element.switch ? item.element.switch.activeText: '是'"
                           :inactive-text="item.element.switch ? item.element.switch.inactiveText: '否'"
                >

                </el-switch>
                <Select v-else-if="item.element.type == 'select'"
                        v-model="form[getFieldName(item)]"
                        :disabled="item.element.disabled"
                        :url="item.element.select.url"
                        :props="item.element.select.props"
                ></Select>
                <template v-else-if="item.element.type == 'txt'">
                    {{form[getFieldName(item)]}}
                </template>
                <template v-else-if="item.element.type == 'button'">
                    <el-button v-if="isObject(item.element.button)"
                               :type="item.element.button.buttonType || aiButtonStyle(item.element.button.label).type || 'primary'"
                               :icon="item.element.button.buttonIcon || aiButtonStyle(item.element.button.label).icon "
                               @click="buttonClick(item.element.button)"
                               :native-type="item.element.button.action == 'submit' ? 'submit': null"
                               :loading="buttonLoading[item.element.button.action] || item.element.button.loading"
                    >{{item.element.button.label}}</el-button>
                    <template v-else-if="isArray(item.element.button)">
                        <el-button v-for="(buttonItem,index) in item.element.button" :key="index"
                                   :type="buttonItem.buttonType || aiButtonStyle(buttonItem.label).type || 'primary'"
                                   :icon="buttonItem.buttonIcon || aiButtonStyle(buttonItem.label).icon "
                                   @click="buttonClick(buttonItem)"
                                   :native-type="buttonItem.action == 'submit' ? 'submit': null"
                                   :loading="buttonLoading[buttonItem.action] || buttonItem.loading"
                        >{{buttonItem.label}}</el-button>
                    </template>
                </template>
                <!-- 默认是文本类型 -->
                <el-input v-else
                          v-model="form[getFieldName(item)]"
                          :placeholder="item.element.placeholder"
                          :disabled="item.element.disabled"
                          :readonly="item.element.readonly"
                          clearable>
                </el-input>
            </el-form-item>
        </template>

    </el-form>
</template>

<script>
    /* formItems 元素说明
        {
        // 表单对象
        field: {
            name: 'test1',// 表单的name
            value: '' // 初始值，也可以通过formItemValue属性对象指定
            {value}__label: ''// 为inputSelectTree 等value对应的翻译，注意两个下划线，以防与后台属性冲突加了两个下划线,{value}是对name值的引用
        },
        // 页面元素
        element:{
            type: 'text', // 表单项类型可选择值 text=文本输入，selectDict=字典下拉选择,button=按钮,inputNumber=数字,inputSelectTree=输入选择树
            // 字典下拉选择时存在该对象
            selectDict:{
                groupCode: 'gender',
                showEmpty: true //是否显示请选择 选填
            },
            // 数字有效
            inputNumber:{
                min: 1,
                max: 100
            },
            inputSelectTree:{
                dataUrl: '',
            },
            // 按钮时存在该对象,可以是一个以该对象为结构的数组
            button:{
                action: 'submit',// submit=表单提交，reset=重置表单，nav=导航到其它页面
                // submit时可用
                requestMethod:'get',
                // submit或nav时可用
                url:'',
                // 数组有效
                label: '测试'
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
    import InputSelectTree from '../../components/common/InputSelectTree.vue'
    import InputSelectIcon from '../../components/common/InputSelectIcon.vue'
    import Select from '../../components/common/Select.vue'

    import {aiButtonStyle} from "../../tools/StyleTools.js"
    export default {
        name: "Form",
        components:{
            SelectDict,
            InputSelectIcon,
            Select,
            InputSelectTree
        },
        props:{
            inline:{
                type: Boolean,
                default: false
            },
            // 可以通过总线触发表单提交操作，目前主要解决表格翻页
            submitBusKey:{
                type: String,
                default: null
            },
            submitSuccessText:{
                type:String,
                default: null
            },
            // 表单项定义信息
            formItems:{
                type: Array,
                default: function () {
                    return []
                }
            },
            // 表单项的值，供修改用
            //
            formItemValue:{
                type: Object,
                default: null
            },
            // 表单加载状态
            loading:{
                type: Boolean,
                default:false
            }
        },
        computed:{
        },
        data(){
            let form = {}
            this.formItems.forEach(item =>{
                if(item.field){
                    form[item.field.name] = this.getFormItemValueByName(item.field.name) || item.field.value || null
                    // 扩展字段支持，label支持，主要是为了选择树回显翻译字面使用 ，当前也可以使用其它方式

                    for (let fieldKey in item.field) {
                        if(fieldKey.indexOf('__') >=0 ){
                            form[fieldKey] = this.getFormItemValueByName(fieldKey) || item.field[fieldKey] || null
                        }
                    }
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
            getInputNumber(item){
                if(item.element.inputNumber){
                    return {
                        min: item.element.inputNumber.min || 1,
                        max: item.element.inputNumber.max || 100
                    }
                }
                return {
                    min: 1,
                    max: 100
                }
            },
            getFormItemValueByName(name){
                if(this.formItemValue){
                    return this.formItemValue[name]
                }
                return null
            },
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
                if (hasPropSimple(item.element, 'required')) {
                    if(item.element.required === true){
                        rules.push({required: true, message: item.element.label + '不能为空', trigger: 'blur'})
                        rules.push({required: true, message: item.element.label + '不能为空', trigger: 'change'})
                    }else if(typeof item.element.required == 'function'){
                        rules.push({required: item.element.required(this.form), message: item.element.label + '不能为空', trigger: 'blur'})
                        rules.push({required: item.element.required(this.form), message: item.element.label + '不能为空', trigger: 'change'})
                    }
                }
                if (hasPropSimple(item.element, 'validator')) {
                    if(typeof item.element.validator == 'function'){
                        let validator = (rule, value, callback)=>{
                            item.element.validator(rule, value, callback,this.form)
                        }
                        rules.push({validator:validator, trigger: 'blur'})
                        rules.push({validator:validator, trigger: 'change'})
                    }
                }
                return rules
            },
            // 事件总线相关初始化,用于和table通信
            busInit(){
                if(this.submitBusKey){
                    this.$bus.$on(this.submitBusKey,this.handleBusSubmit)
                }
            },
            handleBusSubmit(obj){
                if(obj){
                    if(obj.dataType == 'page'){
                        let page = obj.page
                        let button = this.getButton('submit')
                        this.submitForm(button,page)
                    }else if(obj.dataType == 'refresh'){
                        let button = this.getButton('submit')
                        this.submitForm(button)
                    }
                }

            },
            getButton(action){
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
                        this.$router.push(button.url)
                        break;
                }
            },
            submitForm(button,pageQuery) {
                if(!button) {
                    return
                }
                this.$refs['dynamicValidateForm'].validate((valid) => {
                    if (valid) {
                        let tempForm = cloneSimple(this.form,true)
                            if(pageQuery){
                                tempForm = copyPropSimple(tempForm,pageQuery)
                            }
                            this.$set(this.buttonLoading,button.action,true)
                            if(this.submitBusKey){
                                this.$bus.$emit(this.submitBusKey ,{dataType:'loading',loading: true})
                            }
                            this.$emit('submitLoading',true)
                            let method =  button.requestMethod || 'get'
                            let options = {
                                method:method,
                                url: button.url
                            }
                            if('get' == method){
                                options.params = tempForm
                            }else {
                                options.data = tempForm
                            }
                            this.axios(options).then(res =>{
                                if(this.submitBusKey){
                                    this.$bus.$emit(this.submitBusKey ,{dataType:'data',data: res.data.data})
                                }
                                this.$emit('submitData',[])
                                if(this.submitSuccessText){
                                    this.$message.info(this.submitSuccessText)
                                }else if(method == 'post'){
                                    this.$message.info('添加成功')
                                }else if(method == 'delete'){
                                    this.$message.info('删除成功')
                                }else if(method == 'put'){
                                    this.$message.info('更新成功')
                                }
                            }).catch(error => {
                                if(this.submitBusKey){
                                    this.$bus.$emit(this.submitBusKey ,{dataType:'data',data: []})
                                }
                                this.$emit('submitData',[])
                                if(error.response){
                                    if(error.response.status != 404){
                                        this.$message.error(error.response.data.msg)
                                    }
                                }else {
                                    this.$message.error('请求失败,未知错误')
                                }
                            }).finally(()=>{
                                this.$set(this.buttonLoading,button.action,false)
                                if(this.submitBusKey){
                                    this.$bus.$emit(this.submitBusKey ,{dataType:'loading',loading: false})
                                }
                                this.$emit('submitLoading',false)
                            })
                    } else {
                        return false;
                    }
                });
            },
            emit(key,value){
                // 触发事件总线，目前是配合table完成请求与加载
                this.$bus.$emit(key ,value)
                this.$emit(key,value)
            },
            resetForm() {
                this.$refs['dynamicValidateForm'].resetFields();
            },
            aiButtonStyle(label){
                return aiButtonStyle(label)
            }
        },
        watch:{
            formItemValue (val){
                for(let key in val){
                    if(this.form[key] !== undefined){
                        this.form[key] = val[key]
                    }
                }
            }
        }
    }
</script>

<style scoped>

</style>