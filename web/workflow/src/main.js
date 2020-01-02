import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import axios from 'axios'
import VueAxios from 'vue-axios'
import router from './router/router'
import bus from 'common-util/src/components/bus/index.js'
import 'element-ui/lib/theme-chalk/index.css'
import 'common-util/src/css/global.css'
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

router.beforeEach((to, from, next) => {
    next()
})
Vue.use(VueAxios, axios)
// 该id定义在public/index.html中挂载app的容器标识
let containerId = 'app-workflow'

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
    instance.$mount('#' + containerId)
}

export async function unmount() {
    instance.$destroy();
    instance = null;
}
