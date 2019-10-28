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
        }
    ]
})

export default router