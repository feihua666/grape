import VueRouter from 'vue-router'


const router = new VueRouter({
    mode: 'history',
    routes: [
        // 默认是导航页面
        {
            path: '/*',
            component: () => import('@/views/nav/Nav.vue')
        }
    ]
})

export default router