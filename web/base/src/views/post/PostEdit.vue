<template>
    <Form
          :form-items="formItems"
          :form-item-value="formData"
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
                            name: 'isPublic'
                        },
                        element:{
                            type: 'switch',
                            label: '是否公共',
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'typeDictId'
                        },
                        element:{
                            type: 'selectDict',
                            selectDict:{
                                groupCode: 'post_type'
                            },
                            label: '类型',
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'deptId',
                            deptId__label: ''
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/dept/tree'
                            },
                            label: '部门',
                            required:(form)=>{
                                return form.isPublic == false
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
                                    url: '/post/' +this.$route.params.id,
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
            this.getData(this.$route.params.id)
        },
        methods:{
            // 根据id获取数据
            getData(id){
                this.axios.get('/post/' + id).then(res => {
                    let data = res.data.data
                    data.deptId__label = data.deptName
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
                })
            }
        }
    }
</script>

<style scoped>

</style>