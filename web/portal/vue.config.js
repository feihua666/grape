module.exports = {
    devServer: {
        port: 9010,
        proxy: {
            // 子项目静态资源代理，以防止跨域
            '/app-common-nav-public': {
                target:  'http://localhost:9001'
            },
            // 子项目静态资源代理，以防止跨域
            '/app-base-public': {
                target:  'http://localhost:9002'
            },
            // 接口请求代理，，以防止跨域，api是在axios定义的时候设置的baseUrl参考main.js 中的axios.defaults.baseURL = '/api'
            '/api': {
                target:  'http://localhost:9100',
                pathRewrite:{
                    '^/api':''
                }
            }
        },
        disableHostCheck: true
    }
}