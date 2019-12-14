import VueRouter from 'vue-router'


const router = new VueRouter({
    mode: 'history',
    //base: '/common-nav',
    routes:[
        // 登录页面
        {
            path: '/login',
            component: () => import('@/views/login/Login.vue')
        },
        {
            path: '/selectApplication',
            component: () => import('@/views/application/selectApplication.vue')
        },
        {
            path: '/*',
            component: () => import('@/views/mfe/Mfe.vue')
        }
    ]
})

export default router