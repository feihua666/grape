<template>
    <container type="top-header" class="g-width100 g-height100" inner-container-style="overflow:hidden;" header-style="padding:0">
        <Header slot="header">

        </Header>
        <el-scrollbar slot="aside" class="g-height100 scroll-bar-view" v-loading="funcDataLoading" element-loading-background="#304156" wrapStyle="overflow:auto;">
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
        <!-- 渲染子项目到中央区域 -->
        <mfe-loader
                slot="main"
                :app-list="appList"
                :auto-play="true"
        ></mfe-loader>
    </container>
</template>
<script>
    import NavMenuItem from './NavMenuItem.vue'
    import NavSubMenu from './NavSubMenu.vue'
    import Header from '../topheader/Header.vue'
    import MfeLoader from 'common-util/src/components/mfe/MfeLoader.vue'
    import Container from 'common-util/src/components/page/Container.vue'
    export default {
        name: 'Nav',
        components: {
            NavMenuItem,
            Header,
            NavSubMenu,
            Container,
            MfeLoader
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
        methods: {
            getFuncData(){
                this.funcDataLoading = true
                this.axios.get('/func/tree/nav')
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
