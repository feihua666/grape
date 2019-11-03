<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100">
        <Form slot="header" inline
              submit-bus-key="areaManageList"
              :form-items="formItems"
        ></Form>
        <Table slot="main"
               ref="table"
               form-submit-bus-key="areaManageList"
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
                                dataUrl:'/area/tree'
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
                                    url: '/area/listPage',
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
                        prop: 'spellFirst',
                        label:'首字母'
                    },
                    {
                        prop: 'name',
                        label:'名称'
                    },
                    {
                        prop: 'parentName',
                        label:'父级'
                    },
                    {
                        prop: 'typeDictName',
                        label:'类型'
                    },
                    {
                        prop: 'longitude',
                        label:'经度'
                    },
                    {
                        prop: 'latitude',
                        label:'纬度'
                    },
                    {
                        prop: 'adminDivisionId',
                        label:'行政区划id'
                    }
                ],
                tableOperations:[
                    {
                        action: 'nav',
                        url:'/area/areaAdd',
                        label: '添加'
                    },
                    {
                        action: 'nav',
                        disabledOnMissingSelect:true,
                        url:'/area/areaEdit/:id',
                        label: '编辑'
                    },
                    {
                        action: 'delete',
                        url:'/area/:id',
                        disabledOnMissingSelect:true,
                        label:'删除'
                    },
                    {
                        action: 'nav',
                        url:'/area/areaAdd',
                        routeQuery:['id','name'],
                        position:'more',
                        disabledOnMissingSelect:true,
                        label:'添加子节点'
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