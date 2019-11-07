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
                            name: 'requestId'
                        },
                        element:{
                            label: '请求id',
                            type:'txt'
                        }
                    },
                    {
                        field: {
                            name: 'typeDictName'
                        },
                        element:{
                            label: '日志类型',
                            type:'txt'
                        }
                    },
                    {
                        field: {
                            name: 'userNickname'
                        },
                        element:{
                            label: '操作用户昵称',
                            type:'txt'
                        }
                    },
                    {
                        field: {
                            name: 'content'
                        },
                        element:{
                            label: '日志内容',
                            type:'txt'
                        }
                    },
                    {
                        field: {
                            name: 'host'
                        },
                        element:{
                            label: '请求IP',
                            type:'txt'
                        }
                    },
                    {
                        field: {
                            name: 'responseHttpStatus'
                        },
                        element:{
                            label: '请求结果状态码',
                            type:'txt'
                        }
                    },
                    {
                        field: {
                            name: 'requestHttpMethod'
                        },
                        element:{
                            label: '请求方法',
                            type:'txt'
                        }
                    },
                    {
                        field: {
                            name: 'requestParams'
                        },
                        element:{
                            label: '请求参数',
                            type:'txt'
                        }
                    },
                    {
                        field: {
                            name: 'requestHttpHeader'
                        },
                        element:{
                            label: '请求头',
                            type:'txt'
                        }
                    },
                    {
                        field: {
                            name: 'requestHttpUrl'
                        },
                        element:{
                            label: '请求url',
                            type:'txt'
                        }
                    },
                    {
                        field: {
                            name: 'operationName'
                        },
                        element:{
                            label: '操作名称',
                            type:'txt'
                        }
                    },
                    {
                        field: {
                            name: 'interfaceName'
                        },
                        element:{
                            label: '接口名称',
                            type:'txt'
                        }
                    },
                    {
                        field: {
                            name: 'duration'
                        },
                        element:{
                            label: '执行时长（毫秒）',
                            type:'txt'
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
                this.formLoading = true
                this.axios.get('/log/' + id).then(res => {
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