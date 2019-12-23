<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100" header-style="height:auto;" main-style="padding:0;">
        <Form slot="header" inline
              submit-bus-key="workCalendarManageList"
              :form-items="formItems"
              label-width=""
        ></Form>
        <Table slot="main"
               ref="table"
               form-submit-bus-key="workCalendarManageList"
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
                            name: 'year'
                        },
                        element:{
                            label: '年',
                            placeholder:'2019'
                        }
                    },
                    {
                        field: {
                            name: 'month'
                        },
                        element:{
                            label: '月',
                            placeholder:'2'
                        }
                    },
                    {
                        field: {
                            name: 'day'
                        },
                        element:{
                            label: '日',
                            placeholder:'23'
                        }
                    },
                    {
                        field: {
                            name: 'typeDictId'
                        },
                        element:{
                            label: '类型',
                            type: 'selectDict',
                            selectDict:{
                                groupCode: 'work_calendar_type'
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
                                    url: '/workcalendar/listPage',
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
                        prop: 'year',
                        label:'年'
                    },
                    {
                        prop: 'month',
                        label:'月'
                    },
                    {
                        prop: 'day',
                        label:'日'
                    },
                    {
                        prop: 'typeDictName',
                        label:'类型'
                    },
                    {
                        prop: 'content',
                        label:'内容'
                    },
                    {
                        prop: 'remark',
                        label:'备注'
                    }
                ],
                tableOperations:[
                    {
                        action: 'nav',
                        url:'/workCalendar/workCalendarAdd',
                        label: '添加'
                    },
                    {
                        action: 'nav',
                        disabledOnMissingSelect:true,
                        url:'/workCalendar/workCalendarEdit/:id',
                        label: '编辑'
                    },
                    {
                        action: 'delete',
                        url:'/workcalendar/:id',
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