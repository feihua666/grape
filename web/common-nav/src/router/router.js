import VueRouter from 'vue-router'


const router = new VueRouter({
    mode: 'history',
    base: '/common-nav',
    routes: [
        {
            path: '/login',
            component: () => import('@/views/login/Login.vue')
        },
        // 导航页面本身
        {
            path: '/nav',
            component: () => import('@/views/nav/Nav.vue')
        },
        // 所有的子应用页面渲染在导航页面下
        {
            path: '/nav/**',
            meta: { routeName: '' },
            component: () => import('@/views/nav/Nav.vue')
        },
        // 默认是导航页面
        {
            path: '/',
            component: () => import('@/views/nav/Nav.vue')
        },
    ]
})

export default router