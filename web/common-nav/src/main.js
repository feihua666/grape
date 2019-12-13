import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import router from './router/router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import 'element-ui/lib/theme-chalk/index.css'
import {tryMount} from "common-util/src/components/mfe"
import {start, runDefaultMountEffects } from 'qiankun';
import {installMfe} from 'common-util/src/components/mfe/index'
import registApps from './appRegister'

Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(VueRouter)

// 初始化微前端自定义设置
installMfe()

axios.defaults.baseURL = '/api'
// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    return response
}, function (error) {
    // 对响应错误做点什么
    if (error.response) {
        // 本来想统一处理未登录情况跳转到登录页面，但这样可能有的接口需要自己处理，这样显的很乱，所以每个接口还是自己处理吧，不提供统一处理方法，维护起来功能单一，也好维护
        /*if(error.response.status == 401){
            if(window.mfe){
                window.mfe_vue_bus.$emit('login')
            }
        }*/
    }
    return Promise.reject(error)
})
router.beforeEach((to, from, next) => {
    next()
})
Vue.use(VueAxios, axios)
let containerId = 'app-common-nav'

if (!window.mfe) {
    getInstance().$mount('#' + containerId)
}

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
let instance = null;
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

