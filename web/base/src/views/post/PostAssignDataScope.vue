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