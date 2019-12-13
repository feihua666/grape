let packageName = require('./package.json').name
module.exports = {
    // 静态资源的路径，与路由无关
    publicPath: '/app-common-nav-public',
    devServer: {
        port: 9001,
        headers: {
            'Access-Control-Allow-Origin': '*'
        }
    },
    configureWebpack: {
        output:{
            library: packageName,
            libraryTarget: 'umd',
            jsonpFunction: `webpackJsonp_${packageName}`,
        }
    },
    chainWebpack: config => {
        config.devServer.set('inline', false)
        config.devServer.set('hot', false)
    },
    filenameHashing: false
}