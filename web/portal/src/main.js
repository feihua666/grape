
import { registerMicroApps, start, runDefaultMountEffects } from 'qiankun';
import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

// 设置微前端模式
window.mfe = true
// 微前端事件总线
window.mfe_vue_bus = new Vue()
window.mfe_render_key = 'mfeRender'

const portalIns = new Vue({
        el: '#app-portal',
        render: h => h(App),
        data: function(){
            return {
                appProps:{
                    content: '',
                    loading: false
                }
            }
        }
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
            name: 'base',
            entry: '//localhost:9002',
            render,
            activeRule: genActiveRule('/base')
        },
        {
            name: 'common-nav',
            entry: '//localhost:9001',
            render: renderCommonNav,
            activeRule: () => true
        }
    ],
    {
        beforeLoad: [app => {
            console.log('before load', app);
        }],
        beforeMount: [app => {
            console.log('before mount', app);
        }],
        afterMount: [app => {
            console.log('after mount', app);
        }],
        afterUnmount: [app => {
            console.log('after unload', app);
        }]
    }
);

runDefaultMountEffects('/common-nav');

start({ prefetch: false, jsSandbox: false });
