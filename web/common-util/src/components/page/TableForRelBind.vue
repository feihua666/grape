<template>
    <container type="top-header" :show="{header:formShow && formItems.length>0}" class="g-width100 g-height100">
        <Form slot="header" inline v-if="formItems.length>0"
              submit-bus-key="tableRelAssignList"
              :form-items="formItems"
        ></Form>
        <Table slot="main"
               ref="table"
               form-submit-bus-key="tableRelAssignList"
               :columns="columns"
               unique-label="name"
               v-on:toolbarSearchClick="formShow = !formShow"
               :data="data"
               :operations="tableOperations"
               :checked-keys-url="checkedDataUrl"

               :button-loading="buttonLoading"
        ></Table>
    </container>
</template>

<script>
    import Container from '../../components/page/Container.vue'
    import Form from '../../components/page/Form.vue'
    import Table from '../../components/page/Table.vue'

    export default {
        components:{
            Form,
            Table,
            Container
        },
        props:{
            formItems:{
                type: Array,
                default: function () {
                    return []
                }
            },
            columns:{
                type: Array,
                required: true
            },
            formSubmitBusKey:{
                type: String,
                default: null
            },
            // 待选择的数据，具体详见table.vue组件或form.vue组合使用可以不传，不传则以formItems的submit定义为准，如果没有搜索条件请传该值
            data:{
                type: String
            },
            // 已选择的数据url
            checkedDataUrl:{
                type: String,
                required: true
            },
            submitUrl:{
                type: String,
                required: true
            },
            // 主要的属性值
            mainValue:{
                type: String,
                required: true
            },
            // 主要的属性，如：角色分配功能，mainProp可能为roleId
            mainProp:{
                type: String,
                required: true
            },
            checkedProp:{
                type: String,
                required: true
            },
            uncheckedProp:{
                type: String,
                required: true
            },
            lazyload:{
                type: Boolean,
                default: true
            },
            clearAllUrl:{
                type: String
            }
        },
        computed:{
        },
        data(){
            return {
                formShow: true,
                tableOperations:[
                    {
                        code: 'save',
                        action: this.doSubmit,
                        label:'保存'
                    },
                    {
                        code: 'deleteAll',
                        action: this.clearAll,
                        label:'删除所有数据'
                    }
                ],
                buttonLoading:{
                    save: false,
                    deleteAll:false
                }
            }
        },
        mounted(){
        },
        methods:{
            doSubmit(){
                this.buttonLoading.save = true

                let data = {}
                data[this.mainProp] = this.mainValue

                data[this.checkedProp] = this.$refs.table.getCheckedKeys()
                data[this.uncheckedProp] = this.$refs.table.getUncheckedKeys()
                data.isLazyLoad = this.lazyload
                if(data[this.checkedProp].length == 0){
                    data[this.checkedProp] = null
                }
                if(data[this.uncheckedProp].length == 0){
                    data[this.uncheckedProp] = null
                }
                this.axios.post(this.submitUrl,data)
                    .then((res)=>{
                        this.$message.success('保存成功')
                        // 重新加载已选中的数据

                        this.$refs.table.getCheckedData()
                    }).catch((error)=>{
                    if (error.response) {
                        this.$message.error(error.response.data.msg)
                    }else{
                        this.$message.error('操作失败，未知原因')
                    }
                }).finally(()=>{
                    this.buttonLoading.save = false
                })
            },
            clearAll(){
                this.$confirm('将要删除所有数据, 是否继续?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.buttonLoading.deleteAll = true
                    this.axios.delete(this.clearAllUrl)
                        .then(res => {
                            this.$message.success('删除成功')
                            this.$refs.table.clearSelection()
                        })
                        .catch(error => {
                            if(error.response){
                                if(error.response.status == 404){
                                    this.$message.error('数据不存在，请刷新数据再试')
                                }else {
                                    this.$message.error(error.response.data.msg)
                                }
                            }else {
                                this.$message.error('删除失败，请刷新数据再试')
                            }
                        }).finally(()=>{
                        this.buttonLoading.deleteAll = false
                    })
                })

            }
        }
    }
</script>

<style scoped>

</style>