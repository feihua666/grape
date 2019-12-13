<template>
  <router-view></router-view>
</template>
<script>
    export default {
        name: 'app',
        created () {
            this.bindLoginBus()
        },
        mounted(){
            // 尝试获取用户信息，如果未登录，去登录页面
            this.axios.get('/user/userinfo/current').catch(error =>{
                if (error.response) {
                    let status = error.response.status
                    if(status == 401){
                        this.toLogin()
                    }
                }
            })
        },
        methods:{
            bindLoginBus(){
                if (window.mfe) {
                    window.mfe_vue_bus.$on('toLogin',this.toLogin)
                }
            },
            toLogin(){
                this.$router.replace('/login')
            }
        }
    }
</script>

