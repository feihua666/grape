import VueRouter from 'vue-router'


const router = new VueRouter({
    mode: 'history',
    routes: [
        // 默认是导航页面,导航页面下包括微前端，再研究下一个路由渲染两个组件页面 todo
        {
            path: '/*',
            component: () => import('@/views/nav/Nav.vue')
        }
    ]
})

export default router