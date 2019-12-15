<template>
  <el-submenu :index="index(item.node)">
    <template slot="title">
      <i v-if="item.node.icon" :class="item.node.icon"></i>
      <span>{{item.node.name}}</span>
    </template>
    <template v-if="item.children && item.children.length > 0">
      <template v-for="(innerItem, index) in item.children">
        <nav-sub-menu v-if="innerItem.node.typeDictCode == 'menu' && innerItem.children && innerItem.children.length > 0 " :item="innerItem" :key="index"></nav-sub-menu>
        <nav-menu-item v-else-if="innerItem.node.typeDictCode == 'page'" :item="innerItem" :key="index"></nav-menu-item>
      </template>
    </template>
  </el-submenu>
</template>

<script>
    import NavMenuItem from './NavMenuItem.vue'
export default {
    name: 'NavSubMenu',
    components: {
        NavMenuItem
    },
    props: {
        funcDataLoading:{
            type: Boolean,
            default: false
        },
        item:{
          type: Object,
          default:function () {
              return {}
          }
      }
    },
    data(){
      return {

      }
    },
    methods:{
        index(item){
            return '' + item.id
        }
    },
    mounted() {
    }
}
</script>
