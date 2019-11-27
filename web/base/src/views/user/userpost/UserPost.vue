<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100">
        <Form slot="header" inline
              submit-bus-key="userPostManageList"
              :form-items="formItems"
        ></Form>
        <Table slot="main"
               ref="table"
               form-submit-bus-key="userPostManageList"
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
                            name: 'compId'
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/comp/tree'
                            },
                            label: '公司'
                        }
                    },
                    {
                        field: {
                            name: 'userId',
                            userId__label:this.$route.query.nickname,
                            value: this.$route.params.userId
                        },
                        element:{
                            type: 'selectRemote',
                            selectRemote:{
                                url:'/user/list',
                                props:{value:'id',label:'nickname'},
                                queryProp:'nickname'
                            },
                            label: '用户'
                        }
                    },
                    {
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'get',
                                    url: '/userpost/listPage',
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
                        prop: 'userNickname',
                        label:'昵称'
                    },
                    {
                        prop: 'compName',
                        label:'公司'
                    },
                    {
                        prop: 'deptName',
                        label:'部门'
                    },
                    {
                        prop: 'postName',
                        label:'岗位'
                    },
                    {
                        prop: 'isMain',
                        label:'是否主岗'
                    },
                    {
                        prop: 'jobName',
                        label:'职务'
                    },
                    {
                        prop: 'jobLevelName',
                        label:'职级'
                    },
                    {
                        prop: 'isEffect',
                        label:'是否有效'
                    },
                    {
                        prop: 'ineffectReason',
                        label:'无效原因'
                    },
                    {
                        prop: 'effcetAtFormat',
                        label:'生效时间'
                    }
                ],
                tableOperations:[
                    {
                        action: 'nav',
                        url:'/user/userPost/userPostAdd/' + this.$route.params.userId,
                        label: '新增岗位'
                    },
                    {
                        action: 'nav',
                        disabledOnMissingSelect:true,
                        url:'/user/userEdit/:id',
                        label: '编辑岗位'
                    },
                    {
                        action: 'delete',
                        url:'/user/:id',
                        disabledOnMissingSelect:true,
                        label:'删除岗位'
                    },
                    {
                        action: this.enableOrDisable,
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
                        url:'/user/userPost/UserPostAssignRole/:id',
                        label: '分配角色'
                    }
                ]
            }
        },
        methods:{
            enableOrDisable(row){
                this.$prompt('请输入'+ (row.isLock ? '启用': '禁用') +'原因', '提示', {
                    inputErrorMessage: '原因不能为空',
                    inputValidator:(value)=>{return !!value}
                }).then(({ value }) => {
                    let data = {
                        isLock: !row.isDisabled,
                        version: row.version,
                        lockReason: value
                    }
                    this.axios.patch('/user/'+ row.id,data)
                        .then(res => {
                            this.$message.success('操作成功')
                            // 重新加载数据
                            this.$refs.table.toolbarRefreshClick()
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