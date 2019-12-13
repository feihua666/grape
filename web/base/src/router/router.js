import VueRouter from 'vue-router'
let base = '/base'
if(window.mfe){
    base = window.mfe_nav_router_base_path + base
}

const router = new VueRouter({
    mode: 'history',
    base: base,
    routes: [
        {
            path: '/dict',
            component: () => import('@/views/dict/Dict.vue')
        },
        {
            path: '/dict/dictAdd',
            component: () => import('@/views/dict/DictAdd.vue')
        },
        {
            path: '/dict/dictEdit/:dictId',
            component: () => import('@/views/dict/DictEdit.vue')
        },
        {
            path: '/func',
            component: () => import('@/views/func/Func.vue')
        },
        {
            path: '/func/funcAdd',
            component: () => import('@/views/func/FuncAdd.vue')
        },
        {
            path: '/func/funcEdit/:funcId',
            component: () => import('@/views/func/FuncEdit.vue')
        },
        {
            path: '/func/assignRole/:funcId',
            component: () => import('@/views/func/FuncAssignRole.vue')
        },
        {
            path: '/comp',
            component: () => import('@/views/comp/Comp.vue')
        },
        {
            path: '/comp/compAdd',
            component: () => import('@/views/comp/CompAdd.vue')
        },
        {
            path: '/comp/compEdit/:compId',
            component: () => import('@/views/comp/CompEdit.vue')
        },
        {
            path: '/dept',
            component: () => import('@/views/dept/Dept.vue')
        },
        {
            path: '/dept/deptAdd',
            component: () => import('@/views/dept/DeptAdd.vue')
        },
        {
            path: '/dept/deptEdit/:deptId',
            component: () => import('@/views/dept/DeptEdit.vue')
        },
        {
            path: '/user',
            component: () => import('@/views/user/User.vue')
        },
        {
            path: '/user/userAdd',
            component: () => import('@/views/user/UserAdd.vue')
        },
        {
            path: '/user/userEdit/:userId',
            component: () => import('@/views/user/UserEdit.vue')
        },
        {
            path: '/user/updatePwd',
            component: () => import('@/views/user/UpdatePwd.vue')
        },
        {
            path: '/user/userinfo/current',
            component: () => import('@/views/user/Userinfo.vue')
        },
        {
            path: '/user/assignRole/:userId',
            component: () => import('@/views/user/UserAssignRole.vue')
        },
        {
            path: '/user/userPost/:userId',
            component: () => import('@/views/user/userpost/UserPost.vue')
        },
        {
            path: '/user/userPost',
            component: () => import('@/views/user/userpost/UserPost.vue')
        },
        {
            path: '/user/userPost/userPostAdd/:userId',
            component: () => import('@/views/user/userpost/UserPostAdd.vue')
        },
        {
            path: '/user/userPost/userPostAssignRole/:userPostId',
            component: () => import('@/views/user/userpost/UserPostAssignRole.vue')
        },
        {
            path: '/user/userPost/userPostAssignDataScope/:userPostId',
            component: () => import('@/views/user/userpost/UserPostAssignDataScope.vue')
        },
        {
            path: '/user/userIdentifier/:userId',
            component: () => import('@/views/user/useridentifier/UserIdentifier.vue')
        },
        {
            path: '/user/userIdentifier/userIdentifierAdd/:userId',
            component: () => import('@/views/user/useridentifier/UserIdentifierAdd.vue')
        },
        {
            path: '/user/UserAssignDataScope/:userId',
            component: () => import('@/views/user/UserAssignDataScope.vue')
        },
        {
            path: '/role',
            component: () => import('@/views/role/Role.vue')
        },
        {
            path: '/role/roleAdd',
            component: () => import('@/views/role/RoleAdd.vue')
        },
        {
            path: '/role/roleEdit/:roleId',
            component: () => import('@/views/role/RoleEdit.vue')
        },
        {
            path: '/role/assignFunc/:roleId',
            component: () => import('@/views/role/RoleAssignFunc.vue')
        },
        {
            path: '/role/assignUser/:roleId',
            component: () => import('@/views/role/RoleAssignUser.vue')
        },
        {
            path: '/role/RoleAssignDataScope/:roleId',
            component: () => import('@/views/role/RoleAssignDataScope.vue')
        },
        {
            path: '/post',
            component: () => import('@/views/post/Post.vue')
        },
        {
            path: '/post/postAdd',
            component: () => import('@/views/post/PostAdd.vue')
        },
        {
            path: '/post/postEdit/:postId',
            component: () => import('@/views/post/PostEdit.vue')
        },
        {
            path: '/post/PostAssignDataScope/:postId',
            component: () => import('@/views/post/PostAssignDataScope.vue')
        },
        // 职务
        {
            path: '/job',
            component: () => import('@/views/job/Job.vue')
        },
        {
            path: '/job/jobAdd',
            component: () => import('@/views/job/JobAdd.vue')
        },
        {
            path: '/job/jobEdit/:jobId',
            component: () => import('@/views/job/JobEdit.vue')
        },
        // 职级
        {
            path: '/jobLevel',
            component: () => import('@/views/joblevel/JobLevel.vue')
        },
        {
            path: '/jobLevel/jobLevelAdd',
            component: () => import('@/views/joblevel/JobLevelAdd.vue')
        },
        {
            path: '/jobLevel/jobLevelEdit/:jobLevelId',
            component: () => import('@/views/joblevel/JobLevelEdit.vue')
        },
        // 区域
        {
            path: '/area',
            component: () => import('@/views/area/Area.vue')
        },
        {
            path: '/area/areaAdd',
            component: () => import('@/views/area/AreaAdd.vue')
        },
        {
            path: '/area/areaEdit/:areaId',
            component: () => import('@/views/area/AreaEdit.vue')
        },
        // 参数配置
        {
            path: '/paramConfig',
            component: () => import('@/views/paramconfig/ParamConfig.vue')
        },
        {
            path: '/paramConfig/paramConfigAdd',
            component: () => import('@/views/paramconfig/ParamConfigAdd.vue')
        },
        {
            path: '/paramConfig/paramConfigEdit/:paramConfigId',
            component: () => import('@/views/paramconfig/ParamConfigEdit.vue')
        },
        // 数据约束
        {
            path: '/dataConstraint/dataObject',
            component: () => import('@/views/dataconstraint/dataobject/DataObject.vue')
        },
        {
            path: '/dataConstraint/dataObjectAdd',
            component: () => import('@/views/dataconstraint/dataobject/DataObjectAdd.vue')
        },
        {
            path: '/dataConstraint/dataObjectEdit/:dataObjectId',
            component: () => import('@/views/dataconstraint/dataobject/DataObjectEdit.vue')
        },
        {
            path: '/dataConstraint/dataScope',
            component: () => import('@/views/dataconstraint/datascope/DataScope.vue')
        },
        {
            path: '/dataConstraint/dataScopeAdd',
            component: () => import('@/views/dataconstraint/datascope/DataScopeAdd.vue')
        },
        {
            path: '/dataConstraint/dataScopeAdd/:dataObjectId',
            component: () => import('@/views/dataconstraint/datascope/DataScopeAdd.vue')
        },
        {
            path: '/dataConstraint/dataScopeEdit/:dataScopeId',
            component: () => import('@/views/dataconstraint/datascope/DataScopeEdit.vue')
        },
        {
            path: '/dataConstraint/dataScopeAssignDataTree/:dataScopeId',
            component: () => import('@/views/dataconstraint/datascope/DataScopeAssignDataTree.vue')
        },
        {
            path: '/dataConstraint/dataScopeAssignDataTable/:dataScopeId',
            component: () => import('@/views/dataconstraint/datascope/DataScopeAssignDataTable.vue')
        },
        // 应用
        {
            path: '/application',
            component: () => import('@/views/application/Application.vue')
        },
        {
            path: '/application/applicationAdd',
            component: () => import('@/views/application/ApplicationAdd.vue')
        },
        {
            path: '/application/applicationEdit/:applicationId',
            component: () => import('@/views/application/ApplicationEdit.vue')
        }
    ]
})

export default router