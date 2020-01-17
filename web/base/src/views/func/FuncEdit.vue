<template>
    <Form
          :form-items="formItems"
          :form-item-value="formData"
          :loading="formLoading"
    ></Form>
</template>

<script>
    import Form from 'common-util/src/components/page/Form.vue'
    export default {
        name: "FuncEdit",
        components:{
            Form
        },
        data(){
            return {
                formLoading: false,
                formData:null,
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
                            parentId__label:''
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/func/tree'
                            },
                            disabled: true,
                            label: '父级'
                        }
                    },
                    {
                        field: {
                            name: 'code',
                        },
                        element:{
                            label: '编码',
                            disabled: true
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
                            name: 'typeDictId',
                            // 表单验证需要
                            typeDictId__code: ''
                        },
                        element:{
                            type: 'selectDict',
                            selectDict:{
                                groupCode: 'func_type'
                            },
                            disabled: true,
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
                            placeholder: '页面类型必填，路由地址',
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
                            name: 'seq'
                        },
                        element:{
                            type: 'inputNumber',
                            label: '排序',
                        }
                    },{
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
                                    url: '/func/' +this.$route.params.funcId,
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
            this.getData(this.$route.params.funcId)
        },
        methods:{
            // 根据id获取数据
            getData(id){
                this.formLoading = true
                this.axios.get('/func/' + id).then(res => {
                    let data = res.data.data
                    data.parentId__label = data.parentName
                    data.typeDictId__code = data.typeDictCode
                    this.formData = data
                }).catch(error => {
                    if(error.response){
                        if(error.response.status == 404){
                            this.$message.error('数据不存在，请刷新数据再试')
                        }else {
                            this.$message.error(error.response.data.msg)
                        }
                    }else {
                        this.$message.error('数据加载失败')
                    }
                }).finally(()=>{
                    this.formLoading = false
                })
            }
        }
    }
</script>

<style scoped>

</style>