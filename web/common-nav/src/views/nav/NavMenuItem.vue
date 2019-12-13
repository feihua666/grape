<template>
  <el-menu-item :index="index(item.node)" style="min-width: auto">
          <span slot="title">
              <i v-if="item.node.icon" :class="item.node.icon"></i>
            <router-link class="router-link" v-if="item.node.typeDictCode == 'page'" :to="{path: getUrl(item),params:{routeName:item.node.name}}">{{item.node.name}}</router-link>
            <template v-else>{{item.node.name}}</template>
          </span>
  </el-menu-item>
</template>

<script>
export default {
  name: 'NavMenuItem',
  props: {
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
      },
        getUrl(item){
          let url = item.node.url
            return url
        }
    },
    mounted() {
    }
}
</script>
<style scoped>
    .router-link{
        text-decoration:none;
        color: inherit;
    }
</style>
