import VueRouter from 'vue-router'

let base = '/portal'
if(window.mfe){
    window.mfe_nav_router_base_path.portal = base
}
const router = new VueRouter({
    mode: 'history',
    base: base,
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
            component: () => import('@/views/mfe/Mfe.vue'),
            meta:{
                keepAlive: true // 加载子应用的路由必须缓存否则子应用挂载的 dom会丢失
            }
        }
    ]
})
// 路由守卫
router.beforeEach((to, from, next) => {
    next()
})
export default router