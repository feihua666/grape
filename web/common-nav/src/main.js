import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import {tryMount} from "common-util/src/components/mfe"

Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(VueRouter)
const router = new VueRouter({
    //base: '/common-nav',
    mode: 'history'
})
let containerId = 'app-common-nav'

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
let instance = null;

export async function bootstrap() {
}

export async function mount(props) {
    instance = getInstance()
    tryMount(containerId,instance)
}

export async function unmount() {
    instance.$destroy();
    instance = null;
}

