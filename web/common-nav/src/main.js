import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.use(VueRouter);
const router = new VueRouter({mode: 'history'})
let containerId = 'app-common-nav'
let containerIdSelector = '#' + containerId
if (!window.mfe) {
    new Vue({
        router,
        render: h => h(App),
    }).$mount('#' + containerId)
}

let instance = null;

export async function bootstrap() {
}

export async function mount(props) {
    instance = new Vue({
        router,
        render: h => h(App),
    });
    // 如果dom存在直接挂载
    if(document.getElementById(containerId)){
        instance.$mount(containerIdSelector)
    }else {
        // 如果dom不存在延迟一秒再挂载，因为该项目依赖common-nav导航，确保导航项目已正确渲染
        setTimeout(function () {
            instance.$mount(containerIdSelector)
        },window.mfe_mount_delay)
    }
}

export async function unmount() {
    instance.$destroy();
    instance = null;
}

