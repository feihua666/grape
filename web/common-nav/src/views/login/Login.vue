<template>
    <div class="g-center-all g-height100 g-width100">
        <LoginComp class="login" v-on:success="loginSuccess"></LoginComp>
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
                this.$router.replace('/nav')
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
