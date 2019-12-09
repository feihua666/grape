package grape.base.service.roledatascoperel.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.roledatascoperel.po.RoleDataScopeRel;
import grape.common.service.common.IBaseRelService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色数据范围关系表，多对多 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
public interface IRoleDataScopeRelService extends IBaseRelService<RoleDataScopeRel> {

    /**
     * 获取角色绑定的数据范围
     * @param roleId
     * @return
     */
    default List<RoleDataScopeRel> getByRoleId(String roleId){
        if (roleId == null) {
            return null;
        }
        return list(Wrappers.<RoleDataScopeRel>lambdaQuery().eq(RoleDataScopeRel::getRoleId,roleId));
    }

    /**
     * 清空角色下的数据范围
     * @param roleId
     * @return
     */
    default boolean removeByRoleId(String roleId){
        assertParamNotEmpty(roleId,"roleId is not empty");
        return remove(Wrappers.<RoleDataScopeRel>lambdaQuery().eq(RoleDataScopeRel::getRoleId, roleId));
    }
    /**
     * 角色绑定数据范围
     * @param roleId 角色id
     * @param checkedDataScopeIds 已选择的数据范围id
     * @param uncheckedDataScopeIds 未选择的数据范围id
     * @param isLazyLoad 标识页面的可选择数据是否为懒加载
     */
    default void roleAssignDataScope(String roleId, List<String> checkedDataScopeIds,List<String> uncheckedDataScopeIds,Boolean isLazyLoad){

        removeAssignRel(roleId,checkedDataScopeIds,uncheckedDataScopeIds,isLazyLoad,RoleDataScopeRel::getRoleId,RoleDataScopeRel::getDataScopeId);
        // 再添加
        doBind(roleId, checkedDataScopeIds, true);
    }

    /**
     * 获取数据范围绑定的角色
     * @param dataScopeId
     * @return
     */
    default List<RoleDataScopeRel> getByDataScopeId(String dataScopeId){
        if (dataScopeId == null) {
            return null;
        }
        return list(Wrappers.<RoleDataScopeRel>lambdaQuery().eq(RoleDataScopeRel::getDataScopeId,dataScopeId));
    }
    /**
     * 清空数据范围下的角色
     * @param dataScopeId
     * @return
     */
    default boolean removeByDataScopeId(String dataScopeId){
        assertParamNotEmpty(dataScopeId,"dataScopeId is not empty");
        return remove(Wrappers.<RoleDataScopeRel>lambdaQuery().eq(RoleDataScopeRel::getDataScopeId, dataScopeId));
    }
    /**
     * 数据范围绑定角色
     * @param dataScopeId
     * @param checkedRoleIds
     * @param uncheckedRoleIds
     */
    default void dataScopeAssignRole(String dataScopeId, List<String> checkedRoleIds,List<String> uncheckedRoleIds,Boolean isLazyLoad){
        removeAssignRel(dataScopeId,checkedRoleIds,uncheckedRoleIds,isLazyLoad,RoleDataScopeRel::getDataScopeId,RoleDataScopeRel::getRoleId);
        // 再添加
        doBind(dataScopeId, checkedRoleIds, false);
    }

    /**
     * 添加已选数据
     * @param mainId
     * @param checkedIds
     * @param isRoleMain
     */
    void doBind(String mainId,List<String> checkedIds,boolean isRoleMain);
}
