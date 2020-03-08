<template>
  <mfe-loader
              :app-list="appList"
  />
</template>

<script>
  import MfeLoader from 'common-util/src/components/mfe/MfeLoader'
  // 该组件只用来挂载子应用
export default {
    components: {MfeLoader},
    mounted(){
    },
    data () {
      return {
          appList:[
              // 导航项目
              {
                  name: 'common-nav',// 应用的标识，自定义
                  appName:'导航应用',// 该自定义属性主要用来加载子应用loading展示
                  //entry: '//http://yw.ngrok.ahbdz.com:9001/common-nav',// 子应用（导航项目）的首页地址，自动提取导致的子应用挂载函数，这个common-nav是随便写的好像不无关，暂时没弄明白
                  entry: 'http://localhost:9001/app-common-nav-public',// 子应用（导航项目）的首页地址，自动提取导致的子应用挂载函数，这个common-nav是随便写的好像不无关，暂时没弄明白
                 // afterMount:(app)=>{console.log('app',app)},
                  activeRule: '/'  // 导航项目必须一直渲染不能卸载，否则导致其它子项目挂载不上
                  // common-nav  路径来自 common-nav项目的路由配置
                  //activeRule: (window.mfe_nav_router_base_path.portal || '') + '/common-nav'
              }
          ]
      }
    },
    methods:{
    }
}
</script>

