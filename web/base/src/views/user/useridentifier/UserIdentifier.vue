<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100">
        <Form slot="header" inline
              submit-bus-key="userIdentifierManageList"
              :form-items="formItems"
        ></Form>
        <Table slot="main"
               ref="table"
               form-submit-bus-key="userIdentifierManageList"
               :columns="columns"
               :operations="tableOperations"
               unique-label="identifier"
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
                            name: 'identifier'
                        },
                        element:{
                            label: '登录帐号'
                        }
                    },
                    {
                        field: {
                            name: 'userId',
                            value: this.$route.params.userId
                        }
                    },
                    {
                        field: {
                            name: 'version'
                        }
                    },
                    {
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'get',
                                    url: '/useridentifier/listPage',
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
                        prop: 'identifier',
                        label:'登录帐号'
                    },
                    {
                        prop: 'identityTypeDictName',
                        label:'登录帐号类型'
                    },
                    {
                        prop: 'isLock',
                        label:'是否锁定'
                    },
                    {
                        prop: 'lockReason',
                        label:'锁定原因'
                    }
                ],
                tableOperations:[
                    {
                        action: 'nav',
                        url:'/user/userIdentifier/userIdentifierAdd/' + this.$route.params.userId,
                        label: '新增帐号'
                    },
                    {
                        action: 'nav',
                        disabledOnMissingSelect:true,
                        url:'/user/userIdentifier/userIdentifierEdit/:id',
                        label: '编辑帐号'
                    },
                    {
                        action: 'delete',
                        url:'/useridentifier/:id',
                        disabledOnMissingSelect:true,
                        label:'删除帐号'
                    },
                    {
                        action: 'enableOrDisable',
                        enableOrDisable:{
                            prop:'isLock',
                            reasonProp:'lockReason'
                        },
                        url:'/useridentifier/:id',
                        position:'more',
                        disabledOnMissingSelect:true,
                        label:(row) => {
                            if(row){
                                return row.isLock ? '启用': '锁定'
                            }else {
                                return '启用/锁定'
                            }
                        }
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