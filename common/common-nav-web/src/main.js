import './set-public-path'
import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import singleSpaVue from 'single-spa-vue'
import 'element-ui/lib/theme-chalk/index.css'
import ElementUI from 'element-ui'
Vue.config.productionTip = false;
Vue.use(ElementUI)
// 如果是微前端内，则不挂载
if(!window.mfe){
    new Vue({
        render: h => h(App),
    }).$mount('#app')
}
let singleAppEl = 'common-nav'
const vueLifecycles = singleSpaVue({
  Vue,
  appOptions: {
      el: '#' + singleAppEl,
    render: (h) => h(App),
    router,
  },
});

export const bootstrap = vueLifecycles.bootstrap;
export function mount(props) {
    createDomElement();
    return vueLifecycles.mount(props);
}
export const unmount = vueLifecycles.unmount;
function createDomElement() {
    // Make sure there is a div for us to render into
    let el = document.getElementById(singleAppEl);

    if (!el) {
        el = document.createElement('div');
        el.id = singleAppEl;
        document.body.appendChild(el);
    }
    return el;
}