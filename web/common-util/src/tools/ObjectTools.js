/**
 * 判断是否为对象
 * @param obj
 * @returns {boolean}
 */
export function isObject(obj) {
    return Object.prototype.toString.call(obj) === '[object Object]'
}

/**
 * 纯简单对象克隆
 * @param obj
 */
export function cloneSimple(obj,ignoreEmpty = false) {
    let temp = {}
    for(let key in obj){
        let oldValue = obj[key]
        if(ignoreEmpty && oldValue == ''){

        }else {
            temp[key] = oldValue
        }
    }
    return temp
}

/**
 * 复制属性
 * @param dest
 * @param source
 * @returns {*}
 */
export function copyPropSimple(dest, source) {
    if(!source){
        return dest
    }
    for(let key in source){
        dest[key] = source[key]
    }
    return dest
}

/**
 * 深度获取对象属性，prop，如：a.b.c
 * @param obj
 * @param prop 字符串 属性名称
 * @returns {*}
 */
export  function getValue(obj, prop) {
    if (prop) {
        let value = obj
        let key = prop.split('.')
        for (let i = 0; i < key.length; i++) {
            value = value[key[i]]
            if (!value) {
                return null
            }
        }
        return value
    }
    return null
}

/**
 * 是否有属性
 * @param obj 纯简单对象
 * @param prop
 */
export function hasPropSimple(obj,prop) {
    return obj[prop] !== undefined
}