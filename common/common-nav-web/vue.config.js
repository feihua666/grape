module.exports = {
    // publicPath: '/common-nav',
    devServer: {
        port: 9001
    },
    chainWebpack: config => {
        config.devServer.set('inline', false)
        config.devServer.set('hot', false)
        config.externals(['vue', 'vue-router'])
    },
    filenameHashing: false
}