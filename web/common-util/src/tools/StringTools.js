/**
 * 首字母大写
 * @param str
 * @returns {string}
 */
export function upperFirst(str) {
    return str.charAt(0).toUpperCase() + str.slice(1)
}