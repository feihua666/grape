import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import router from './router/router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import 'element-ui/lib/theme-chalk/index.css'
import 'common-util/src/css/global.css'
import {installMfe} from 'common-util/src/components/mfe/index'

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

    return Promise.reject(error)
})
// 路由守卫
router.beforeEach((to, from, next) => {
    next()
})
Vue.use(VueAxios, axios)
const portalIns = new Vue({
        el: '#app-portal',
        router,
        render: h => h(App),
        mounted () {
        }
    })

