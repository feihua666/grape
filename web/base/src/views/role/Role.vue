<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100">
        <Form slot="header" inline
              submit-bus-key="roleManageList"
              :form-items="formItems"
        ></Form>
        <Table slot="main"
               ref="table"
               form-submit-bus-key="roleManageList"
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
                        field: {
                            name: 'parentId'
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/role/tree'
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
                                    url: '/role/listPage',
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
                    },{
                        prop: 'parentName',
                        label:'父级'
                    },
                    {
                        prop: 'isDisabled',
                        label:'是否禁用'
                    }
                ],
                tableOperations:[
                    {
                        action: 'nav',
                        url:'/role/roleAdd',
                        label: '添加'
                    },
                    {
                        action: 'nav',
                        disabledOnMissingSelect:true,
                        url:'/role/roleEdit/:id',
                        label: '编辑'
                    },
                    {
                        action: 'delete',
                        url:'/role/:id',
                        disabledOnMissingSelect:true,
                        label:'删除'
                    },
                    {
                        action: this.enableOrDisable,
                        position:'more',
                        disabledOnMissingSelect:true,
                        label:(row) => {
                            if(row){
                                return row.isDisabled ? '启用': '禁用'
                            }else {
                                return '启用/禁用'
                            }
                        }
                    },
                    {
                        action: 'nav',
                        url:'/role/roleAdd',
                        routeQuery:['id','name'],
                        position:'more',
                        disabledOnMissingSelect:true,
                        label:'添加子节点'
                    }
                ]
            }
        },
        methods:{
            enableOrDisable(row){
                this.$prompt('请输入'+ row.name + (row.isDisabled ? '启用': '禁用') +'原因', '提示', {
                    inputErrorMessage: '原因不能为空',
                    inputValidator:(value)=>{return !!value}
                }).then(({ value }) => {
                    let data = {
                        disabled: !row.isDisabled,
                        version: row.version,
                        disabledReason: value
                    }
                    this.axios.patch('/role/'+ row.id,data)
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