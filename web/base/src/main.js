import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import axios from 'axios'
import VueAxios from 'vue-axios'
import router from './router/router'
import {tryMount} from "common-util/src/components/mfe"
import bus from 'common-util/src/components/bus/index.js'
import 'element-ui/lib/theme-chalk/index.css'
Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.prototype.$bus = Vue.prototype.$bus || bus   // 注册一个全局的总线组件
axios.defaults.baseURL = '/api'
// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    return response
}, function (error) {
    // 对响应错误做点什么
    if (!error.response) {
        console.log(error)
    }
    return Promise.reject(error)
})
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
