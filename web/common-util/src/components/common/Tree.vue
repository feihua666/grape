<template>
<div>
  <el-input
    placeholder="输入关键字进行过滤"
    v-model="treeFilterText" clearable>
    <el-button slot="append" icon="el-icon-refresh" @click="treeRefreshBtnClick"></el-button>
  </el-input>
  <el-tree v-loading="treeLoading"
           :data="treeData"
           :props="props"
           @node-click="treeNodeClick"
           :filter-node-method="treeFilterNode"
           ref="tree"
           node-key="id"
           lazy
           :load="loadChildren"
           :show-checkbox="showCheckbox"
           :check-strictly="checkStrictly"
           :expand-on-click-node="false" :default-expanded-keys="defaultExpandedKeys">

  </el-tree>
</div>
</template>

<script>
  export default {
    name: 'Tree',
    props: {
      // 在显示复选框的情况下，是否严格的遵循父子不互相关联的做法，默认为 false
      checkStrictly: {
        default: false
      },
      showCheckbox: {
          type: Boolean,
        default: false
      },
      dataUrl:{
        type: String,
        required:true
      },
        label:{
          type: String,
            default: 'name'
        }
    },
    data () {
      return {
        defaultExpandedKeys: [],
        // 左边树搜索文本
        treeFilterText: '',
        treeData: null,
        treeLoading: false,
        // 树属性，关键字属性
        props: {
          children: 'children',
          label: this.getLabel,
          isLeaf: this.isLeaf
        }
      }
    },
    mounted () {
        this.loadTreeData()
    },
    methods: {
        isLeaf(data,node){
            return !data.hasChildren
        },
        getLabel(data,node){
            return data.node[this.label]
        },
      // 树过滤匹配
      treeFilterNode (value, data) {
        if (!value) return true
        return data.node[this.label].indexOf(value) !== -1
      },
      // 点击树节点事件
      treeNodeClick (data) {
        this.$emit('nodeClick', data)
          // 扩展事件，
        this.$emit('nodeClickExt', {
            data: data,
            // 关键信息
            key:{
                id:data.id,
                label:data.node[this.label]
            }})
      },
      // 刷新按钮，重新加载树数据
      treeRefreshBtnClick () {
        this.loadTreeData()
      },
        loadChildren(node,resolve){
            if(!node.data){
                return
            }
            let params = {parentId: node.data.id}
            this.axios.get(this.dataUrl,{params:params})
                .then(function (response) {
                    let content = response.data.data
                    resolve(content)
                })
                .catch(function (error) {
                    resolve([])
                    if(error.response){
                        if(error.response.status != 404){
                            this.$message.error(error.response.data.msg)
                        }
                    }else{
                        self.$message.error('请求失败,未知错误')
                    }
                })
        },
      // 加载树数据
      loadTreeData () {
        let self = this
        self.treeLoading = true
          let params = {}
        this.axios.get(this.dataUrl,{params:params})
          .then(function (response) {
            let content = response.data.data
            self.treeData = content
          })
          .catch(function (error) {
              self.treeData = []
              if(error.response){
                  if(error.response.status != 404){
                      this.$message.error(error.response.data.msg)
                  }
              }else{
                  self.$message.error('请求失败,未知错误')
              }
          }).finally(()=>{
            self.treeLoading = false
        })
      },
      getCheckedNodes () {
        return this.$refs.tree.getCheckedNodes()
      },
      getCheckedKeys () {
        return this.$refs.tree.getCheckedKeys()
      },
      setCheckedNodes (nodes) {
        this.$refs.tree.setCheckedNodes(nodes)
      },
      setCheckedKeys (keys) {
        this.$refs.tree.setCheckedKeys(keys)
      },
      // 半选中
      getHalfCheckedKeys () {
        return this.$refs.tree.getHalfCheckedKeys()
      },
      getAllCheckedKeys () {
        let checkedKeys = this.getCheckedKeys()
        let halfCheckedKeys = this.getHalfCheckedKeys()
        checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys)
        return checkedKeys
      }
    },
    watch: {
      // 左边树过滤筛选
      treeFilterText (val) {
        this.$refs.tree.filter(val)
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
