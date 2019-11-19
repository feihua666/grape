<template>
    <TreeForRelBind :checked-data-url="'/rolefuncrel/role/' + $route.params.id"
                    data-url="/func/tree"
                    submit-url="/rolefuncrel/role/assign/func"
                    :main-value="$route.params.id"
                    main-prop="roleId"
                    checked-prop="checkedFuncIds"
                    unchecked-prop="uncheckedFuncIds"
                    :clear-all-url="'/rolefuncrel/role/' + $route.params.id"
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