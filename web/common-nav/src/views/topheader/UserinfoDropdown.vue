<template>
    <el-dropdown class=" g-flex-center-v" @command="handleUserinfoCommand"  trigger="click" :v-loading="userinfoLoading"  element-loading-spinner="el-icon-loading">
        <div class="g-pointer g-height100 g-flex-center-v  g-color-fff"  style="padding: 0 5px;">
            <el-avatar icon="el-icon-user-solid" :src="currentUserinfo.avatar"></el-avatar>
            <span> {{currentUserinfo.nickname}}</span>
        </div>
        <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="userinfo">个人信息</el-dropdown-item>
            <el-dropdown-item  command="updatePwd">修改密码</el-dropdown-item>
            <el-dropdown-item command="updateAvatar">修改头像</el-dropdown-item>
            <el-popover
                    placement="left"
                    width="30"
                    v-model="roleChangePopShow"
                    trigger="hover">
                <div class="g-flex-direction-c g-flex-center-h" v-if="currentUserinfo.isSwitchRole">
                    <el-button v-for="role in currentUserinfo.roles" :key="role.id" type="text" class="g-margin0" size="mini" @click="switchRole(role)">{{role.name}}</el-button>
                </div>
                <el-dropdown-item divided :disabled="currentUserinfo.isSwitchRole ===false " slot="reference">角色切换</el-dropdown-item>
            </el-popover>
            <el-popover
                    placement="left"
                    width="30"
                    v-model="postChangePopShow"
                    trigger="hover">
                <div class="g-flex-direction-c g-flex-center-h" v-if="!currentUserinfo.isSwitchRole">
                    <el-button v-for="post in currentUserinfo.posts" :key="post.id" type="text" class="g-margin0" size="mini" @click="switchPost(post)">{{post.name}}</el-button>
                </div>
                <el-dropdown-item divided slot="reference" :disabled="currentUserinfo.isSwitchRole === true">岗位切换</el-dropdown-item>
            </el-popover>
            <el-dropdown-item divided  command="logout">退出登陆</el-dropdown-item>

        </el-dropdown-menu>
    </el-dropdown>
</template>

<script>
    export default {
        data (){
            return {
                roleChangePopShow: false,
                postChangePopShow: false,
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
                    //this.$set('currentUserinfo',res.data.data)
                }).catch((error) => {
                    if(error.response){
                        if(error.response.status != 401){
                            this.$message.error(error.response.data.msg)
                        }
                    }
                }).finally(()=>{
                    this.userinfoLoading = false
                })
            },
            switchRole(role){
                this.roleChangePopShow = !this.roleChangePopShow
                this.axios.post('/user/switchrole/' + role.id).then(res => {
                    this.$message.success('切换成功')
                    window.location.reload()
                }).catch(error => {
                    if(error.response){
                        if(error.response.status != 401){
                            this.$message.error(error.response.data.msg)
                        }
                    }
                })
            },
            switchPost(post){
                this.postChangePopShow = !this.postChangePopShow
                this.axios.post('/user/switchpost/' + post.id).then(res => {
                    this.$message.success('切换成功')
                    window.location.reload()
                }).catch(error => {
                    if(error.response){
                        if(error.response.status != 401){
                            this.$message.error(error.response.data.msg)
                        }
                    }
                })
            },
            handleUserinfoCommand(command){
                switch (command) {
                    case 'userinfo':{
                        // 该页面在base项目
                        this.$router.push('/nav/base/user/userinfo/current')
                        break
                    }
                    case 'updatePwd':{
                        // 该页面在base项目
                        this.$router.push('/nav/base/user/updatePwd')
                        break
                    }
                    case 'updateAvatar':{
                        break
                    }
                    case 'logout':{
                        this.axios.post('/user/logout').then(res=>{
                            this.$router.replace('/login')
                        })
                        break
                    }
                }
            }
        }
    }
</script>

<style scoped>

</style>