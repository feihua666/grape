<template>
    <TreeForRelBind :checked-data-url="'/userrolerel/user/' + $route.params.userId"
                    data-url="/role/tree"
                    submit-url="/userrolerel/user/assign/role"
                    :main-value="$route.params.userId"
                    main-prop="userId"
                    checked-prop="checkedRoleIds"
                    unchecked-prop="uncheckedRoleIds"
                    :clear-all-url="'/userrolerel/user/' + $route.params.userId"
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