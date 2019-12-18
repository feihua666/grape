<template>
    <TreeForRelBind :checked-data-url="'/userpostrolerel/userpost/' + $route.params.userPostId"
                    data-url="/role/tree"
                    submit-url="/userpostrolerel/userpost/assign/role"
                    :main-value="$route.params.userPostId"
                    main-prop="userPostId"
                    checked-prop="checkedRoleIds"
                    unchecked-prop="uncheckedRoleIds"
                    :clear-all-url="'/userpostrolerel/userpost/' + $route.params.userPostId"
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