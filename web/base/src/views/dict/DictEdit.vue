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
        name: "DictEdit",
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
                                dataUrl:'/dict/tree'
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
                            name: 'isGroup'
                        },
                        element:{
                            type: 'switch',
                            label: '字典组',
                            disabled: true
                        }
                    },
                    {
                        field: {
                            name: 'isSystem'
                        },
                        element:{
                            type: 'switch',
                            label: '系统字典',
                            disabled: true
                        }
                    },
                    {
                        field: {
                            name: 'isPublic'
                        },
                        element:{
                            type: 'switch',
                            label: '公共字典',
                            disabled: true
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
                                    url: '/dict/' + this.$route.params.dictId,
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
            this.getData(this.$route.params.dictId)
        },
        methods:{
            // 根据id获取数据
            getData(id){
                this.formLoading = true
                this.axios.get('/dict/' + id).then(res => {
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