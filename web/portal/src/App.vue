<template>
  <mfe-loader :loading-id="loadingId"
              :container-id="containerId"
              :render-key="renderKey"
  />
</template>

<script>
  import MfeLoader from 'common-util/src/components/mfe/MfeLoader'

export default {
  name: 'app',
    components: {MfeLoader},
    data () {
      return {
          loadingId: 'app-portal-container-loading',
          containerId: 'app-portal-container',
          renderKey: window.mfe_render_key + 'common'
      }
    }
}
</script>

