
<template>
    <el-dropdown class=" g-flex-center-v g-color-fff" style="padding: 0 5px;" @command="applicationSwitch"  :v-loading="userinfoLoading"  element-loading-spinner="el-icon-loading">
        <span class="g-pointer" title="切换应用"> {{currentApplication.name}}<i class="el-icon-arrow-down el-icon--right"></i></span>
        <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-for="(app,index) in currentUserinfo.applications" :command="app" :key="index"
                              :disabled="currentApplication.id == app.id"
            >{{app.name}}</el-dropdown-item>
        </el-dropdown-menu>
    </el-dropdown>
</template>


<script>
    import storage from 'common-util/src/tools/StorageTools.js'
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
                currentApplication: {}

            }
        },
        created(){
            // currentApplication在portal项目选择应用时设置和applicationSwitch方法中设置
            this.currentApplication = storage.get("currentApplication")
        },
        mounted(){
        },
        methods:{
            applicationSwitch(command){
                storage.set("currentApplication",command)
                window.location.reload()
            }
        }
    }
</script>

<style scoped>

</style>