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
                            name: 'dataCustomUrl'
                        },
                        element:{
                            label: '自定义url',
                            placeholder:'自定义url应该是一个加载数据的url'
                        }
                    },
                    {
                        field: {
                            name: 'isDataLazy'
                        },
                        element:{
                            type: 'switch',
                            label: '数据是否懒加载',
                            required:(form)=>{return !!form.dataCustomUrl}
                        }
                    },
                    {
                        field: {
                            name: 'interviewModeDictId'
                        },
                        element:{
                            type: 'selectDict',
                            selectDict:{
                                groupCode:'data_object_custom_interview_mode'
                            },
                            label: '交互方式',
                            required:(form)=>{return !!form.dataCustomUrl}
                        }
                    },
                    {
                        field: {
                            name: 'tableModeColumns'
                        },
                        element:{
                            type: 'textarea',
                            label: '表格列定义',
                            placeholder:'默认id和名称'
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
                                    url: '/dataobject/' +this.$route.params.dataObjectId,
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
            this.getData(this.$route.params.dataObjectId)
        },
        methods:{
            // 根据id获取数据
            getData(id){
                this.formLoading = true
                this.axios.get('/dataobject/' + id).then(res => {
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