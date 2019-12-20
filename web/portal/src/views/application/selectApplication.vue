<template>
    <div class="g-flex-align-center g-height100 g-width100">
        <el-card class="box-card" style="width: 480px;">
            <div slot="header" class="g-flex-align-v-center g-flex-align-h-between">
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
                currentUserinfo:{
                },
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
                    // 添加混合模式
                    if(this.currentUserinfo.applications.length>1){
                        this.currentUserinfo.applications.push({
                            name:'混合模式',
                            id:'mixmode'
                        })
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

                    this.$set(this.buttonLoading,application.id,true)
                    setTimeout(()=>{
                        this.enter(application,false)
                    },200)
                }else {
                    this.$set(this.buttonLoading,application.id,true)
                    storage.set("currentApplication",application)
                    // 感觉遇到一个问题描述如下：
                    // 在portal项目中加载子项目导航项目，但portal导航到登录页面后Mfe组件页面后这里Mfe的被挂载的子项目的挂载点已经不存在了，（即使存在一个同样的id容器也不是以前的容器了）
                    // 所以子项目应该会加载不出来，暂时两种解决方法，一种是mfe组件页面加keepAlive包裹（这种方式不会触发子项目的生命周期钩子函数，只有一个berforeRouteUpdate方法会监测到路由的改变）,另一种是直接直接浏览器地址刷新重新加载
                    // 暂时用刷新的方式 todo
                     //this.$router.replace('/')
                    window.location.href=window.location.href.replace('selectApplication','')
                }

            }
        }
    }
</script>

<style scoped>

</style>