<template>
    <Form
          :form-items="formItems"
    ></Form>
</template>

<script>
    import Form from 'common-util/src/components/page/Form.vue'
    export default {
        name: "CompAdd",
        components:{
            Form
        },
        data(){
            return {
                formItems:[
                    {
                        field: {
                            name: 'parentId',
                            value:this.$route.query.id,
                            parentId__label:this.$route.query.name
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/comp/tree'
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
                            name: 'isVirtual'
                        },
                        element:{
                            type: 'switch',
                            label: '虚拟公司'
                        }
                    },
                    {
                        field: {
                            name: 'typeDictId'
                        },
                        element:{
                            type: 'selectDict',
                            selectDict:{
                                groupCode: 'comp_type'
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
                                    url: '/comp',
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