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
            path: '/user/assignRole/:userId',
            component: () => import('@/views/user/UserAssignRole.vue')
        },
        {
            path: '/user/userPost/:userId',
            component: () => import('@/views/user/userpost/UserPost.vue')
        },
        {
            path: '/user/userPost/userPostAdd/:userId',
            component: () => import('@/views/user/userpost/UserPostAdd.vue')
        },
        {
            path: '/user/userPost/UserPostAssignRole/:userPostId',
            component: () => import('@/views/user/userpost/UserPostAssignRole.vue')
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
        }
    ]
})

export default router