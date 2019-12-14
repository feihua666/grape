<template>
  <div role="mfe-mount-loading-container" class="g-width100 g-height100">
    <div role="mfe-mount-container" class="g-width100 g-height100" v-html="mfeRenderProps.appContent"></div>
  </div>
</template>

<script>
    import {start, runDefaultMountEffects,registerMicroApps } from 'qiankun'
    import {genActiveRule} from "./index"

    // 子应用加载组件，在主应用中使用
let loadingInst = null
export default {
  name: 'MfeLoader',
  props: {
      // item={name:'test',appName:'测试',entry:'http://localhost:8080/test',activeRule:Function}
      // 还可以添加beforeLoad beforeMount afterMount afterUnmount 回调属性
      appList:{
          type: Array,
          required: true
      },
      autoPlay:{
          type: Boolean,
          default: true
      }

  },
    created () {

    },
    mounted(){
      if(this.autoPlay){
          this.registApps()
      }

    },
    methods: {
      // 参数属性与this.mfeRenderProps一致
        $_mfeRender (mfeRenderProps){
            this.mfeRenderProps = mfeRenderProps

        },
        // 注册app
        registApps(){
            let _appList = []
            let _hook = {
                beforeLoad: [],
                beforeMount: [],
                afterMount: [],
                afterUnmount: []
            }
            for(let i =0;i < this.appList.length; i++){

                let item = this.appList[i]
                item.render = (mfeRenderProps)=>{
                    mfeRenderProps.appName = item.appName
                    mfeRenderProps.name = item.name
                    this.$_mfeRender(mfeRenderProps)
                }

                //
                if(typeof item.activeRule == 'string'){
                    item.activeRule = genActiveRule(window.mfe_nav_router_base_path + item.activeRule)
                }
                _appList.push(item)


                _hook.beforeLoad.push(item.beforeLoad || ((app)=>{}))
                _hook.beforeMount.push(item.beforeMount || ((app)=>{}))
                _hook.afterMount.push(item.afterMount || ((app)=>{}))
                _hook.afterUnmount.push(((app)=>{
                    if(item.afterMount){
                        item.afterMount(app)
                    }
                    // 自定义方法在应用卸载的时候将dom渲染为空，因为vue在destroy的时候不会清除dom，如果子项目不是vue请考虑具体情况
                    //this.mfeRenderProps.appContent = null
                }))

            }

            this.registAndStart(_appList,_hook)
        },
        registAndStart(appList,hook){
            registerMicroApps(
                appList,
                hook
            )
            start({ prefetch: false, jsSandbox: true })
        }
    },
    data () {
        return {
            // qiankun的render方法参数
            mfeRenderProps: {
                appContent: null,
                loading: false,
                appName:null,
                name:null
            }
        }
    },
    destroyed() {

    },
    watch: {
      // 监控loading 子应用状态
        'mfeRenderProps.loading' (val) {
            if(val === true) {
                loadingInst = this.$loading({
                    target: this.$el,
                    text: (this.mfeRenderProps.appName || '应用') + '加载中...'
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
