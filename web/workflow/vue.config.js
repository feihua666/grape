let packageName = require('./package.json').name
module.exports = {
    publicPath: '/app-workflow-public',
    devServer: {
        port: 9003,
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