<template>
    <el-dropdown class=" g-flex-align-v-center" @command="handleUserinfoCommand" :v-loading="userinfoLoading"  element-loading-spinner="el-icon-loading">
        <div class="g-pointer g-height100 g-flex-align-v-center  g-color-fff"  style="padding: 0 5px;">
            <el-avatar icon="el-icon-user-solid" :src="currentUserinfo.avatar"></el-avatar>
            <span> {{currentUserinfo.nickname}}</span>
        </div>
        <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="userinfo">个人信息</el-dropdown-item>
            <el-dropdown-item  command="updatePwd">修改密码</el-dropdown-item>
            <el-dropdown-item command="updateAvatar">修改头像</el-dropdown-item>
            <el-dropdown-item divided  command="logout">退出登陆</el-dropdown-item>
        </el-dropdown-menu>
    </el-dropdown>
</template>

<script>

    export default {
        props:{
            userinfoLoading:{
                type:Boolean,
                default: false
            },
            currentUserinfo:{
                type:Object,
                default: function () {
                    return {}
                }
            }
        },
        data (){
            return {
                roleChangePopShow: false,
                postChangePopShow: false
            }
        },
        mounted(){
        },
        methods:{
            handleUserinfoCommand(command){
                switch (command) {
                    case 'userinfo':{

                        this.$router.push('/base/user/userinfo/current')
                        break
                    }
                    case 'updatePwd':{

                        this.$router.push('/base/user/updatePwd')
                        break
                    }
                    case 'updateAvatar':{
                        break
                    }
                    case 'logout':{
                        this.axios.post('/user/logout').then(res=>{
                            if (window.mfe) {
                                // 该事件是在portal项目app.vue中监听
                                window.mfe_vue_bus.$emit('toLogin')
                            }else {
                                // 如果不是向前端
                                this.$router.replace('/login')
                            }

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