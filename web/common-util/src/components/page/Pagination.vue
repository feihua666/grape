<template>
  <el-pagination
    background
    @size-change="pageSizeChange"
    @current-change="pageNoChange"
    :current-page="current"
    :page-sizes="[10, 20, 50, 100]"
    :page-size="pageSize"
    layout="total, sizes, prev, pager, next, jumper"
    :total="total"
    style="text-align: right;">
  </el-pagination>
</template>

<script>
  export default {
    name: 'Pagination',
    props: {
      current: {
          type: Number,
          default: 1
      },
      pageSize: {
          type: Number,
          default: 10
      },
        total: {
          type: Number,
          default: 0
      }
    },
    data () {
      return {
      }
    },
    mounted () {
    },
    methods: {
      // 每面大小改变重新查询数据
      pageSizeChange (val) {
        this.$emit('sizeChange', val)
      },
      // 页码改变加载对应页码数据
      pageNoChange (val) {
        this.$emit('currentChange', val)
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
