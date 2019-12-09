<template>
    <TreeForRelBind :checked-data-url="'/userdatascoperel/user/' + $route.params.userId"
                    data-url="/datascope/tree"
                    submit-url="/userdatascoperel/user/assign/datascope"
                    :main-value="$route.params.userId"
                    main-prop="userId"
                    checked-prop="checkedDataScopeIds"
                    unchecked-prop="uncheckedDataScopeIds"
                    :clear-all-url="'/userdatascoperel/user/' + $route.params.userId"
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