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
        name: "DeptEdit",
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
                            name: 'compId',
                            compId__label:''
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/comp/tree'
                            },
                            label: '公司'
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
                                dataUrl:'/dept/tree'
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
                            disable: true
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
                            label: '虚拟部门'
                        }
                    },

                    {
                        field: {
                            name: 'typeDictId'
                        },
                        element:{
                            type: 'selectDict',
                            selectDict:{
                                groupCode: 'dept_type'
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
                                    url: '/dept/' +this.$route.params.deptId,
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
            this.getData(this.$route.params.deptId)
        },
        methods:{
            // 根据id获取数据
            getData(id){
                this.formLoading = true
                this.axios.get('/dept/' + id).then(res => {
                    let data = res.data.data
                    data.parentId__label = data.parentName
                    data.compId__label = data.compName
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