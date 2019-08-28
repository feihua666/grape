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
if (!window.mfe) {
    new Vue({
        router,
        render: h => h(App),
    }).$mount('#' + containerId)
}

let instance = null;

export async function bootstrap() {
    console.log(containerId + ' app bootstraped');
}

export async function mount(props) {
    console.log('props from main framework', props);
    console.log(containerId + ' app mount');

    instance = new Vue({
        router,
        el: '#' + containerId,
        render: h => h(App),
    });
}

export async function unmount() {
    instance.$destroy();
    instance = null;
    console.log( containerId + ' app unmount');
}

