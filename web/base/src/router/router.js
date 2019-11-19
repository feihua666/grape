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
            path: '/dict/dictEdit/:id',
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
            path: '/func/funcEdit/:id',
            component: () => import('@/views/func/FuncEdit.vue')
        },
        {
            path: '/func/assignRole/:id',
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
            path: '/comp/compEdit/:id',
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
            path: '/dept/deptEdit/:id',
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
            path: '/user/userEdit/:id',
            component: () => import('@/views/user/UserEdit.vue')
        },
        {
            path: '/user/assignRole/:id',
            component: () => import('@/views/user/UserAssignRole.vue')
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
            path: '/role/roleEdit/:id',
            component: () => import('@/views/role/RoleEdit.vue')
        },
        {
            path: '/role/assignFunc/:id',
            component: () => import('@/views/role/RoleAssignFunc.vue')
        },
        {
            path: '/role/assignUser/:id',
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
            path: '/post/postEdit/:id',
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
            path: '/job/jobEdit/:id',
            component: () => import('@/views/job/JobEdit.vue')
        },
        {
            path: '/joblevel',
            component: () => import('@/views/joblevel/Joblevel.vue')
        },
        {
            path: '/joblevel/joblevelAdd',
            component: () => import('@/views/joblevel/JoblevelAdd.vue')
        },
        {
            path: '/joblevel/joblevelEdit/:id',
            component: () => import('@/views/joblevel/JoblevelEdit.vue')
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
            path: '/area/areaEdit/:id',
            component: () => import('@/views/area/AreaEdit.vue')
        },
        {
            path: '/paramconfig',
            component: () => import('@/views/paramconfig/Paramconfig.vue')
        },
        {
            path: '/paramconfig/paramconfigAdd',
            component: () => import('@/views/paramconfig/ParamconfigAdd.vue')
        },
        {
            path: '/paramconfig/paramconfigEdit/:id',
            component: () => import('@/views/paramconfig/ParamconfigEdit.vue')
        }
    ]
})

export default router