package grape.base.service.userdatascoperel.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.userdatascoperel.po.UserDataScopeRel;
import grape.common.service.common.IBaseRelService;
import grape.common.service.common.IBaseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户数据范围关系表，多对多 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
public interface IUserDataScopeRelService extends IBaseRelService<UserDataScopeRel> {

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    default List<UserDataScopeRel> getByUserId(String userId){
        if (userId == null) {
            return null;
        }
        UserDataScopeRel userDataScopeRel = new UserDataScopeRel();
        userDataScopeRel.setUserId(userId);
        return list(Wrappers.query(userDataScopeRel));
    }
    /**
     * 清空用户下的数据范围
     * @param userId
     * @return
     */
    default boolean removeByUserId(String userId){
        assertParamNotEmpty(userId,"userId is not empty");
        return remove(Wrappers.<UserDataScopeRel>lambdaQuery().eq(UserDataScopeRel::getUserId, userId));
    }
    /**
     * 用户绑定数据范围
     * @param userId
     * @param checkedDataScopeIds
     * @param uncheckedDataScopeIds
     */
    default void userAssignDataScope(String userId, List<String> checkedDataScopeIds,List<String> uncheckedDataScopeIds,Boolean isLazyLoad){
        removeAssignRel(userId,checkedDataScopeIds,uncheckedDataScopeIds,isLazyLoad,UserDataScopeRel::getUserId,UserDataScopeRel::getDataScopeId);
        // 再添加
        doBind(userId, checkedDataScopeIds, false);
    }

    /**
     * 根据数据范围id查询
     * @param dataScopeId
     * @return
     */
    default List<UserDataScopeRel> getByDataScopeId(String dataScopeId){
        if (dataScopeId == null) {
            return null;
        }
        UserDataScopeRel userDataScopeRel = new UserDataScopeRel();
        userDataScopeRel.setDataScopeId(dataScopeId);
        return list(Wrappers.query(userDataScopeRel));
    }
    /**
     * 清空数据范围下的用户
     * @param dataScopeId
     * @return
     */
    default boolean removeByDataScopeId(String dataScopeId){
        assertParamNotEmpty(dataScopeId,"dataScopeId is not empty");
        return remove(Wrappers.<UserDataScopeRel>lambdaQuery().eq(UserDataScopeRel::getDataScopeId, dataScopeId));
    }
    /**
     * 数据范围绑定用户
     * @param dataScopeId 数据范围id
     * @param checkedUserIds 已选择的用户id
     * @param uncheckedUserIds 未选择的用户id
     * @param isLazyLoad 标识页面的可选择数据是否为懒加载
     */
    default void dataScopeAssignUser(String dataScopeId, List<String> checkedUserIds,List<String> uncheckedUserIds,Boolean isLazyLoad){

        removeAssignRel(dataScopeId,checkedUserIds,uncheckedUserIds,isLazyLoad,UserDataScopeRel::getDataScopeId,UserDataScopeRel::getUserId);
        // 再添加
        doBind(dataScopeId, checkedUserIds, true);
    }
    /**
     * 添加已选数据
     * @param mainId
     * @param checkedIds
     * @param isDataScopeMain
     */
    public void doBind(String mainId,List<String> checkedIds,boolean isDataScopeMain);
}
