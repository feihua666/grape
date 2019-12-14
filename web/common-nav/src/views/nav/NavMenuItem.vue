<template>
  <el-menu-item :index="index(item.node)" style="min-width: auto">
          <span slot="title">
              <i v-if="item.node.icon" :class="item.node.icon"></i>
            <!--<router-link class="router-link" v-if="item.node.typeDictCode == 'page'" :to="{path: getUrl(item)}">{{item.node.name}}</router-link>-->
            <a :href="getUrl(item)" class="router-link" v-if="item.node.typeDictCode == 'page'" @click.prevent="nav(item)">{{item.node.name}}</a>
            <template v-else>{{item.node.name}}</template>
          </span>
  </el-menu-item>
</template>

<script>
    import storageTool from 'common-util/src/tools/StorageTools.js'
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
        },
        nav(item){
            storageTool.set("rootBreadcrumb",{
                path:this.$route.path
            })
            this.$router.push(this.getUrl(item))
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
