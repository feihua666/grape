<template>
    <TreeForRelBind :checked-data-url="'/userpostdatascoperel/userpost/' + $route.params.userPostId"
                    data-url="/datascope/tree"
                    submit-url="/userpostdatascoperel/userpost/assign/datascope"
                    :main-value="$route.params.userPostId"
                    main-prop="userPostId"
                    checked-prop="checkedDataScopeIds"
                    unchecked-prop="uncheckedDataScopeIds"
                    :clear-all-url="'/userpostdatascoperel/userpost/' + $route.params.userPostId"
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