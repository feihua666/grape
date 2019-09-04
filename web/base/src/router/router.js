import VueRouter from 'vue-router'


const router = new VueRouter({
    mode: 'history',
    base: '/base',
    routes: [
        {
            path: '/dict',
            name: 'dict',
            component: () => import('@/views/dict/dict.vue')
        },
    ]
})

export default router