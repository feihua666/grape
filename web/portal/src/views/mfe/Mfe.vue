<template>
  <mfe-loader
              render-key="renderKey-portalRenderMfe"
              :ready="ready"
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
      }
    },
    methods:{
        // 在ready后调用
        ready() {
        }
    }
}
</script>

