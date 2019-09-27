import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'common-util/src/css/global.css'
import {start, runDefaultMountEffects } from 'qiankun';
import {installMfe} from 'common-util/src/components/mfe/index'
import registApps from './appRegister'

Vue.config.productionTip = false
Vue.use(ElementUI);
// 初始化微前端自定义设置
installMfe()

const portalIns = new Vue({
        el: '#app-portal',
        render: h => h(App),
        mounted () {
            // 注册应用
            registApps()
            //runDefaultMountEffects('/common-nav');
            start({ prefetch: false, jsSandbox: true });
        }
    });

