<template>
  <el-container class="width100 height100">
      <el-header>Header</el-header>
      <el-container>
        <el-aside width="200px">Aside</el-aside>
        <el-container>
          <el-main>Main</el-main>
        </el-container>
      </el-container>
    </el-container>
</template>
<script>
    export default {
        name: 'app'
    }
</script>
<style scoped>
</style>
<style>

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