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
            path: '/func',
            component: () => import('@/views/func/Func.vue')
        },
    ]
})

export default router