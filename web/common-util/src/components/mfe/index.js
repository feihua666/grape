import Vue from 'vue'
//自定义一些微前端属性
// 主应用中使用
export function installMfe() {
    if(window.mfe){
        return
    }
// 设置微前端模式
    window.mfe = true
// 微前端事件总线
    window.mfe_vue_bus = new Vue()
    window.mfe_mount_delay = 10
    // 默认导航项目路由的基础路径，这里加这个是因为暂时common-nav项目添加了路由，导致导航项目再加载三方项目时必须要以导航项目的路由基础路径开始，否则路由切换时不会渲染导航项目
    // 使用方式就是在子应用的路由中拼接上这个路径，作为路径的基础路径
    // 默认portal项目和导航项目都设置为根路径，这里默认空
    // 如果要修改该值，请在调用方法后再次赋值
    window.mfe_nav_router_base_path = ''
}
// 子应用激活的规则，当前只支持history模式
// 子应用中使用
export function genActiveRule(routerPrefix) {
    return (location) => location.pathname.startsWith(routerPrefix);
}