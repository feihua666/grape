<template>
    <container type="top-header" :show="{header:formShow}" class="g-width100 g-height100" header-style="height:auto;" main-style="padding:0;">
        <Form slot="header" inline
              submit-bus-key="userPostManageList"
              :form-items="formItems"
              label-width=""
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
                        url:'/user/userPost/userPostAdd/' + this.$route.params.userId + '?nickname=' + this.$route.query.nickname,
                        // 没有用户要禁用
                        disabled:!this.$route.params.userId,
                        label: '新增用户岗位'
                    },
                    {
                        action: 'nav',
                        disabledOnMissingSelect:true,
                        url:'/user/userPost/userPostEdit/:id',
                        label: '编辑用户岗位'
                    },
                    {
                        action: 'delete',
                        url:'/user/:id',
                        disabledOnMissingSelect:true,
                        label:'删除用户岗位'
                    },
                    {
                        action: 'enableOrDisable',
                        enableOrDisable:{
                            prop:'isEffect',
                            reasonProp:'ineffectReason'
                        },
                        url:'/userpost/:id',
                        position:'more',
                        disabledOnMissingSelect:true,
                        label:(row) => {
                            if(row){
                                return row.isEffect ? '使其失效': '启用'
                            }else {
                                return '启用/锁定'
                            }
                        }
                    },
                    {
                        action: 'nav',
                        position:'more',
                        disabledOnMissingSelect:true,
                        url:'/user/userPost/userPostAssignRole/:id',
                        label: '分配角色'
                    },
                    {
                        action: 'nav',
                        url:'/user/userPost/userPostAssignDataScope/:id',
                        position:'more',
                        disabledOnMissingSelect:true,
                        label: '分配数据范围约束'
                    },
                    {
                        action: 'nav',
                        url:'/user/userPost/userPostAssignFunc/:id',
                        position:'more',
                        disabledOnMissingSelect:true,
                        label: '分配功能'
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