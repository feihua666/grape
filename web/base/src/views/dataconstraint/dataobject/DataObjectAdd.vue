<template>
    <Form
          :form-items="formItems"
    ></Form>
</template>

<script>
    import Form from 'common-util/src/components/page/Form.vue'
    export default {
        components:{
            Form
        },
        data(){
            return {
                formItems:[
                    {
                        field: {
                            name: 'code'
                        },
                        element:{
                            label: '编码',
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'name'
                        },
                        element:{
                            label: '名称',
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'dataCustomUrl'
                        },
                        element:{
                            label: '自定义url',
                            placeholder:'自定义url应该是一个加载数据的url'
                        }
                    },
                    {
                        field: {
                            name: 'isDataLazy'
                        },
                        element:{
                            type: 'switch',
                            label: '数据是否懒加载',
                            required:(form)=>{return !!form.dataCustomUrl}
                        }
                    },
                    {
                        field: {
                            name: 'interviewModeDictId'
                        },
                        element:{
                            type: 'selectDict',
                            selectDict:{
                                groupCode:'data_object_custom_interview_mode'
                            },
                            label: '交互方式',
                            required:(form)=>{return !!form.dataCustomUrl}
                        }
                    },
                    {
                        field: {
                            name: 'tableModeColumns'
                        },
                        element:{
                            type: 'textarea',
                            label: '表格列定义',
                            placeholder:'默认id和名称'
                        }
                    },
                    {
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'post',
                                    url: '/dataobject',
                                    label: '保存'
                                },
                                {
                                    action: 'reset',
                                    label: '重置',
                                    buttonType: 'default'
                                }
                            ]
                        }
                    }
                ]
            }
        },
        methods:{
        }
    }
</script>

<style scoped>

</style>