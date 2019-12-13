<template>
    <Form
          :form-items="formItems"
    ></Form>
</template>

<script>
    import Form from 'common-util/src/components/page/Form.vue'
    export default {
        name: "FuncAdd",
        components:{
            Form
        },
        data(){
            return {
                formItems:[
                    {
                        field: {
                            name: 'applicationId'
                        },
                        element:{
                            label: '应用',
                            type:'select',
                            select:{
                                url: '/application/list'
                            },
                            required:true
                        }
                    },
                    {
                        field: {
                            name: 'parentId',
                            value:this.$route.query.id,
                            parentId__label:this.$route.query.name
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/func/tree'
                            },
                            disabled: !!this.$route.query.id,
                            placeholder: '不填写为根节点',
                            label: '父级'
                        }
                    },
                    {
                        field: {
                            name: 'code'
                        },
                        element:{
                            label: '编码',
                            required: true
                        }
                    },
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
                            name: 'permissions'
                        },
                        element:{
                            label: '权限',
                            placeholder:'如 user:delete,多个以英文逗号分隔'
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
                            required: true,
                            label: '类型'
                        }
                    },
                    {
                        field: {
                            name: 'icon'
                        },
                        element:{
                            type: 'inputSelectIcon',
                            label: '图标',
                            required: (form)=>{
                                return form.typeDictId__code == 'menu'
                            }
                        }
                    },
                    {
                        field: {
                            name: 'url'
                        },
                        element:{
                            label: '地址',
                            placeholder: '页面类型时必填，路由地址',
                            required:(form)=>{
                                return form.typeDictId__code == 'page'
                            }
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
                            name: 'seq',
                            value:10
                        },
                        element:{
                            type: 'inputNumber',
                            label: '排序',
                        }
                    },
                    {
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'post',
                                    url: '/func',
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