/**
 * 是否为数组
 * @param array
 * @returns {*}
 */
export function isArray(array) {
    if (typeof Array.isArray === "function") {
        return Array.isArray(array);
    }else{
        return Object.prototype.toString.call(array) === "[object Array]";
    }
}

/**
 * 数组的元素对象是否有某个属性
 * @param array
 */
export function itemHasProp(array,prop) {
    let r = false
    array.forEach(item => {
        if(item[prop] !== undefined){
            r = true
            // 如果存在跳出循环
            return false
        }
    })
    return r
}