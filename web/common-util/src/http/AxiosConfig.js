
import StorageTools from '../tools/StorageTools'
export default function axiosConfig(axios,vue){
    let baseUrl = process.env.NODE_ENV === 'development' ? '/api/' : '/'
    axios.defaults.baseURL = baseUrl
// 请求拦截器
    axios.interceptors.request.use(
        config => {
            if (StorageTools.get('token')) { // 判断是否存在token，如果存在的话，则每个http header都加上token
                config.headers.Authorization = StorageTools.get('token')  //请求头加上token
            }
            return config
        },
        err => {
            return Promise.reject(err)
        })
// 添加响应拦截器
    axios.interceptors.response.use(function (response) {
        // 对响应数据做点什么
        return response
    }, function (error) {
        // 对响应错误做点什么
        if (!error.response) {
            console.log(error)
        }
        return Promise.reject(error)
    })
    vue.prototype.$baseURL = {
        base: baseUrl + 'base/',
        auth: baseUrl + 'auth/',
    }
}
