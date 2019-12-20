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
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'post',
                                    url: '/orgname',
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