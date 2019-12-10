<template>
    <div>
        <el-alert
                title="不会遵循严格父选子也选的规则，也就是说只以手动勾选的为准"
                type="warning" show-icon  :closable="false">
        </el-alert>
        <el-alert  v-if="lazyload"
                   title="此树为懒加载，暂不支持一键全选，当前已选择的数据和取消选择的数据不会影响未加载的选中数据"
                   type="info" show-icon :closable="false">
        </el-alert>
        <slot name="alert"></slot>

    <tree ref="tree" :lazy="lazyload" show-checkbox check-strictly  :checked-keys="selectedIds" :data-url="dataUrl">
    </tree>
        <div class="g-align-center">
            <el-button type="primary" size="mini" :loading="submitLoading" @click="doSubmit">保存</el-button>
            <el-button v-if="clearAllUrl" type="primary" size="mini" :loading="clearLoading" @click="clearAll">删除所有数据</el-button>
        </div>
    </div>
</template>

<script>
    /* 用于关系表分配关系 */
    import Tree from '../../components/common/Tree.vue'
    export default {
        components:{
            Tree
        },
        props:{
            // 待选择的数据url
            dataUrl:{
                type: String,
                required: true
            },
            // 已选择的数据url
            checkedDataUrl:{
                type: String,
                required: true
            },
            // 提交的url
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
            // 已选中的提交属性
            checkedProp:{
                type: String,
                required: true
            },
            // 未选中的提交属性
            // 懒加载时填写
            uncheckedProp:{
                type: String
            },
            // 是否是数据懒加载，懒加载指供选择的数据不是数据库中全部数据，而是按分页或树懒加载展示
            lazyload:{
                type: Boolean,
                default: true
            },
            // 清除所有已选的url
            clearAllUrl:{
                type: String
            },
            // 限制的选择个数，最多选择几个
            limit:{
                type:Number
            },
            // 在form提交之前可以再做一些操作
            formFormat:{
                type:Function
            }
        },
        data (){
            return {
                // 已选择的id
                selectedIds: [],
                submitLoading: false,
                clearLoading: false
            }
        },
        mounted(){
            this.getCheckedIds()
        },
        methods:{
            getCheckedIds(){
                this.axios.get(this.checkedDataUrl)
                    .then((res)=>{
                        this.selectedIds = res.data.data
                    }).catch((error)=>{

                })
            },
            doSubmit(){
                this.submitLoading = true
                let data = {}
                data[this.mainProp] = this.mainValue
                data[this.checkedProp] = this.$refs.tree.getAllCheckedKeys()
                if(this.limit && data[this.checkedProp] && data[this.checkedProp].length > this.limit){
                    this.$message.error('最多可选择 ' + this.limit + '个')
                    return
                }
                if(this.uncheckedProp){
                    data[this.uncheckedProp] = this.$refs.tree.getAllUncheckedKeys()
                }
                data.isLazyLoad = this.lazyload
                if(data[this.checkedProp].length == 0){
                    data[this.checkedProp] = null
                }
                if(this.uncheckedProp && data[this.uncheckedProp].length == 0){
                    data[this.uncheckedProp] = null
                }
                if(this.formFormat){
                    data = this.formFormat(data)
                }
                this.axios.post(this.submitUrl,data)
                    .then((res)=>{
                        this.$message.success('保存成功')
                    }).catch((error)=>{
                    if (error.response) {
                        this.$message.error(error.response.data.msg)
                    }else{
                        this.$message.error('操作失败，未知原因')
                    }
                }).finally(()=>{
                    this.submitLoading = false
                })
            },
            clearAll(){
                this.$confirm('将要删除所有数据, 是否继续?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.clearLoading = true
                    this.axios.delete(this.clearAllUrl)
                        .then(res => {
                            this.$message.success('删除成功')
                            // 重新加载数据
                            this.selectedIds = []
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
                        this.clearLoading = false
                    })
                })

            }
        }
    }
</script>

<style scoped>

</style>