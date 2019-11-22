// 定义索引
const d = [
    {
        pattern:['查询','搜索'],
        icon: 'el-icon-search',
        type:'primary',
    },
    {
        pattern:['重置'],
        icon: 'el-icon-refresh',
        type: 'default'
    },
    {
        pattern:'删除',
        icon: 'el-icon-delete',
        type:'danger',
    },
    {
        pattern:['添加','新增'],
        icon: 'el-icon-plus',
        type:'primary',
    },
    {
        pattern:['修改','编辑','更新'],
        icon: 'el-icon-edit',
        type:'primary',
    },
    {
        pattern:'刷新',
        icon: 'el-icon-refresh'
    }
]

/**
 * 智能获取按钮样式
 * @param pattern
 * @return {*}
 */
export function aiButtonStyle(pattern) {
    let r = {}
    d.forEach(item =>{
        if(item.pattern.indexOf(pattern)>=0 || pattern.indexOf(item.pattern) >= 0){
            r = item
            return false
        }
    })
    return r
}