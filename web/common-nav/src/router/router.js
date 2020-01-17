import VueRouter from 'vue-router'
let base = '/common-nav'
if(window.mfe){
    base = (window.mfe_nav_router_base_path.portal || '') + base
    window.mfe_nav_router_base_path.commonNav = base
}
const router = new VueRouter({
    mode: 'history',
    base: base,
    routes: [
        // 默认是导航页面,导航页面下包括微前端，再研究下一个路由渲染两个组件页面 todo
        {
            path: '/*',
            //component: () => import('@/views/nav/Nav.vue')
            components:{
                topHeader: () => import('@/views/topheader/Header.vue'),
                leftAside: () => import('@/views/leftaside/Aside.vue')
            }
        }
    ]
})
// 路由守卫
router.beforeEach((to, from, next) => {
    next()
})
export default router