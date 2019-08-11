import * as singleSpa from 'single-spa'
import moduleConfig from './module-config'
// 全局控制开关，微前端模式
window.IS_IN_MFE = true

//数组判断 用于判断是否有多个url前缀
function isArray(o){
    return Object.prototype.toString.call(o)=='[object Array]';
}
// hash 模式,项目路由用的是hash模式会用到该函数
/*
let hashPrefix =  function (app) {
    return function (location) {
        let isShow = false
        //如果该应用 有多个需要匹配的路劲
        if(isArray(app.matchPath)){
            app.matchPath.forEach(path => {
                if(location.hash.startsWith(`#${path}`)){
                    isShow = true
                }
            });
        }
        // 普通情况
        else if(location.hash.startsWith(`#${app.matchPath || app.url}`)){
            isShow = true
        }
        return isShow;
    }
}
*/

// pushState 模式
let pathPrefix = function (app) {
    return function (location) {
        let isShow = true
        //如果该模块 有多个需要匹配的路径
        if(isArray(app.matchPath)){
            app.matchPath.forEach(path => {
                if(location.pathname.indexOf(`${path}`) === 0){
                    isShow = true
                }
            });
        }
        // 普通情况
        else if(location.pathname.indexOf(`${app.matchPath || app.url}`) === 0){
            isShow = true
        }
        return isShow;
    }
}

let loadCss = function (){

}
let loadJs = function (){
    let script = document.createElement('script')
    script.src = 'http://localhost:9001/app.js'
    document.body.appendChild(script)
    //import('E:\\git-source\\grape\\common\\common-nav-web\\src\\main.js')
    //import('E:\\git-source\\grape\\common\\common-nav-web\\dist\\js\\app.c3d40d94.js')

}

// vue 项目
moduleConfig.vueProject.forEach((item) => {
    singleSpa.registerApplication(
        item.module,
        () => {
            fetch(item.url);
            // const {cssUrls, jsUrls} = match(html, item.resourcePatterns);
            loadCss();
            loadJs();
            return window['mfe:' + item.module];
        },
        () => {
            return pathPrefix(item);
        }
    )
})
// 如果还有其它技术栈的spa项目将会在这里添加

// 启动
singleSpa.start()