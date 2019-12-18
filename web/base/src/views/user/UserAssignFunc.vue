<template>
    <TreeForRelBind :checked-data-url="'/userfuncrel/user/' + $route.params.userId"
                    data-url="/func/tree"
                    submit-url="/userfuncrel/user/assign/func"
                    :main-value="$route.params.userId"
                    main-prop="userId"
                    checked-prop="checkedFuncIds"
                    unchecked-prop="uncheckedFuncIds"
                    :clear-all-url="'/userfuncrel/user/' + $route.params.userId"
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