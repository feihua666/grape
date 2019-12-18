<template>
    <TreeForRelBind :checked-data-url="'/postfuncrel/post/' + $route.params.postId"
                    data-url="/func/tree"
                    submit-url="/postfuncrel/post/assign/func"
                    :main-value="$route.params.postId"
                    main-prop="postId"
                    checked-prop="checkedFuncIds"
                    unchecked-prop="uncheckedFuncIds"
                    :clear-all-url="'/postfuncrel/post/' + $route.params.postId"
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