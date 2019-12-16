<template>
  <div role="app-base-router-box" class="g-width100 g-height100">

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
      <router-view v-if="$route.meta.keepAlive === true"></router-view>
    </keep-alive>
    <router-view v-if="!$route.meta.keepAlive"></router-view>
  </div>
</template>

<script>

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
          if(this.breadcrumbs.length > 0){
              if('/' == from.path || from.meta.reset){
                  // 清空数组
                  this.breadcrumbs.splice(0)
              }else{
                  //如果去向路由是面包屑中的路径，则将面包屑后面的数据清除
                  let isInclude = false
                  for(let i = 0;i < this.breadcrumbs.length; i++){
                      if(to.path == this.breadcrumbs[i].path){
                          this.breadcrumbs.splice(i)
                          isInclude = true
                          break
                      }
                  }
                  if(!isInclude && to.meta.root){
                      this.breadcrumbs.splice(0)
                  }
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

