<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100" header-style="height:auto;" main-style="padding:0;">
        <Form slot="header" inline
              submit-bus-key="dataScopeManageList"
              :form-items="formItems"
              label-width=""
        ></Form>
        <Table slot="main"
               ref="table"
               form-submit-bus-key="dataScopeManageList"
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
                            name: 'dataObjectId'
                        },
                        element:{
                            type: 'select',
                            select:{
                                url: '/dataobject/list'
                            },
                            label: '数据对象'
                        }
                    },
                    {
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'get',
                                    url: '/datascope/listPage',
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
                        prop: 'dataObjectName',
                        label:'数据对象'
                    },
                    {
                        prop: 'isCustom',
                        label:'是否自定义'
                    }
                ],
                tableOperations:[
                    {
                        action: 'nav',
                        url:'/dataConstraint/dataScopeAdd',
                        label: '添加'
                    },
                    {
                        action: 'nav',
                        disabledOnMissingSelect:true,
                        url:'/dataConstraint/dataScopeEdit/:id',
                        label: '编辑'
                    },
                    {
                        action: 'delete',
                        url:'/datascope/:id',
                        disabledOnMissingSelect:true,
                        label:'删除'
                    },
                    {
                        action: this.navigateToCustom,
                        position: 'more',
                        url: '/dataConstraint/dataScopeAssignData/:id',
                        disabledOnMissingSelect:true,
                        disabled:(row)=>{
                            return !row.isCustom
                        },
                        label:'自定义数据'
                    }
                ]
            }
        },
        methods:{
            navigateToCustom(row){
                this.axios.get('/dataobject/dataobject/' + row.id)
                    .then(res=>{
                        let dataobject = res.data.data
                        // 此字符串与字典一致，最终源自后台DataObject实现类定义
                        if('interview_mode_tree' == dataobject.interviewModeDictCode){
                            this.$router.push('/dataConstraint/dataScopeAssignDataTree/' + row.id)
                        }else if('interview_mode_table' == dataobject.interviewModeDictCode){
                            this.$router.push('/dataConstraint/dataScopeAssignDataTable/' + row.id)
                        }else {
                            // 其它交互方式，比如穿梭框，暂未用到，没有实现
                        }
                    }).catch(error=>{
                        if(error.response){
                            this.$message.error(error.response.data.msg)
                        }else {
                            this.$message.error("未知错误")
                        }
                })
            }
        }
    }
</script>

<style scoped>

</style>