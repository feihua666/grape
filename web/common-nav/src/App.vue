<template>
  <el-container class="g-width100 g-height100">
      <el-aside width="200px">
          <NavMenu/>
      </el-aside>
      <el-container>
          <el-header>Header</el-header>
        <el-container>
          <el-main>
              <mfe-loader :loading-id="loadingId"
                          :container-id="containerId"
              />
          </el-main>
        </el-container>
      </el-container>
    </el-container>
</template>
<script>
    import NavMenu from './components/NavMenu'
    import MfeLoader from 'common-util/src/components/mfe/MfeLoader'

    export default {
        name: 'app',
        components: {
            NavMenu,
            MfeLoader
        },
        created () {

        },
        methods: {

        },
        data () {
            return {
                loadingId: 'app-common-nav-container-loading',
                containerId: 'app-common-nav-container'
            }
        },
        destroyed() {

        },
        watch: {

        }
    }
</script>
<style scoped>
</style>
