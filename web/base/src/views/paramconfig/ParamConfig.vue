<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100">
        <Form slot="header" inline
              submit-bus-key="paramconfigManageList"
              :form-items="formItems"
        ></Form>
        <Table slot="main"
               ref="table"
               form-submit-bus-key="paramconfigManageList"
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
                            name: 'valueTypeDictId'
                        },
                        element:{
                            label: '值类型',
                            type: 'selectDict',
                            selectDict:{
                                groupCode:'java_type'
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
                        prop: 'code',
                        label:'编码'
                    },
                    {
                        prop: 'name',
                        label:'名称'
                    },{
                        prop: 'value',
                        label:'值'
                    },
                    {
                        prop: 'valueTypeDictName',
                        label:'值类型'
                    },
                    {
                        prop: 'isDisabled',
                        label:'是否禁用'
                    }
                ],
                tableOperations:[
                    {
                        action: 'nav',
                        url:'/paramConfig/paramConfigAdd',
                        label: '添加'
                    },
                    {
                        action: 'nav',
                        disabledOnMissingSelect:true,
                        url:'/paramConfig/paramConfigEdit/:id',
                        label: '编辑'
                    },
                    {
                        action: 'delete',
                        url:'/paramconfig/:id',
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
                    this.axios.patch('/paramconfig/'+ row.id,data)
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