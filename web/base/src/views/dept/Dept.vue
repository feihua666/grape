<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100">
        <Form slot="header" inline
              submit-bus-key="deptManageList"
              :form-items="formItems"
        ></Form>
        <Table slot="main"
               ref="table"
               form-submit-bus-key="deptManageList"
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
        name: "Dept",
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
                        field: {
                            name: 'parentId'
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/dept/tree'
                            },
                            label: '父级'
                        }
                    },
                    {
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'get',
                                    url: '/dept/listPage',
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
                        prop: 'parentName',
                        label:'父级'
                    },
                    {
                        prop: 'compName',
                        label:'公司'
                    },
                    {
                        prop: 'typeDictName',
                        label:'类型'
                    },
                    {
                        prop: 'masterUserName',
                        label:'负责人'
                    },
                    {
                        prop: 'isVirtual',
                        label:'是否虚拟'
                    }
                ],
                tableOperations:[
                    {
                        action: 'nav',
                        url:'/dept/deptAdd',
                        label: '添加'
                    },
                    {
                        action: 'nav',
                        disabledOnMissingSelect:true,
                        url:'/dept/deptEdit/:id',
                        label: '编辑'
                    },
                    {
                        action: 'delete',
                        url:'/dept/:id',
                        disabledOnMissingSelect:true,
                        label:'删除'
                    },
                    {
                        action: 'nav',
                        url:'/dept/deptAdd',
                        routeQuery:['id','name'],
                        position:'more',
                        disabledOnMissingSelect:true,
                        label:'添加子节点'
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