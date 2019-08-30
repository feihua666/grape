
import { registerMicroApps, start, runDefaultMountEffects } from 'qiankun';
import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.config.productionTip = false
Vue.use(ElementUI);
// 设置微前端模式
window.mfe = true
// 微前端事件总线
window.mfe_vue_bus = new Vue()
window.mfe_render_key = 'mfeRender'
window.mfe_mount_delay = 1000

const portalIns = new Vue({
        el: '#app-portal',
        render: h => h(App)
    });

function render(renderProps) {
    // renderProps 只包含两个属性{appContent,loading}
    window.mfe_vue_bus.$emit(window.mfe_render_key,renderProps)
}
function renderCommonNav(renderProps) {
    // renderProps 只包含两个属性{appContent,loading}
    window.mfe_vue_bus.$emit(window.mfe_render_key + 'common',renderProps)
}
function genActiveRule(routerPrefix) {
    return (location) => location.pathname.startsWith(routerPrefix);
}

registerMicroApps(
    [
        {
            name: 'common-nav',
            entry: '//localhost:9001',
            render: renderCommonNav,
            activeRule: () => true  // 导航项目必须一直渲染不能卸载，否则导致其它子项目挂载不上
        },
        {
            name: 'base',
            entry: '//localhost:9002',
            render,
            activeRule: genActiveRule('/base'),
            props: {
                // 自定义方法在应用千载的时候将dom渲染为空，因为应该在destroy的时候不会清除dom
                removeDomAfterUnmount: () => {render({appContent: null,loading: false})}
            }
        },
    ],
    {
        beforeLoad: [app => {
        }],
        beforeMount: [app => {
        }],
        afterMount: [app => {
        }],
        afterUnmount: [app => {
            if (app.props.removeDomAfterUnmount) {
                app.props.removeDomAfterUnmount()
            }
        }]
    }
);

//runDefaultMountEffects('/common-nav');

start({ prefetch: true, jsSandbox: true });
