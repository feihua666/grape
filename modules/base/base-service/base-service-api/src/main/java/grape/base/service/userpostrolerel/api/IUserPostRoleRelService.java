package grape.base.service.userpostrolerel.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.userpostrolerel.po.UserPostRoleRel;
import grape.common.service.common.IBaseRelService;
import grape.common.service.common.IBaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户岗位与角色关系表,决定了用户的功能 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
public interface IUserPostRoleRelService extends IBaseRelService<UserPostRoleRel> {

    /**
     * 根据用户岗位id查询
     * @param userPostId
     * @return
     */
    default UserPostRoleRel getByUserPostId(String userPostId){
        if (userPostId == null) {
            return null;
        }
        UserPostRoleRel userPostRoleRel = new UserPostRoleRel();
        userPostRoleRel.setUserPostId(userPostId);
        return getOne(Wrappers.query(userPostRoleRel));
    }


    /**
     * 清空用户岗位下的角色
     * @param userPostId
     * @return
     */
    default boolean removeByUserPostId(String userPostId){
        assertParamNotEmpty(userPostId,"userPostId is not empty");
        return remove(Wrappers.<UserPostRoleRel>lambdaQuery().eq(UserPostRoleRel::getUserPostId, userPostId));
    }
    /**
     * 用户岗位绑定角色
     * @param userPostId
     */
    default void userAssignRole(String userPostId,String checkedRoleId){
        removeByUserPostId(userPostId);
        List<String> checkedRoleIds = new ArrayList<>(1);
        checkedRoleIds.add(checkedRoleId);
        // 再添加
        doBind(userPostId, checkedRoleIds, false);
    }

    /**
     * 根据角色id查询
     * @param roleId
     * @return
     */
    default List<UserPostRoleRel> getByRoleId(String roleId){
        if (roleId == null) {
            return null;
        }
        UserPostRoleRel userRoleRel = new UserPostRoleRel();
        userRoleRel.setRoleId(roleId);
        return list(Wrappers.query(userRoleRel));
    }
    /**
     * 清空角色下的用户岗位
     * @param roleId
     * @return
     */
    default boolean removeByRoleId(String roleId){
        assertParamNotEmpty(roleId,"roleId is not empty");
        return remove(Wrappers.<UserPostRoleRel>lambdaQuery().eq(UserPostRoleRel::getRoleId, roleId));
    }
    /**
     * 角色绑定用户岗位
     * @param roleId 角色id
     * @param checkedUserPostIds 已选择的用户岗位id
     * @param uncheckedUserPostIds 未选择的用户岗位id
     * @param isLazyLoad 标识页面的可选择数据是否为懒加载
     */
    default void roleAssignUser(String roleId, List<String> checkedUserPostIds,List<String> uncheckedUserPostIds,Boolean isLazyLoad){

        removeAssignRel(roleId,checkedUserPostIds,uncheckedUserPostIds,isLazyLoad,UserPostRoleRel::getRoleId,UserPostRoleRel::getUserPostId);
        // 再添加
        doBind(roleId, checkedUserPostIds, true);
    }
    /**
     * 添加已选数据
     * @param mainId
     * @param checkedIds
     * @param isRoleMain
     */
    default void doBind(String mainId,List<String> checkedIds,boolean isRoleMain){
        if (!isListEmpty(checkedIds)) {
            List<UserPostRoleRel> insert = new ArrayList<>(checkedIds.size());
            UserPostRoleRel rel = null;
            for (String checkedId : checkedIds) {
                rel = new UserPostRoleRel();
                if (isRoleMain) {
                    rel.setRoleId(mainId);
                    rel.setUserPostId(checkedId);
                }else {
                    rel.setRoleId(checkedId);
                    rel.setUserPostId(mainId);
                }
                insert.add(rel);
            }
            saveBatch(insert);
        }
    }
}
