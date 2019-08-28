import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

let containerId = 'app-base'
if (!window.mfe) {
    new Vue({
        render: h => h(App),
    }).$mount('#' + containerId)
}

let instance = null;

export async function bootstrap() {
    console.log(containerId + ' app bootstraped');
}

export async function mount(props) {
    console.log('props from main framework', props);
    console.log(containerId + ' app mount');

    instance = new Vue({
        el: '#' + containerId,
        render: h => h(App),
    });
}

export async function unmount() {
    instance.$destroy();
    instance = null;
    console.log( containerId + ' app unmount');
}
