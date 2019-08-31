<template>
  <el-container class="width100 height100">
      <el-aside width="200px">
          <NavMenu/>
      </el-aside>
      <el-container>
          <el-header>Header</el-header>
        <el-container>
          <el-main>
              <div id="app-common-nav-container-loading" class="width100 height100">
              <div id="app-common-nav-container" class="width100 height100" v-html="mfeRenderProps.appContent"></div>
              </div>
          </el-main>
        </el-container>
      </el-container>
    </el-container>
</template>
<script>
    import NavMenu from './components/NavMenu'
    let loadingInst = null
    export default {
        name: 'app',
        components: {
            NavMenu
        },
        created () {
            // window.mfe 前缀的属性或方法都是在portal项目设置的全局属性或方法，供微前端子项目使用
            if (window.mfe) {
                // 导航模块必须加载，且只加载一次
                window.mfe_vue_bus.$on(window.mfe_render_key,this.$_mfeRender)
            }
        },
        methods: {
            $_mfeRender (mfeRenderProps){
                this.mfeRenderProps.appContent = mfeRenderProps.appContent
                this.mfeRenderProps.loading = mfeRenderProps.loading
            }
        },
        data () {
            return {
                mfeRenderProps: {
                    appContent: null,
                    loading: false
                }
            }
        },
        destroyed() {
            // 卸载掉，为避免重复触发
            if (window.mfe) {
                window.mfe_vue_bus.$off(window.mfe_render_key,this.$_mfeRender)
            }
        },
        watch: {
            'mfeRenderProps.loading' (val) {
                if(val) {
                    loadingInst = this.$loading({
                        target: '#app-common-nav-container-loading',
                        text: '应用加载中...'
                    })
                }else {
                    if(loadingInst) {
                        loadingInst.close()
                    }
                }
            }
        }
    }
</script>
<style scoped>
</style>
<!-- 全局样式 -->
<style>
  html,body{
    height:100% !important;
    margin: 0;
    padding: 0;
  }
  .width100{
    width: 100%;
  }
  .height100{
    height: 100%;
  }
</style>