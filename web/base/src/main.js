import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import axios from 'axios'
import VueAxios from 'vue-axios'
import router from './router/router'
import {tryMount} from "common-util/src/components/mfe";
import bus from 'common-util/src/components/bus/index.js'
Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.prototype.$bus = Vue.prototype.$bus || bus   // 注册一个全局的总线组件
axios.defaults.baseURL = '/api'
Vue.use(VueAxios, axios)

let containerId = 'app-base'
if (!window.mfe) {
    getInstance().$mount('#' + containerId)
}



function getInstance() {
    let instance = new Vue({
        router,
        render: h => h(App),
    });
    return instance
}

export async function bootstrap() {
}
let instance = null;
export async function mount(props) {
    instance = getInstance()
    tryMount(containerId,instance)
}

export async function unmount() {
    instance.$destroy();
    instance = null;
}
