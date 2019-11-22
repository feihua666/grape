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
      },
        // 选中的id
      checkedKeys: {
        type: Array,
          default: function () {
              return []
          }
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
        },
          // 记录所有已加载的主键
        loadedKeys:[],
        localCheckedKeys: this.checkedKeys
      }
    },
    mounted () {
        this.loadTreeData()
        if(this.localCheckedKeys.length > 0){
            this.setCheckedKeys(this.localCheckedKeys)
        }
    },
    methods: {
        isLeaf(data,node){
            return !data.hasChildren
        },
        getLabel(data,node){
            return data.node[this.label]
        },
        dataArrayToKeys(dataArray){
            return dataArray.map((item)=> item.node.id)
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
          this.$emit('lazyLoadingBegin', node.data)
          let params = {parentId: node.data.id}
          this.axios.get(this.dataUrl,{params:params})
              .then( (response)=> {
                  let content = response.data.data
                  this.loadedKeys = this.loadedKeys.concat(this.dataArrayToKeys(content))
                  resolve(content)
                  this.$emit('lazyLoadingEnd', node.data,content)
              })
              .catch( (error)=> {
                  resolve([])
                  this.$emit('lazyLoadingEnd', node.data,[])
                  if(error.response){
                      if(error.response.status != 404){
                          this.$message.error(error.response.data.msg)
                      }
                  }else{

                      this.$message.error('请求失败,未知错误')
                  }
              }).finally(()=>{

          })
      },
      // 加载树数据
      loadTreeData () {
        let self = this
        self.treeLoading = true
        let params = {}
        this.axios.get(this.dataUrl,{params:params})
          .then( (response)=> {
            let content = response.data.data
              this.loadedKeys = this.loadedKeys.concat(this.dataArrayToKeys(content))
            self.treeData = content
              // 如果没有一个父节点，默认展开
              if(content.length == 1 && content[0].hasChildren){
                  this.defaultExpandedKeys.push(content[0].node.id)
              }
          })
          .catch( (error)=> {
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
        getUncheckedKeys () {
            let checkedKeys = this.getCheckedKeys()
            return this.loadedKeys.filter((item)=>{
                return checkedKeys.indexOf(item) < 0
            })
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
        getAllUncheckedKeys () {
            let checkedKeys = this.getAllCheckedKeys()
            return this.loadedKeys.filter((item)=>{
                return checkedKeys.indexOf(item) < 0
            })
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
      },
      checkedKeys (val){
        this.localCheckedKeys = val
        this.setCheckedKeys(this.localCheckedKeys)
      },
      dataUrl (val){
          this.loadTreeData()
          if(this.localCheckedKeys.length > 0){
              this.setCheckedKeys(this.localCheckedKeys)
          }
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
