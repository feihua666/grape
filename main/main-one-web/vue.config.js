module.exports = {
    devServer: {
        port: 9000
    },
    chainWebpack: config => {
        config.module
            .rule('compile')
            .parser({
                system: false
            })
        config.devServer.set('inline', false)
        config.devServer.set('hot', false)
    },
    filenameHashing: false
}