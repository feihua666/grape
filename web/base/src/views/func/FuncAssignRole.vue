<template>
    <TreeForRelBind :checked-data-url="'/rolefuncrel/func/' + $route.params.id"
                    data-url="/role/tree"
                    submit-url="/rolefuncrel/func/assign/role"
                    :main-value="$route.params.id"
                    main-prop="funcId"
                    checked-prop="checkedRoleIds"
                    unchecked-prop="uncheckedRoleIds"
                    :clear-all-url="'/rolefuncrel/func/' + $route.params.id"
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