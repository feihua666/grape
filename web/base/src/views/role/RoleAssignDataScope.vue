<template>
    <TreeForRelBind :checked-data-url="'/roledatascoperel/role/' + $route.params.roleId"
                    data-url="/datascope/tree"
                    submit-url="/roledatascoperel/role/assign/datascope"
                    :main-value="$route.params.roleId"
                    main-prop="roleId"
                    checked-prop="checkedDataScopeIds"
                    unchecked-prop="uncheckedDataScopeIds"
                    :clear-all-url="'/roledatascoperel/role/' + $route.params.roleId"
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