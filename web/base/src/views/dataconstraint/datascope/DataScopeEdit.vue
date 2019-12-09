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
                            name: 'code'
                        },
                        element:{
                            label: '编码',
                            required: true,
                            disable:true
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
                            name: 'dataObjectId'
                        },
                        element:{
                            label: '数据对象',
                            type: 'select',
                            select:{
                                url:'/dataobject/list'
                            },
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'isCustom'
                        },
                        element:{
                            type: 'switch',
                            label: '自定义',
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'constraintContent'
                        },
                        element:{
                            type: 'textarea',
                            label: '约束内容',
                            required:(form)=>{
                                return !form.isCustom
                            }
                        }
                    },
                    {
                        field: {
                            name: 'remark'
                        },
                        element:{
                            label: '备注'
                        }
                    },
                    {
                        field:{
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
                                    url: '/datascope/' +this.$route.params.dataScopeId,
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
            this.getData(this.$route.params.dataScopeId)
        },
        methods:{
            // 根据id获取数据
            getData(id){
                this.formLoading = true
                this.axios.get('/datascope/' + id).then(res => {
                    let data = res.data.data
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