<template>
  <div role="router-box" class="g-width100 g-height100">

    <el-breadcrumb separator="/" style="margin-bottom: 20px;">
      <el-breadcrumb-item>当前位置：</el-breadcrumb-item>
      <el-breadcrumb-item v-for="(item,index) in breadcrumbs" :key="index">
        <template v-if="index < breadcrumbs.length -1">
          <a :href="item.path" @click.prevent="navigate(item,index)">{{item.title}}</a>
        </template>
        <template v-else>{{item.title}}</template>
      </el-breadcrumb-item>
    </el-breadcrumb>
    <keep-alive>
      <router-view v-if="$route.meta && $route.meta.keepAlive === true"></router-view>
    </keep-alive>
    <router-view v-if="!$route.meta || !$route.meta.keepAlive"></router-view>
  </div>
</template>

<script>
    import storageTool from 'common-util/src/tools/StorageTools.js'

    export default {
  name: 'app',
    mounted() {
    },
    data(){
      return {
          // item={path:'',replace:Boolean,reset:Boolean,title:''}
          breadcrumbs:[]
      }
    },
    methods:{
      navigate(item){
          this.$route.meta.reset = !!item.reset
          this.$route.meta.replace = !!item.replace
          // 点击面包屑，设置目标路由缓存
          this.$route.meta.nextKeepalive = true
          if(item.replace){
              this.$router.replace(item.path)
          }else{
              this.$router.push(item.path)
          }
      }
    },
    watch:{
      $route(to,from){
          if('/' == from.path || from.meta.reset){
              // 清空数组
              this.breadcrumbs.splice(0)
          }
          // 其它微前端项目中设置的需要重置面包屑数组，这里目前是导航项目点击导航菜单设置的
          let rootBreadcrumb = storageTool.get('rootBreadcrumb')
          if(rootBreadcrumb && rootBreadcrumb.path.indexOf(from.path) >=0){
              this.breadcrumbs.splice(0)
          }
          //如果去向路由是面包屑中的路径，则将面包屑后面的数据清除
          for(let i = 0;i < this.breadcrumbs.length; i++){
              if(to.path == this.breadcrumbs[i].path){
                  this.breadcrumbs.splice(i)
                  break
              }
          }
          //暂时没什么作用以后研究 todo
          // 缓存判断
          if(from.meta.nextKeepalive){
              to.meta.keepalive = true
              from.meta.nextKeepalive = false
          }
          this.breadcrumbs.push(
              {
                  path:to.path,
                  title:to.meta.title,
                  replace: false,//点击面包屑的时候是路由push还是replace
                  reset:false// 面包屑数据是否清空重置，一般点击一个根路由时设置为重置
              }
          )

      }
    }
}
</script>

