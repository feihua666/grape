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
const portalIns = new Vue({
        el: '#app-portal',
        router,
        render: h => h(App),
        mounted () {
        }
    })

