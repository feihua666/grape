<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100">
        <Form slot="header" inline
              submit-bus-key="dataObjectManageList"
              :form-items="formItems"
        ></Form>
        <Table slot="main"
               ref="table"
               form-submit-bus-key="dataObjectManageList"
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
                            label: '名称'
                        }
                    },
                    {
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'get',
                                    url: '/dataobject/listPage',
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
                        prop: 'dataCustomUrl',
                        label:'自定义url'
                    },
                    {
                        prop: 'isDataLazy',
                        label:'是否数据懒加载'
                    },
                    {
                        prop: 'interviewModeDictName',
                        label:'交互方式'
                    },
                    {
                        prop: 'tableModeColumns',
                        label:'表格交互列定义'
                    }
                ],
                tableOperations:[
                    {
                        action: 'nav',
                        url:'/dataConstraint/dataObjectAdd',
                        label: '添加'
                    },
                    {
                        action: 'nav',
                        disabledOnMissingSelect:true,
                        url:'/dataConstraint/dataObjectEdit/:id',
                        label: '编辑'
                    },
                    {
                        action: 'delete',
                        url:'/dataobject/:id',
                        disabledOnMissingSelect:true,
                        label:'删除'
                    },
                    {
                        action: 'nav',
                        url:'/dataConstraint/dataScopeAdd/:id',
                        disabledOnMissingSelect:true,
                        position: 'more',
                        label:'添加数据范围'
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