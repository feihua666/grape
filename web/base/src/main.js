import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import axios from 'axios'
import VueAxios from 'vue-axios'
import router from './router/router'
import {tryMount} from "common-util/src/components/mfe";

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(VueRouter)
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
