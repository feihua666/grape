<template>
    <TreeForRelBind :checked-data-url="'/userpostfuncrel/userpost/' + $route.params.userPostId"
                    data-url="/func/tree"
                    submit-url="/userpostfuncrel/userpost/assign/func"
                    :main-value="$route.params.userPostId"
                    main-prop="userPostId"
                    checked-prop="checkedFuncId"
                    :clear-all-url="'/userpostfuncrel/userpost/' + $route.params.userPostId"
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