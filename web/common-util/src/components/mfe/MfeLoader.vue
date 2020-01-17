<template>
  <div role="mfe-mount-loading-container" class="g-width100 g-height100">
    <template v-for="(item ) in mfeRenderPropsArray">
      <div role="mfe-mount-container" :key="item.name" v-show="show(item)"  class="g-width100 g-height100" v-html="item.appContent"></div>
    </template>
  </div>
</template>

<script>
  /* mfeloader只适用于在该容器下一次只能渲染一个子应用,请确保activeRule不冲突 */
    import {start, runDefaultMountEffects,registerMicroApps } from 'qiankun'
    import {genActiveRule} from "./index"
  //import { getMountedApps, navigateToUrl,triggerAppChange,checkActivityFunctions,unloadApplication } from 'single-spa'

    // 子应用加载组件，在主应用中使用
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
    computed:{
      localAppListData(){
          let _appList = []
          let _hook = {
              beforeLoad: [],
              beforeMount: [],
              afterMount: [],
              afterUnmount: []
          }
          for(let i =0;i < this.appList.length; i++){
              let item = this.appList[i]
              // 支持String 类型
              if(typeof item.activeRule == 'string'){
                  item.activeRule = genActiveRule(item.activeRule)
              }
              item.render = (mfeRenderProps)=>{
                  mfeRenderProps.appName = item.appName
                  mfeRenderProps.name = item.name,
                  mfeRenderProps.activeRule = item.activeRule
                  this.$_mfeRender(mfeRenderProps)
              }

              _appList.push(item)
              _hook.beforeLoad.push(item.beforeLoad || ((app)=>{}))
              _hook.beforeMount.push(item.beforeMount || ((app)=>{}))
              _hook.afterMount.push(item.afterMount || ((app)=>{}))
              _hook.afterUnmount.push(((app)=>{
                  if(item.afterMount){
                      item.afterMount(app)
                  }
              }))
          }
          return {
              appList:_appList,
              hook:_hook
          }
      }
    },

    data () {
        return {
            loadingInst: null,
            // qiankun的render方法参数数组
            /*
             {
                appContent: null,
                loading: false,
                appName:null,
                name:null,
                active:false
            }
            */
            mfeRenderPropsArray: []
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
        // 参数属性与mfeRenderProps一致
        $_mfeRender (mfeRenderProps){
            if(this.mfeRenderPropsArray.length == 0){
                this.mfeRenderPropsArray.push(mfeRenderProps)
            }else {
                for(let i=0;i < this.mfeRenderPropsArray.length; i++){
                    let item = this.mfeRenderPropsArray[i]
                    if(item.name == mfeRenderProps.name){
                        this.mfeRenderPropsArray.splice(i,1,mfeRenderProps)
                        break
                    }
                }
            }

        },
        // 注册app
        registApps(){
            this.registAndStart(this.localAppListData)
        },
        registAndStart(localAppListData){
            registerMicroApps(
                localAppListData.appList,
                localAppListData.hook
            )
            start({ prefetch: false, jsSandbox: true })
        },
        show(item){
            return item.activeRule(window.location)
        }
    },
    beforeDestroy(){
    },
    destroyed() {

    },
    watch: {
      // 监控loading 子应用状态
        mfeRenderPropsArray (val) {
            for(let i=0;i < val.length; i++){
                let item = val[i]
                if(item.loading && !this.loadingInst){
                    this.loadingInst = this.$loading({
                        target: this.$el,
                        text: (item.appName || '应用') + '加载中...'
                    })
                    return
                }
            }
            if(this.loadingInst) {
                this.loadingInst.close()
                this.loadingInst = null
            }
        }
    }
}
</script>
