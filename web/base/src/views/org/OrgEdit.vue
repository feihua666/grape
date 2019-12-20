<template>
    <Form
          :form-items="formItems"
          :init-data-url="'org/' + $route.params.orgId"
          :handle-init-data="handleInitData"
    ></Form>
</template>

<script>
    import Form from 'common-util/src/components/page/Form.vue'
    export default {
        name: "OrgEdit",
        components:{
            Form
        },
        data(){
            return {
                formItems:[
                    {
                        field: {
                            name: 'name'
                        },
                        element:{
                            label: '名称',
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'orgNameId'
                        },
                        element:{
                            type:'select',
                            select:{
                                url:'/orgname/list'
                            },
                            label: '组织树名称',
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'parentId',
                            parentId__label:''
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/org/tree'
                            },
                            disabled: true,
                            label: '父级'
                        }
                    },
                    {
                        field: {
                            name: 'relationCompId',
                            relationCompId__label:''
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/comp/tree'
                            },
                            label: '关联公司'
                        }
                    },
                    {
                        field: {
                            name: 'relationDeptId',
                            relationDeptId__label:''
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/dept/tree'
                            },
                            label: '关联部门',
                            validator:(rule, value, callback,form)=>{
                                if(form.relationCompId && form.relationDeptId){
                                    callback(new Error('只能关联一个公司或部门或不关联'))
                                }else{
                                    callback()
                                }
                            }
                        }
                    },
                    {
                        field: {
                            name: 'typeDictId'
                        },
                        element:{
                            type: 'selectDict',
                            selectDict:{
                                groupCode: 'org_type'
                            },
                            required: true,
                            label: '类型'
                        }
                    },
                    {
                        field: {
                            name: 'remark'
                        },
                        element:{
                            type: 'textarea',
                            label: '备注',
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
                                    requestMethod:'put',
                                    url: '/org/' +this.$route.params.orgId,
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
        mounted(){
        },
        methods:{
            handleInitData(data){
                data.parentId__label = data.parentName
                data.relationCompId__label = data.relationCompName
                data.relationDeptId__label = data.relationDeptName
                return data
            }
        }
    }
</script>

<style scoped>

</style>