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
                            name: 'year'
                        },
                        element:{
                            label: '年',
                            placeholder:'2019',
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'month'
                        },
                        element:{
                            label: '月',
                            placeholder:'2',
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'day'
                        },
                        element:{
                            label: '日',
                            placeholder:'23',
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
                                groupCode: 'work_calendar_type'
                            },
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'content'
                        },
                        element:{
                            label: '内容'
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
                                    url: '/workcalendar',
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