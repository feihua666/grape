import VueRouter from 'vue-router'
let base = '/workflow'
if(window.mfe){
    base = window.mfe_nav_router_base_path + base
}

const router = new VueRouter({
    mode: 'history',
    base: base,
    routes: [

    ]
})

export default router