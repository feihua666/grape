<template>
    <div class="g-flex-align-center g-height100 g-width100">
        <UpdatePassword class="update-password" v-on:success="updateSuccess"></UpdatePassword>
    </div>
</template>
<script>
import UpdatePassword from 'common-util/src/components/login/UpdatePassword.vue'
    export default {
        components: {
            UpdatePassword
        },
        created () {
        },
        methods: {
            updateSuccess(){
                this.$message.success("修改成功")
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
    .update-password{
        max-width: 500px;
        min-width: 500px;
    }
</style>
