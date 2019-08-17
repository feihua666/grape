/* eslint-disable */
//import Vue from 'vue'
//import VueRouter from 'vue-router'
import singleSpaConfig from './singleSpa'
import * as singleSpa from 'single-spa'
import 'import-map-overrides'
import 'systemjs/dist/system.min'
import 'systemjs/dist/extras/amd.min'
import 'systemjs/dist/extras/named-exports.min'
import 'systemjs/dist/extras/named-register.min'
import 'systemjs/dist/extras/use-default.min'
window.mfe = true
let ssc = singleSpaConfig.concat([{
    name: 'single-spa',
    app: false,
    url: 'https://cdnjs.cloudflare.com/ajax/libs/single-spa/4.3.7/system/single-spa.min.js'
},{
    name: 'vue',
    app: false,
    url: 'https://cdn.jsdelivr.net/npm/vue@2.6.10/dist/vue.js'
},{
    name: 'vue-router',
    app: false,
    url: 'https://cdn.jsdelivr.net/npm/vue-router@3.0.7/dist/vue-router.min.js'
}
])

let mapStr = {imports:{}}
ssc.forEach(item => {
    mapStr.imports[item.name] = item.url
})
const im = document.createElement('script');
im.type = 'systemjs-importmap';
im.textContent = JSON.stringify(mapStr)
document.body.append(im)


let originalResolve = System.resolve
let moduleMap = {}
// 手动注入组件

//System.set('https://cdn.jsdelivr.net/npm/vue@2.6.10/dist/vue.js', {Vue: Vue})
//System.set('https://cdn.jsdelivr.net/npm/vue-router@3.0.7/dist/vue-router.min.js',{VueRouter: VueRouter})

System.resolve = function(name) {
    return originalResolve.apply(this, arguments).then(resolved => {
        moduleMap[name] = resolved
        return resolved
    });
}
window.getPublicPath = function(name) {
    const url = moduleMap[name]
    if (url) {
        let index = url.lastIndexOf('/js')
        if (index < 0) {
            index = url.lastIndexOf('/')
        }
        index++
        return url.slice(0, index);
    } else {
        throw Error(`Could not find url for module '${name}'`)
    }
}

Promise.all([System.import('vue'),System.import('vue-router')]).then(function (modules) {

    let Vue = modules[0]
    let VueRouter = modules[1]
    Vue.use(VueRouter)

    ssc.forEach(item => {
        if (item.app !== false) {
            if (item.nav) {
                singleSpa.registerApplication(
                    item.name,
                    () => System.import(item.name),
                    location => true
                )
            }else{
                singleSpa.registerApplication(
                    item.name,
                    () => System.import(item.name),
                    location => location.pathname.startsWith('/' + item.name)
                )
            }

        }

    })
    singleSpa.start()
})


