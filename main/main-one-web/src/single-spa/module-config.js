const vueResourcePatterns = ['/app.*.css$', '/vendor.*.css$', '/manifest.*.js$', '/vendor.*.js$', '/app.*.js$']

// vue 业务模块项目配置
const vueProject = [
    {
        // 导航模块'
        module:'commonNav',
        // URL 匹配模式
        matchPath: ['^/commonNav'],
        // 微前端地址
        url: 'http://localhost:9001/index.html',
        // 资源匹配模式
        resourcePatterns: vueResourcePatterns
    }
]
// 暂时只支持vue spa 技术栈
export default {
    vueProject: vueProject
}