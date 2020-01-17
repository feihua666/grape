<template>
    <Nav></Nav>
</template>
<script>
    import Nav from '@/views/nav/Nav.vue'
    import {installMfe} from 'common-util/src/components/mfe/index'
    // 放在这里会最先执行优先于main.js
    installMfe()
    export default {
        name: 'app',
        components:{
            Nav
        },
        mounted() {
        }
    }
</script>