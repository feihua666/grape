module.exports = {
    devServer: {
        port: 9010,
        proxy: {
            '/app-common-nav': {
                target:  'http://localhost:9001'
            },
            '/app-base': {
                target:  'http://localhost:9002'
            }
            ,
            '/api': {
                target:  'http://localhost:8080',
                pathRewrite:{
                    '^/api':''
                }
            }
        }
    }
}