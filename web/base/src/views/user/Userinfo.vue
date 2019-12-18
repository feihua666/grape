<template>
    <div class="g-flex-align-h-center g-height100 g-width100">
        <el-card style="width:500px;">
            <div slot="header" class="g-flex-align-v-center g-flex-align-h-between">
                <span>个人信息</span>
                <el-avatar style="float: right; padding: 3px 0" icon="el-icon-user-solid" :src="currentUserinfo.avatar"></el-avatar>
            </div>
            <div class="g-font-size-md">
                <el-row type="flex" class="item" justify="space-between">
                    <el-col :span="8">昵称:</el-col><el-col :span="6" class="g-color-less">{{currentUserinfo.nickname}}</el-col>
                </el-row>
                <el-row type="flex" class="item" justify="space-between">
                    <el-col :span="8">归属公司:</el-col><el-col :span="6" class="g-color-less">{{currentUserinfo.compName}}</el-col>
                </el-row>
                <el-row type="flex" class="item" justify="space-between">
                    <el-col :span="8">归属部门:</el-col><el-col :span="6" class="g-color-less">{{currentUserinfo.deptName}}</el-col>
                </el-row>
                <el-row type="flex" class="item" justify="space-between">
                    <el-col :span="8">当前切换模式:</el-col><el-col :span="6" class="g-color-less">{{currentUserinfo.isSwitchRole == null? '无可切换模式':currentUserinfo.isSwitchRole? '角色切换': '岗位切换'}}</el-col>
                </el-row>
                <el-row type="flex" class="item" justify="space-between">
                    <el-col :span="8">当前使用的角色:</el-col><el-col :span="6" class="g-color-less">{{currentUserinfo.currentRoleName}}</el-col>
                </el-row>
                <el-row type="flex" class="item" justify="space-between">
                    <el-col :span="8">当前使用岗位:</el-col><el-col :span="6" class="g-color-less">{{currentUserinfo.currentPostName}}</el-col>
                </el-row>
            </div>

        </el-card>
    </div>

</template>

<script>
    export default {
        data (){
            return {
                userinfoLoading: false,
                currentUserinfo:{}
            }
        },
        mounted(){
            this.getUserinfo()
        },
        methods:{
            getUserinfo(){
                this.userinfoLoading = true
                this.axios.get('/user/userinfo/current').then(res => {
                    this.currentUserinfo = res.data.data
                }).catch((error) => {
                    if(error.response){
                        if(error.response.status != 401){
                            this.$message.error(error.response.data.msg)
                        }
                    }
                }).finally(()=>{
                    this.userinfoLoading = false
                })
            }
        }
    }
</script>

<style scoped>
    .item {
        margin-bottom: 18px;
    }
</style>