package grape.base.service.userrolerel.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.userrolerel.po.UserRoleRel;
import grape.common.service.IBaseRelService;
import grape.common.service.IBaseService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单功能关系表，多对多 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-27
 */
public interface IUserRoleRelService extends IBaseRelService<UserRoleRel> {

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    default List<UserRoleRel> getByUserId(String userId){
        if (userId == null) {
            return null;
        }
        UserRoleRel userRoleRel = new UserRoleRel();
        userRoleRel.setUserId(userId);
        return list(Wrappers.query(userRoleRel));
    }
    /**
     * 清空功能下的角色
     * @param userId
     * @return
     */
    default boolean removeByUserId(String userId){
        assertParamNotEmpty(userId,"funcId is not empty");
        return remove(Wrappers.<UserRoleRel>lambdaQuery().eq(UserRoleRel::getUserId, userId));
    }
    /**
     * 用户绑定角色
     * @param userId
     * @param checkedRoleIds
     * @param uncheckedRoleIds
     */
    default void userAssignRole(String userId, List<String> checkedRoleIds,List<String> uncheckedRoleIds,Boolean isLazyLoad){
        removeAssignRel(userId,checkedRoleIds,uncheckedRoleIds,isLazyLoad,UserRoleRel::getUserId,UserRoleRel::getRoleId);
        // 再添加
        doBind(userId, checkedRoleIds, false);
    }

    /**
     * 根据角色id查询
     * @param roleId
     * @return
     */
    default List<UserRoleRel> getByRoleId(String roleId){
        if (roleId == null) {
            return null;
        }
        UserRoleRel userRoleRel = new UserRoleRel();
        userRoleRel.setRoleId(roleId);
        return list(Wrappers.query(userRoleRel));
    }
    /**
     * 清空角色下的用户
     * @param roleId
     * @return
     */
    default boolean removeByRoleId(String roleId){
        assertParamNotEmpty(roleId,"roleId is not empty");
        return remove(Wrappers.<UserRoleRel>lambdaQuery().eq(UserRoleRel::getRoleId, roleId));
    }
    /**
     * 角色绑定用户
     * @param roleId 角色id
     * @param checkedUserIds 已选择的用户id
     * @param uncheckedUserIds 未选择的用户id
     * @param isLazyLoad 标识页面的可选择数据是否为懒加载
     */
    default void roleAssignUser(String roleId, List<String> checkedUserIds,List<String> uncheckedUserIds,Boolean isLazyLoad){

        removeAssignRel(roleId,checkedUserIds,uncheckedUserIds,isLazyLoad,UserRoleRel::getRoleId,UserRoleRel::getUserId);
        // 再添加
        doBind(roleId, checkedUserIds, true);
    }
    /**
     * 添加已选数据
     * @param mainId
     * @param checkedIds
     * @param isRoleMain
     */
    default void doBind(String mainId,List<String> checkedIds,boolean isRoleMain){
        if (!isListEmpty(checkedIds)) {
            List<UserRoleRel> insert = new ArrayList<>(checkedIds.size());
            UserRoleRel rel = null;
            for (String checkedId : checkedIds) {
                rel = new UserRoleRel();
                if (isRoleMain) {
                    rel.setRoleId(mainId);
                    rel.setUserId(checkedId);
                }else {
                    rel.setRoleId(checkedId);
                    rel.setUserId(mainId);
                }
                insert.add(rel);
            }
            saveBatch(insert);
        }
    }
}
