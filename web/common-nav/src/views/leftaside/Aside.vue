<template>
    <el-scrollbar class="g-height100 scroll-bar-view" v-loading="funcDataLoading" style="background-color: #304156;" element-loading-background="#304156" wrapStyle="overflow:auto;">
        <el-menu
                :default-openeds="defaultActive"
                class="g-height100"
                background-color="#304156"
                text-color="#fff"
                active-text-color="#ffd04b"
        >
            <template v-for="(item,index) in funcData">
                <nav-sub-menu v-if="item.children && item.children.length > 0" :item="item" :key="index"></nav-sub-menu>
                <nav-menu-item v-else-if="item.node.typeDictCode == 'page'" :item="item" :key="index"></nav-menu-item>
            </template>
        </el-menu>
    </el-scrollbar>

</template>
<script>
    import NavMenuItem from './NavMenuItem.vue'
    import NavSubMenu from './NavSubMenu.vue'
    import storage from 'common-util/src/tools/StorageTools.js'
    export default {
        name: 'Aside',
        components: {
            NavMenuItem,
            NavSubMenu
        },
        computed:{
            // 默认激活的左侧导航菜单
            defaultActive(){
                if(this.funcData && this.funcData.length > 0){
                    return ['' + this.funcData[0].node.id]
                }
                return ['']
            }
        },
        data () {
            return {
                funcData: [],
                funcDataLoading: false,
                appList:[
                    // 导航项目
                    {
                        name: 'base',// 应用的标识，自定义
                        appName:'基础应用',// 该自定义属性主要用来加载子应用loading展示
                        entry: '//localhost:9002/base',// 子应用（导航项目）的首页地址，自动提取导致的子应用挂载函数，这个common-nav是随便写的好像不无关，暂时没弄明白
                        activeRule: '/base'  // 导航项目必须一直渲染不能卸载，否则导致其它子项目挂载不上

                    }
                ]
            }
        },
        mounted () {
            this.getFuncData()
        },
        updated(){
        },
        activated(){
        },
        beforeRouteEnter(to, from, next){
            next()
        },
        methods: {
            getFuncData(){
                this.funcDataLoading = true
                // 获取当前选中的应用，数据结构同后台应用vo
                let currentApplication = storage.get('currentApplication')
                this.axios.get('/func/tree/nav',{params:{
                    applicationId:currentApplication?currentApplication.id:null
                    }})
                    .then(res =>{
                        this.funcData = res.data.data
                    })
                    .catch(error =>{
                        if(error.response){
                            let status = error.response.status
                            this.$message.error('加载导航菜单出现错误：' + error.response.data.msg)
                        }

                    }).finally(()=>{
                    this.funcDataLoading = false
                })
            }
        },
        watch: {

        }
    }
</script>
<style scoped>
</style>
<style>
    .el-scrollbar__view{
        overflow: hidden;
    }
</style>