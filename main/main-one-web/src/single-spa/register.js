import * as singleSpa from 'single-spa'
import moduleConfig from './module-config'

// vue 项目
moduleConfig.vueProject.forEach((item,index) => {
    console.log(item)
    singleSpa.registerApplication(
        item.module,
        () => {
            const html = fetch(item.target);
            const {cssUrls, jsUrls} = match(html, item.resourcePatterns);
            loadCss(cssUrls);
            loadJs(jsUrls);
            return windows['mfe:' + item.module];
        },
        () => {
            return match(window.location.hash, item.matchUrlHash);
        }
    )
})
// 如果还有其它技术栈的spa项目将会在这里添加

// 启动
singleSpa.start()