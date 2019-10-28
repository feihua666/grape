package grape.base.service.userrolerel.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.userrolerel.po.UserRoleRel;
import grape.common.service.IBaseService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色菜单功能关系表，多对多 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-27
 */
public interface IUserRoleRelService extends IBaseService<UserRoleRel> {

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
     * 根据角色id查询
     * @param roleId
     * @return
     */
    default List<UserRoleRel> getByRole(String roleId){
        if (roleId == null) {
            return null;
        }
        UserRoleRel userRoleRel = new UserRoleRel();
        userRoleRel.setRoleId(roleId);
        return list(Wrappers.query(userRoleRel));
    }
    default Set<String> convertRoleIds(List<UserRoleRel> userRoleRels){
        if (!isListEmpty(userRoleRels)) {
            Set<String> roleIds = new HashSet<>(userRoleRels.size());
            for (UserRoleRel userRoleRel : userRoleRels) {
                roleIds.add(userRoleRel.getRoleId());
            }
            return roleIds;
        }
        return null;
    }
    default Set<String> convertUserIds(List<UserRoleRel> userRoleRels){
        if (!isListEmpty(userRoleRels)) {
            Set<String> userIds = new HashSet<>(userRoleRels.size());
            for (UserRoleRel userRoleRel : userRoleRels) {
                userIds.add(userRoleRel.getUserId());
            }
            return userIds;
        }
        return null;
    }
}
