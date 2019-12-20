<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100" header-style="height:auto;" main-style="padding:0;">
        <Form slot="header" inline
              submit-bus-key="orgManageList"
              :form-items="formItems"
              label-width=""
        ></Form>
        <Table slot="main"
               ref="table"
               form-submit-bus-key="orgManageList"
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
        name: "Org",
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
                                dataUrl:'/org/tree'
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
                                    url: '/org/listPage',
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
                        prop: 'name',
                        label:'名称'
                    },
                    {
                        prop: 'parentName',
                        label:'父级'
                    },
                    {
                        prop: 'relationCompName',
                        label:'关联公司'
                    },
                    {
                        prop: 'relationDeptName',
                        label:'关联部门'
                    },
                    {
                        prop: 'typeDictName',
                        label:'类型'
                    }
                ],
                tableOperations:[
                    {
                        action: 'nav',
                        url:'/org/orgAdd',
                        label: '添加'
                    },
                    {
                        action: 'nav',
                        disabledOnMissingSelect:true,
                        url:'/org/orgEdit/:id',
                        label: '编辑'
                    },
                    {
                        action: 'delete',
                        url:'/org/:id',
                        disabledOnMissingSelect:true,
                        label:'删除'
                    },
                    {
                        action: 'nav',
                        url:'/org/orgAdd',
                        routeQuery:['id','name'],// 回显父级
                        position:'more',
                        disabledOnMissingSelect:true,
                        label:'添加下级节点'
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