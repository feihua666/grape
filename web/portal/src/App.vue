<template>
  <div id="app-portal-container-loading" style="width: 100%;height: 100%;">
    <div id="app-portal-container" style="width: 100%;height: 100%;" v-html="mfeRenderProps.appContent"></div>
  </div>

</template>

<script>
let loadingInst = null
export default {
  name: 'app',
    created () {
      // window.mfe 前缀的属性或方法都是在portal项目设置的全局属性或方法，供微前端子项目使用
        if (window.mfe) {
            // 导航模块必须加载，且只加载一次
            window.mfe_vue_bus.$on(window.mfe_render_key + 'common',this.$_mfeRender)
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
            window.mfe_vue_bus.$off(window.mfe_render_key + 'common',this.$_mfeRender)
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

