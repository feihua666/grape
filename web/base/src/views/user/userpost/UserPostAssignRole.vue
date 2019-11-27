<template>
    <TreeForRelBind :checked-data-url="'/userpostrolerel/userpost/' + $route.params.userPostId"
                    data-url="/role/tree"
                    submit-url="/userpostrolerel/userpost/assign/role"
                    :main-value="$route.params.userPostId"
                    main-prop="userPostId"
                    checked-prop="checkedRoleId"
                    :clear-all-url="'/userpostrolerel/userpost/' + $route.params.userPostId"
                    :form-format="formFormat"
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
            formFormat(form){
                // 因为角色只能选择一个，转换一下
                if(form.checkedRoleId && form.checkedRoleId.length > 0){
                    form.checkedRoleId = form.checkedRoleId[0]
                }
                return form
            }
        }
    }
</script>

<style scoped>

</style>