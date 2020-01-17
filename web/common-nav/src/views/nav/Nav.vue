<template>
    <container type="top-header" class="g-width100 g-height100"
               inner-container-style="overflow:hidden;"
               header-style="padding:0"
               :force-render="{header:true}"
    >
        <router-view name="topHeader" slot="header"></router-view>
        <router-view name="leftAside" slot="aside"></router-view>
        <!-- 渲染子项目到中央区域 -->
        <mfe-loader
                slot="main"
                :app-list="appList"
                :auto-play="true"
        ></mfe-loader>
    </container>
</template>
<script>
    import MfeLoader from 'common-util/src/components/mfe/MfeLoader.vue'
    import Container from 'common-util/src/components/page/Container.vue'
    export default {
        name: 'Nav',
        components: {
            Container,
            MfeLoader
        },
        mounted (){
        },
        data () {
            return {
                appList:[
                    // 导航项目
                    {
                        name: 'base',// 应用的标识，自定义
                        appName:'基础应用',// 该自定义属性主要用来加载子应用loading展示
                        //entry: '//localhost:9002/base',// 子应用（导航项目）的首页地址，自动提取导致的子应用挂载函数，这个common-nav是随便写的好像不无关，暂时没弄明白
                        entry: 'http://yw.ngrok.ahbdz.com/app-base-public',// 子应用（导航项目）的首页地址，自动提取导致的子应用挂载函数，这个common-nav是随便写的好像不无关，暂时没弄明白
                        //activeRule: '/base'  // 导航项目必须一直渲染不能卸载，否则导致其它子项目挂载不上
                        // base  路径来自 base项目的路由配置
                        activeRule: (window.mfe_nav_router_base_path.commonNav || '') + '/base'

                    }
                ]
            }
        }
    }
</script>
<style scoped>
</style>
