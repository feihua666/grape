<template>
    <div class="g-flex-align-center g-height100 g-width100">
        <LoginComp style="width: 500px;" class="login" v-on:success="loginSuccess"></LoginComp>
    </div>
</template>
<script>
import LoginComp from 'common-util/src/components/login/Login.vue'
    export default {
        name: 'Login',
        components: {
            LoginComp
        },
        created () {
        },
        methods: {
            loginSuccess(){
                this.$router.replace('/selectApplication')
                // 临时解决方案，因为导航项目在登录页面是无法挂载的
                //window.location.href=window.location.href.replace('login','')
            }
        },
        data () {
            return {}
        },
        destroyed() {
        },
        watch: {
        }
    }
</script>
<style scoped>
    .login{
        max-width: 500px;
        min-width: 500px;
    }
</style>
