import Vue from 'vue'
export function installMfe() {
// 设置微前端模式
    window.mfe = true
// 微前端事件总线
    window.mfe_vue_bus = new Vue()
    // 渲染应用的事件
    window.mfe_render_key = 'mfeRender'
    window.mfe_mount_delay = 10
}