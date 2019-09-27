import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
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

