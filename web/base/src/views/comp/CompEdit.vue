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
        name: "CompEdit",
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
                            name: 'parentId',
                            parentId__label:''
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/comp/tree'
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
                                    url: '/comp/' +this.$route.params.compId,
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
            this.getData(this.$route.params.compId)
        },
        methods:{
            // 根据id获取数据
            getData(id){
                this.formLoading = true
                this.axios.get('/comp/' + id).then(res => {
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
                }).finally(()=>{
                    this.formLoading = false
                })
            }
        }
    }
</script>

<style scoped>

</style>