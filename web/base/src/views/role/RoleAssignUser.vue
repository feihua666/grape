<template>
    <TableForRelBind
            :columns="columns"
            :form-items="formItems"
            form-submit-bus-key="roleAssignUserList"
            :checked-data-url="'/userrolerel/role/' + $route.params.id"
            submit-url="/userrolerel/role/assign/user"
            :main-value="$route.params.id"
            main-prop="roleId"
            checked-prop="checkedUserIds"
            unchecked-prop="uncheckedUserIds"
            :clear-all-url="'/userrolerel/role/' + $route.params.id"
    >

    </TableForRelBind>
</template>

<script>
    import TableForRelBind from 'common-util/src/components/page/TableForRelBind.vue'

    export default {
        components:{
            TableForRelBind
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
                        type:'selection'
                    },
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
                ]
            }
        },
        methods:{
        }
    }
</script>

<style scoped>

</style>