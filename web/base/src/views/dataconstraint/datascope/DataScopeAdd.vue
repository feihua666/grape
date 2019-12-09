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
                            name: 'dataObjectId',
                            value:this.$route.params.dataObjectId
                        },
                        element:{
                            label: '数据对象',
                            type: 'select',
                            disable:!!this.$route.params.dataObjectId,
                            select:{
                                url:'/dataobject/list'
                            },
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'isCustom'
                        },
                        element:{
                            type: 'switch',
                            label: '自定义',
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'constraintContent'
                        },
                        element:{
                            type: 'textarea',
                            label: '约束内容',
                            required:(form)=>{
                                return !form.isCustom
                            }
                        }
                    },
                    {
                        field: {
                            name: 'remark'
                        },
                        element:{
                            label: '备注'
                        }
                    },
                    {
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'post',
                                    url: '/datascope',
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