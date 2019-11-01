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
                            name: 'typeDictId'
                        },
                        element:{
                            label: '类型',
                            type: 'selectDict',
                            selectDict:{
                                groupCode:'job_type'
                            },
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'isPublic'
                        },
                        element:{
                            type: 'switch',
                            label: '是否公共'
                        }
                    },
                    {
                        field: {
                            name: 'deptId'
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl: '/dept/tree'
                            },
                            required: (form) => {
                                return !form.isPublic
                            },
                            label: '部门'
                        }
                    },
                    {
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'post',
                                    url: '/job',
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