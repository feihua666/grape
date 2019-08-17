import './set-public-path'
import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import singleSpaVue from 'single-spa-vue'
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')


const vueLifecycles = singleSpaVue({
  Vue,
  appOptions: {
    render: (h) => h(App),
    router,
  },
});

export const bootstrap = vueLifecycles.bootstrap;
export const mount = vueLifecycles.mount;
export const unmount = vueLifecycles.unmount;
