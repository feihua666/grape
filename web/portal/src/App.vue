<template>
  <div id="app-portal-container" style="width: 100%;height: 100%;" v-if="mfeRenderProps && mfeRenderProps.loading" v-html="mfeRenderProps.appContent"></div>
</template>

<script>
export default {
  name: 'app',
    created () {
        console.log('portal-app-created')
      // window.mfe 前缀的属性或方法都是在portal项目设置的全局属性或方法，供微前端子项目使用
        if (window.mfe) {
            // 导航模块必须加载，且只加载一次
            window.mfe_vue_bus.$once(window.mfe_render_key + 'common',this.$_mfeRender)
        }
    },
    methods: {
        $_mfeRender (mfeRenderProps){
            console.log('portal-$_mfeRender',mfeRenderProps)
            this.mfeRenderProps = mfeRenderProps
        }
    },
    data () {
      return {
          mfeRenderProps: null
      }
    }

}
</script>

