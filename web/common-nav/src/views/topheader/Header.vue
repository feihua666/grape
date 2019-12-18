<template>
    <el-container class="g-height100 g-width100 g-padding0" style="background-color: #3c8dbc;">
        <el-aside width="200px" class="g-flex-align-center g-font-size-elg g-color-fff" style="background-color: #367fa9;">
            <i class="el-icon-grape"> grape</i>
        </el-aside>
        <el-main class="g-color-fff g-padding0">
            <el-row type="flex" class="g-height100" justify="space-between">
                <el-col class="g-height100 g-flex-align-v-center">
                    <ApplicationSwitch  :current-userinfo="currentUserinfo" :userinfo-loading="userinfoLoading"></ApplicationSwitch>
                </el-col>
                <el-col class="g-height100 g-flex-align-v-center g-flex-align-h-end">
                    <!-- 右侧 头像和用户名 -->
                    <UserinfoDropdown class="item" :current-userinfo="currentUserinfo" :userinfo-loading="userinfoLoading"></UserinfoDropdown>
                </el-col>
            </el-row>

        </el-main>
    </el-container>
</template>

<script>
    import UserinfoDropdown from './UserinfoDropdown.vue'
    import ApplicationSwitch from './ApplicationSwitch.vue'

    export default {
        components: {
            UserinfoDropdown,
            ApplicationSwitch
        },
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
            }
        }
    }
</script>

<style scoped>
.item{
    height: 100%;
}
.item:hover{
    background-color: #367fa9;
}
</style>