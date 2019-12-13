<template>
  <div role="mfe-mount-loading-container" class="g-width100 g-height100">
    <div role="mfe-mount-container" class="g-width100 g-height100" v-html="mfeRenderProps.appContent"></div>
  </div>
</template>

<script>

  // 子应用加载组件，在主应用中使用
let loadingInst = null
export default {
  name: 'MfeLoader',
  props: {
    renderKey:{ // 触发容器渲染的事件key，通过全局的vue事件总线派发
        type: String,
        required:true
    },
      ready:{
        type: Function
      }
  },
    created () {
        // window.mfe 前缀的属性或方法都是在portal项目设置的全局属性或方法，供微前端子项目使用
        if (window.mfe) {
            // 导航模块必须加载，且只加载一次
            window.mfe_vue_bus.$on(this.renderKey,this.$_mfeRender)
        }
    },
    mounted(){
      if( typeof this.ready == 'function' ){
          this.ready()
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
            // qiankun的render方法参数
            mfeRenderProps: {
                appContent: null,
                loading: false
            }
        }
    },
    destroyed() {
        // 卸载掉，为避免重复触发
        if (window.mfe) {
            window.mfe_vue_bus.$off(this.renderKey,this.$_mfeRender)
        }
    },
    watch: {
      // 监控loading 子应用状态
        'mfeRenderProps.loading' (val) {
            if(val === true) {
                loadingInst = this.$loading({
                    target: this.$el,
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
