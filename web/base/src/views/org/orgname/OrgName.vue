<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100" header-style="height:auto;" main-style="padding:0;">
        <Form slot="header" inline
              submit-bus-key="orgNameManageList"
              :form-items="formItems"
              label-width=""
        ></Form>
        <Table slot="main"
               ref="table"
               form-submit-bus-key="orgNameManageList"
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
                            name: 'code'
                        },
                        element:{
                            label: '编码',
                        }
                    },
                    {
                        field: {
                            name: 'name'
                        },
                        element:{
                            label: '名称',
                        }
                    },
                    {
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'get',
                                    url: '/orgname/listPage',
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
                        prop: 'code',
                        label:'编码'
                    },
                    {
                        prop: 'name',
                        label:'名称'
                    },
                    {
                        prop: 'typeDictName',
                        label:'类型'
                    },
                    {
                        prop: 'deptName',
                        label:'部门'
                    },
                    {
                        prop: 'isPublic',
                        label:'是否公共'
                    }
                ],
                tableOperations:[
                    {
                        action: 'nav',
                        url:'/orgName/orgNameAdd',
                        label: '添加'
                    },
                    {
                        action: 'nav',
                        disabledOnMissingSelect:true,
                        url:'/orgName/orgNameEdit/:id',
                        label: '编辑'
                    },
                    {
                        action: 'delete',
                        url:'/orgname/:id',
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