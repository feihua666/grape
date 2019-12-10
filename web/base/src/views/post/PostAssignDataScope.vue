<template>
    <TreeForRelBind :checked-data-url="'/postdatascoperel/post/' + $route.params.postId"
                    data-url="/datascope/tree"
                    submit-url="/postdatascoperel/post/assign/datascope"
                    :main-value="$route.params.postId"
                    main-prop="postId"
                    checked-prop="checkedDataScopeIds"
                    unchecked-prop="uncheckedDataScopeIds"
                    :clear-all-url="'/postdatascoperel/post/' + $route.params.postId"
                    :lazyload="false"
    >
        <el-alert slot="alert"
                  title="每个数据对象只能选中一个数据范围"
                  type="warning" show-icon  :closable="false">
        </el-alert>
    </TreeForRelBind>
</template>

<script>
    import TreeForRelBind from 'common-util/src/components/page/TreeForRelBind.vue'
    export default {
        components:{
            TreeForRelBind
        },
        data (){
            return {
            }
        },
        mounted(){
        },
        methods:{
        }
    }
</script>

<style scoped>

</style>