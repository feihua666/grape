<template>
    <Form
          :form-items="formItems"
    ></Form>
</template>

<script>
    import Form from 'common-util/src/components/page/Form.vue'
    export default {
        name: "OrgAdd",
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
                            placeholder: '手动输入的名称将会覆盖关联的公司或部门，如果只输入了名称没有关联则是一个非关联节点',
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
                            value: this.$route.query.id,
                            parentId__label:this.$route.query.name
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/org/tree'
                            },
                            disable: !!this.$route.query.id,
                            placeholder: '不填写为根节点',
                            label: '父级'
                        }
                    },
                    {
                        field: {
                            name: 'relationCompId'
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
                            name: 'relationDeptId'
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
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'post',
                                    url: '/org',
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
        }
    }
</script>

<style scoped>

</style>