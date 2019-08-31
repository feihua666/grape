import Vue from 'vue'
//自定义一些微前端属性
export function installMfe() {
// 设置微前端模式
    window.mfe = true
// 微前端事件总线
    window.mfe_vue_bus = new Vue()
    // 渲染应用的默认事件
    window.mfe_render_key = 'mfeRender'
    window.mfe_mount_delay = 10
}
//渲染子应用的函数 renderKey vue事件总线的kdy，renderProps qiankun 注册应用的render方法的调用参数
export function render(renderProps,renderKey) {

    if (window.mfe_vue_bus) {
        window.mfe_vue_bus.$emit(renderKey ? renderKey : window.mfe_render_key,renderProps)
    }else {
        console.error('没有安装自定义属性，请确定 installMfe 方法已调用')
    }
}
// 子应用激活的规则，当前只支持history模式
export function genActiveRule(routerPrefix) {
    return (location) => location.pathname.startsWith(routerPrefix);
}