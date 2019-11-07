<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100">
        <Form slot="header" inline
              submit-bus-key="logManageList"
              :form-items="formItems"
        ></Form>
        <Table slot="main"
               ref="table"
               form-submit-bus-key="logManageList"
               :columns="columns"
               :operations="tableOperations"
               unique-label="name"
               v-on:toolbarSearchClick="formShow = !formShow"
        ></Table>
    </container>
</template>

<script>
    import Container from 'common-util/src/components/page/Container.vue'
    import Form from 'common-util/src/components/page/Form.vue'
    import Table from 'common-util/src/components/page/Table.vue'

    export default {
        components:{
            Form,
            Table,
            Container
        },
        data(){
            return {
                formShow: true,
                formItems:[
                    {
                        field: {
                            name: 'requestId'
                        },
                        element:{
                            label: '请求id',
                        }
                    },
                    {
                        field: {
                            name: 'userNickname'
                        },
                        element:{
                            label: '用户名',
                        }
                    },
                    {
                        field: {
                            name: 'typeDictId'
                        },
                        element:{
                            label: '日志类型',
                            type: 'selectDict',
                            selectDict:{
                                groupCode:'log_type'
                            }
                        }
                    },
                    {
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'get',
                                    url: '/paramconfig/listPage',
                                    label: '查询'
                                },
                                {
                                    action: 'reset',
                                    label: '重置'
                                }
                            ]
                        }
                    }
                ],
                columns:[
                    {
                        prop: 'requestId',
                        label:'请求id'
                    },
                    {
                        prop: 'parentRequestId',
                        label:'上次请求id'
                    },
                    {
                        prop: 'userNickname',
                        label:'用户'
                    },{
                        prop: 'content',
                        label:'内容'
                    },
                    {
                        prop: 'requestHttpUrl',
                        label:'请求url'
                    },
                    {
                        prop: 'responseHttpStatus',
                        label:'响应状态码'
                    },
                    {
                        prop: 'duration',
                        label:'时长ms'
                    }
                ],
                tableOperations:[
                    {
                        action: 'nav',
                        disabledOnMissingSelect:true,
                        url:'/log/logDetail/:id',
                        label: '详情'
                    },
                    {
                        action: 'delete',
                        url:'/log/:id',
                        disabledOnMissingSelect:true,
                        label:'删除'
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