import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import router from './router/router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import 'element-ui/lib/theme-chalk/index.css'
import 'common-util/src/css/global.css'
import {tryMount} from "common-util/src/components/mfe"
import {start, runDefaultMountEffects } from 'qiankun';
import {installMfe} from 'common-util/src/components/mfe/index'
import registApps from './appRegister'

Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(VueRouter)

axios.defaults.baseURL = '/api'
// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    return response
}, function (error) {

    return Promise.reject(error)
})
router.beforeEach((to, from, next) => {
    next()
})
Vue.use(VueAxios, axios)
// 该id定义在public/index.html中挂载app的容器标识
let containerId = 'app-common-nav'



// 初始化微前端自定义设置

if (!window.mfe) {
    getInstance().$mount('#' + containerId)
}
installMfe()
let instance = null;
function getInstance() {
    let instance = new Vue({
        router,
        render: h => h(App),
        mounted () {
            // 注册应用
            registApps()
            //runDefaultMountEffects('/common-nav');
            start({ prefetch: false, jsSandbox: true });
        }
    });
    return instance
}

// 微前端子应用需要导出
export async function bootstrap() {
}

export async function mount(props) {
    instance = getInstance()
    tryMount(containerId,instance)
}

export async function unmount() {
    if(instance != null){
        instance.$destroy();
        instance = null;
    }

}

