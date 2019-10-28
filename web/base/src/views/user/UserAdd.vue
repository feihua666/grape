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
                            name: 'nickname'
                        },
                        element:{
                            label: '昵称',
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'genderDictId'
                        },
                        element:{
                            label: '性别',
                            type: 'selectDict',
                            selectDict:{
                                groupCode:'gender'
                            },
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'isVirtual'
                        },
                        element:{
                            type: 'switch',
                            label: '虚拟用户'
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
                            required: true,
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
                                    url: '/user',
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