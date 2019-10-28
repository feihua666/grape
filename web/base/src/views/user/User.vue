<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100">
        <Form slot="header" inline
              submit-bus-key="userManageList"
              :form-items="formItems"
        ></Form>
        <Table slot="main"
               ref="table"
               form-submit-bus-key="userManageList"
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
                            name: 'nickname'
                        },
                        element:{
                            label: '昵称',
                        }
                    },
                    {
                        field: {
                            name: 'genderDictId'
                        },
                        element:{
                            type: 'selectDict',
                            selectDict:{
                                groupCode: 'gender'
                            },
                            label: '性别'
                        }
                    },
                    {
                        field: {
                            name: 'deptId'
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/dept/tree'
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
                                    requestMethod:'get',
                                    url: '/user/listPage',
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
                        prop: 'nickname',
                        label:'昵称'
                    },
                    {
                        prop: 'genderDictName',
                        label:'性别'
                    },
                    {
                        prop: 'deptName',
                        label:'部门'
                    },
                    {
                        prop: 'compName',
                        label:'公司'
                    },
                    {
                        prop: 'isVirtual',
                        label:'虚拟用户'
                    },
                    {
                        prop: 'isLock',
                        label:'是否锁定'
                    }
                ],
                tableOperations:[
                    {
                        action: 'nav',
                        url:'/user/userAdd',
                        label: '添加'
                    },
                    {
                        action: 'nav',
                        disabledOnMissingSelect:true,
                        url:'/user/userEdit/:id',
                        label: '编辑'
                    },
                    {
                        action: 'delete',
                        url:'/user/:id',
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