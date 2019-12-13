<template>
    <div class="g-flex-center-all g-height100 g-width100">
        <el-card class="box-card" style="width: 480px;">
            <div slot="header" class="g-flex-center-v g-flex-align-between">
                <span>选择要进入的应用</span>
                <el-avatar icon="el-icon-user-solid" :src="currentUserinfo.avatar"></el-avatar>
            </div>
                <el-button type="primary" v-for="(app,index) in currentUserinfo.applications" :key="index" style="margin:10px;" class="g-pointer" @click="enter(app)" :loading="buttonLoading[app.id]">{{app.name}}</el-button>

        </el-card>

    </div>
</template>

<script>
    import storage from 'common-util/src/tools/StorageTools.js'
    /* 选择应用界面 */
    export default {
        name: "selectApplication",
        data (){
            return {
                userinfoLoading: false,
                currentUserinfo:{},
                buttonLoading:{}
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
                    // 如果只有一个系统直接进入
                    if(this.currentUserinfo.applications.length == 1){
                        this.enter(this.currentUserinfo.applications[0],true)
                    }
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
            enter(application,delay){

                if(delay){
                    this.$message.info('即将进入应用：' + application.name)

                    setTimeout(()=>{
                        this.enter(application,false)
                    },2000)
                }else {
                    this.buttonLoading[application.id] = true
                    storage.set("currentApplication",application)
                    // 临时解决
                    window.location.href=window.location.href.replace('selectApplication','')
                }

            }
        }
    }
</script>

<style scoped>

</style>