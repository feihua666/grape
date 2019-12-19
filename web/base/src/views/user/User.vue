<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100" header-style="height:auto;" main-style="padding:0;">
        <Form slot="header" inline
              submit-bus-key="userManageList"
              :form-items="formItems"
              label-width=""
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
                    },
                    {
                        action: 'enableOrDisable',
                        enableOrDisable:{
                            prop:'isLock',
                            reasonProp:'lockReason'
                        },
                        url:'/user/:id',
                        position:'more',
                        disabledOnMissingSelect:true,
                        label:(row) => {
                            if(row){
                                return row.isLock ? '启用': '锁定'
                            }else {
                                return '启用/锁定'
                            }
                        }
                    },
                    {
                        action: 'nav',
                        position:'more',
                        disabledOnMissingSelect:true,
                        url:'/user/assignRole/:id',
                        label: '分配角色'
                    },
                    {
                        action: 'nav',
                        position:'more',
                        disabledOnMissingSelect:true,
                        url:'/user/assignFunc/:id',
                        label: '分配功能'
                    },
                    {
                        action: 'nav',
                        position:'more',
                        disabledOnMissingSelect:true,
                        routeQuery:['nickname'],
                        url:'/user/userPost/userPostAdd/:id',
                        label: '新增岗位'
                    },
                    {
                        action: 'nav',
                        position:'more',
                        disabledOnMissingSelect:true,
                        url:'/user/userPost/:id',
                        routeQuery: 'nickname',
                        label: '岗位信息'
                    },
                    {
                        action: 'nav',
                        position:'more',
                        disabledOnMissingSelect:true,
                        url:'/user/userIdentifier/:id',
                        label: '帐号信息'
                    },
                    {
                        action: 'nav',
                        position:'more',
                        disabledOnMissingSelect:true,
                        url:'/user/userIdentifier/userIdentifierAdd/:id',
                        label: '新增帐号'
                    },
                    {
                        action: this.resetPwd,
                        position:'more',
                        disabledOnMissingSelect:true,
                        label: '重置用户密码'
                    },
                    {
                        action: 'nav',
                        url:'/user/UserAssignDataScope/:id',
                        position:'more',
                        disabledOnMissingSelect:true,
                        label: '数据范围约束'
                    }
                ]
            }
        },
        methods:{
            resetPwd(row){
                this.$prompt('请输入密码', '提示', {
                    inputErrorMessage: '密码不能为空',
                    inputValidator:(value)=>{return !!value}
                }).then(({ value }) => {
                    let data = {
                        userId: row.id,
                        version: row.version,
                        pwd: value
                    }
                    this.axios.put('/userpwd/'+ row.id,data)
                        .then(res => {
                            this.$message.success('密码重置成功')
                        })
                        .catch(error => {
                            if(error.response){
                                if(error.response.status == 404){
                                    this.$message.error('数据不存在，请刷新数据再试')
                                }else {
                                    this.$message.error(error.response.data.msg)
                                }
                            }else {
                                this.$message.error('删除失败，请刷新数据再试')
                            }
                        })
                })
            }
        }
    }
</script>

<style scoped>

</style>