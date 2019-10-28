/**
 * 首字母大写
 * @param str
 * @returns {string}
 */
export function upperFirst(str) {
    return str.charAt(0).toUpperCase() + str.slice(1)
}

/**
 * 获取路径参数,形如：/user/:id
 * :id为参数
 * @param url
 * @return 返回属性名，如：id
 */
export function getPathVar(url) {
    return url.substring(url.lastIndexOf(':') + 1)
}

/**
 * 替换路径变量
 * @param url 形如：/user/:id
 * @param prop 如：id
 * @param val 替换的值
 * @return 返回替换后的url
 */
export function putPathVar(url,prop,val) {
    return url.replace(':' + prop,val)
}