<template>
    <Form
          :form-items="formItems"
          :init-data-url="'/userpost/' + $route.params.userPostId"
          :handle-init-data="handleFormInitData"
    ></Form>
</template>

<script>
    import Form from 'common-util/src/components/page/Form.vue'
    export default {
        components:{
            Form
        },
        data(){
            return {
                formItems:[
                    {
                        field: {
                            name: 'userNickname'
                        },
                        element:{
                            type: 'txt',
                            label: '用户'
                        }
                    },
                    {
                        field: {
                            name: 'userId'
                        }
                    },
                    {
                        field: {
                            name: 'compId',
                            compId__label:''
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl: '/comp/tree'
                            },
                            required: true,
                            label: '公司'
                        }
                    },
                    {
                        field: {
                            name: 'deptId',
                            deptId__label:''
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl: (form)=>{
                                    return '/dept/tree?compId=' + form.compId
                                }
                            },
                            required: true,
                            disable:(form)=>{
                                return !form.compId
                            },
                            label: '部门'
                        }
                    },
                    {
                        field: {
                            name: 'postId'
                        },
                        element:{
                            label: '岗位',
                            type: 'select',
                            select:{
                                url:(form)=>{
                                    return '/post/list?deptId=' + form.deptId
                                }
                            },
                            disable:(form)=>{
                                return !form.deptId
                            },
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'jobId'
                        },
                        element:{
                            label: '职务',
                            type: 'select',
                            select:{
                                url:(form)=>{
                                    return '/job/list?deptId=' + form.deptId
                                }
                            },
                            disable:(form)=>{

                                return !form.deptId
                            },
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'jobLevelId'
                        },
                        element:{
                            label: '职级',
                            type: 'select',
                            select:{
                                url:(form)=>{
                                    return '/joblevel/list?jobId=' + form.jobId
                                }
                            },
                            disable:(form)=>{
                                return !form.jobId
                            },
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'isMain'
                        },
                        element:{
                            type: 'switch',
                            label: '是否主岗'
                        }
                    },
                    {
                        field: {
                            name: 'isEffect'
                        },
                        element:{
                            type: 'switch',
                            label: '是否有效'
                        }
                    },
                    {
                        field: {
                            name: 'ineffectReason'
                        },
                        element:{
                            label: '无效原因',
                            required: (form)=>{
                                return !form.isEffect
                            }
                        }
                    },
                    {
                        field: {
                            name: 'effcetAt'
                        },
                        element:{
                            type: 'date',
                            date:{
                                type:'datetime'
                            },
                            label: '生效时间',
                            required: (form)=>{
                                return !form.isEffect
                            }
                        }
                    },
                    {
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'put',
                                    url: '/userpost/' +this.$route.params.userPostId,
                                    label: '保存'
                                },
                                {
                                    action: 'reset',
                                    label: '重置',
                                    buttonType: 'default'
                                }
                            ]
                        }
                    }
                ]
            }
        },
        methods:{
            handleFormInitData(data){
                data.compId__label = data.compName
                data.deptId__label = data.deptName
                return data
            }
        }
    }
</script>

<style scoped>

</style>