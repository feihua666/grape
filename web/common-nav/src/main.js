import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import router from './router/router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import 'element-ui/lib/theme-chalk/index.css'
import 'common-util/src/css/global.css'
import axiosConfig from 'common-util/src/http/AxiosConfig.js'
Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(VueRouter)
axiosConfig(axios,Vue)
axios.defaults.baseURL = Vue.prototype.$baseURL.base

Vue.use(VueAxios, axios)
// 该id定义在public/index.html中挂载app的容器标识
let containerId = 'app-common-nav'

// 初始化微前端自定义设置

if (!window.mfe) {
    getInstance().$mount('#' + containerId)
}
let instance = null;
function getInstance() {
    let instance = new Vue({
        router,
        render: h => h(App),
        mounted () {
        }
    });
    return instance
}

// 微前端子应用需要导出
export async function bootstrap() {
}

export async function mount(props) {
    instance = getInstance()
    instance.$mount('#' + containerId)
}

export async function unmount() {
    if(instance != null){
        instance.$destroy();
        instance = null;
    }

}

