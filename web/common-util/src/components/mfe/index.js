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
    // 渲染应用的默认事件
    window.mfe_render_key = 'mfeRender'
    window.mfe_mount_delay = 10
    // 默认导航项目路由的基础路径，这里加这个是因为暂时common-nav项目添加了路由，导致导航项目再加载三方项目时必须要以导航项目的路由基础路径开始，否则路由切换时不会渲染导航项目
    // 使用方式就是在子应用的路由中拼接上这个路径，作为路径的基础路径
    window.mfe_nav_router_base_path = '/common-nav/nav'
}
//渲染子应用的函数 renderKey vue事件总线的kdy，renderProps qiankun 注册应用的render方法的调用参数
// 主应用中使用
export function render(renderProps,renderKey) {

    if (window.mfe_vue_bus) {
        window.mfe_vue_bus.$emit(renderKey ? renderKey : window.mfe_render_key,renderProps)
    }else {
        new Error('没有安装自定义属性，请确定 installMfe 方法已调用')
    }
}
// 挂载应用，如果第一时间没有dom延迟再试
// 子应用中使用
export function tryMount(containerId,instance,times) {
    if(times == null || times == undefined){
        tryMount(containerId,instance,1)
    }else if (times >= 0){

        // 如果dom存在直接挂载
        if(document.getElementById(containerId)){
            instance.$mount('#' + containerId)
        }else {
            // 如果dom不存在延迟一秒再挂载，因为该项目依赖common-nav导航，确保导航项目已正确渲染
            setTimeout(function () {
                tryMount(containerId,instance,times - 1)
            },window.mfe_mount_delay)
        }
    }


}
// 子应用激活的规则，当前只支持history模式
// 子应用中使用
export function genActiveRule(routerPrefix) {
    return (location) => location.pathname.startsWith(routerPrefix);
}