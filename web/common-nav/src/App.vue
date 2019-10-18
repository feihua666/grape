<template>
    <router-view></router-view>
</template>
<script>
    export default {
        name: 'app',
        created () {
            this.bindLoginBus()
        },
        methods:{
            bindLoginBus(){
                if (window.mfe) {
                    window.mfe_vue_bus.$once('login',this.toLogin)
                }
            },
            toLogin(){
                this.$router.replace('/login')
            }
        }
    }
</script>