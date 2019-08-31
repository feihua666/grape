<template>
  <div :id="loadingId" class="g-width100 g-height100">
    <div :id="containerId" class="g-width100 g-height100" v-html="mfeRenderProps.appContent"></div>
  </div>
</template>

<script>
let loadingInst = null
export default {
  name: 'MfeLoader',
  props: {
    loadingId: String, // 该容器用来显示加载动画
    containerId: String,  // 真正的容器
    renderKey:{ // 触发容器渲染的事件key，通过全局的vue事件总线派发
        type: String,
        default: window.mfe_render_key ? window.mfe_render_key : 'mfeRender'
    }
  },
    created () {
        // window.mfe 前缀的属性或方法都是在portal项目设置的全局属性或方法，供微前端子项目使用
        if (window.mfe) {
            // 导航模块必须加载，且只加载一次
            window.mfe_vue_bus.$on(this.renderKey,this.$_mfeRender)
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
            window.mfe_vue_bus.$off(this.renderKey,this.$_mfeRender)
        }
    },
    watch: {
        'mfeRenderProps.loading' (val) {
            if(val) {
                loadingInst = this.$loading({
                    target: '#' + this.loadingId,
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
