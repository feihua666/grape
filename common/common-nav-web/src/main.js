import './set-public-path'
import Vue from 'vue'
import App from './App.vue'
import singleSpaVue from 'single-spa-vue'

Vue.config.productionTip = false
if(!window.IS_IN_MFE){
    new Vue({
        render: h => h(App),
    }).$mount('#app')
}

const vueLifecycles = singleSpaVue({
    Vue,
    appOptions: {
        el: '#app',
        render: r => r(App)
    }
})

export const bootstrap = [
    vueLifecycles.bootstrap
]

export const mount = [
    vueLifecycles.mount
]

export const unmount = [
    vueLifecycles.unmount
]