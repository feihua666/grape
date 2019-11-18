package grape.base.service.rolefuncrel.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import grape.base.service.rolefuncrel.po.RoleFuncRel;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.IBaseRelService;
import grape.common.service.IBaseService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import static cn.hutool.core.io.file.FileMode.r;

/**
 * <p>
 * 角色菜单功能关系表，多对多 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
public interface IRoleFuncRelService extends IBaseRelService<RoleFuncRel> {

    /**
     * 获取角色绑定的功能
     * @param roleId
     * @return
     */
    default List<RoleFuncRel> getRoleAssignFunc(String roleId){
        return list(Wrappers.<RoleFuncRel>lambdaQuery().eq(RoleFuncRel::getRoleId,roleId));
    }

    /**
     * 清空角色下的功能
     * @param roleId
     * @return
     */
    default boolean removeByRoleId(String roleId){
        assertParamNotEmpty(roleId,"roleId is not empty");
        return remove(Wrappers.<RoleFuncRel>lambdaQuery().eq(RoleFuncRel::getRoleId, roleId));
    }
    /**
     * 角色绑定功能
     * @param roleId 角色id
     * @param checkedFuncIds 已选择的功能id
     * @param uncheckedFuncIds 未选择的功能id，如果页面为懒加载该参数必须传，否则认为全选
     * @param isLazyLoad 标识页面的可选择数据是否为懒加载
     */
    default void roleAssignFunc(String roleId, List<String> checkedFuncIds,List<String> uncheckedFuncIds,Boolean isLazyLoad){

        removeAssignRel(roleId,checkedFuncIds,uncheckedFuncIds,isLazyLoad,RoleFuncRel::getRoleId,RoleFuncRel::getFuncId);
        // 再添加
        doBind(roleId, checkedFuncIds, true);
    }

    /**
     * 获取功能绑定的角色
     * @param funcId
     * @return
     */
    default List<RoleFuncRel> getFuncAssignRole(String funcId){
        return list(Wrappers.<RoleFuncRel>lambdaQuery().eq(RoleFuncRel::getFuncId,funcId));
    }
    /**
     * 清空功能下的角色
     * @param funcId
     * @return
     */
    default boolean removeByFuncId(String funcId){
        assertParamNotEmpty(funcId,"funcId is not empty");
        return remove(Wrappers.<RoleFuncRel>lambdaQuery().eq(RoleFuncRel::getFuncId, funcId));
    }
    /**
     * 功能绑定角色
     * @param funcId
     * @param checkedRoleIds
     * @param uncheckedRoleIds
     */
    default void funcAssignRole(String funcId, List<String> checkedRoleIds,List<String> uncheckedRoleIds,Boolean isLazyLoad){
        removeAssignRel(funcId,checkedRoleIds,uncheckedRoleIds,isLazyLoad,RoleFuncRel::getFuncId,RoleFuncRel::getRoleId);
        // 再添加
        doBind(funcId, checkedRoleIds, false);
    }
    default void doBind(String mainId,List<String> checkedIds,boolean isRoleMain){
        if (!isListEmpty(checkedIds)) {
            List<RoleFuncRel> insert = new ArrayList<>(checkedIds.size());
            RoleFuncRel roleFuncRel = null;
            for (String checkedId : checkedIds) {
                roleFuncRel = new RoleFuncRel();
                if (isRoleMain) {
                    roleFuncRel.setRoleId(mainId);
                    roleFuncRel.setFuncId(checkedId);
                }else {
                    roleFuncRel.setRoleId(checkedId);
                    roleFuncRel.setFuncId(mainId);
                }
                insert.add(roleFuncRel);
            }
            saveBatch(insert);
        }
    }
}
