<template>
    <container type="top-header" class="g-width100 g-height100">
        <Form slot="header" inline
              :form-items="formItems"
        ></Form>
        <Table slot="main" :bus-key-prefix="busKeyPrefix" bus-data-prop="submit" :columns="columns"></Table>
    </container>
</template>

<script>
    import Container from 'common-util/src/components/page/Container.vue'
    import Form from 'common-util/src/components/page/Form.vue'
    import Table from 'common-util/src/components/page/Table.vue'
    export default {
        name: "Func",
        components:{
            Form,
            Table,
            Container
        },
        data(){
            let busKeyPrefix = 'funManageList'
            return {
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
                            name: 'typeDictId'
                        },
                        element:{
                            type: 'selectDict',
                            selectDict:{
                                groupCode: 'func_type'
                            },
                            label: '类型'
                        }
                    },
                    {
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'get',
                                    actionUrl: '/func/listPage',
                                    busKeyPrefix: busKeyPrefix,
                                    label: '查询'
                                },
                                {
                                    action: 'reset',
                                    label: '重置',
                                    buttonType: 'default'
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
                        prop: 'isDisabled',
                        label:'是否禁用'
                    },
                    {
                        prop: 'typeDictName',
                        label:'类型'
                    },
                    {
                        prop: 'seq',
                        label:'排序'
                    }
                ],
                busKeyPrefix: busKeyPrefix
            }
        },
        methods:{
        }
    }
</script>

<style scoped>

</style>