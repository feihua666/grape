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
                            disabled: true,
                            required:true
                        }
                    },
                    {
                        field: {
                            name: 'name'
                        },
                        element:{
                            label: '名称',
                            required:true
                        }
                    },
                    {
                        field: {
                            name: 'value'
                        },
                        element:{
                            label: '值',
                            required:true
                        }
                    },
                    {
                        field: {
                            name: 'valueTypeDictId'
                        },
                        element:{
                            label: '值类型',
                            type: 'selectDict',
                            required:true,
                            selectDict:{
                                groupCode:'java_type'
                            }
                        }
                    },
                    {
                        field: {
                            name: 'remark'
                        },
                        element:{
                            type: 'textarea',
                            label: '备注'
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
                                    url: '/paramconfig/' +this.$route.params.paramconfigId,
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
            this.getData(this.$route.params.paramconfigId)
        },
        methods:{
            // 根据id获取数据
            getData(id){
                this.formLoading = true
                this.axios.get('/paramconfig/' + id).then(res => {
                    let data = res.data.data
                    data.parentId__label = data.parentName
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
                }).finally(() => {
                    this.formLoading = false
                })
            }
        }
    }
</script>

<style scoped>

</style>