package grape.base.service.userfuncrel.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.userfuncrel.po.UserFuncRel;
import grape.common.service.common.IBaseRelService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户功能关系表，多对多 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-17
 */
public interface IUserFuncRelService extends IBaseRelService<UserFuncRel> {

    /**
     * 获取用户绑定的功能
     * @param userId
     * @return
     */
    default List<UserFuncRel> getByUserId(String userId){
        if (userId == null) {
            return null;
        }
        return list(Wrappers.<UserFuncRel>lambdaQuery().eq(UserFuncRel::getUserId,userId));
    }

    /**
     * 清空用户下的功能
     * @param userId
     * @return
     */
    default boolean removeByUserId(String userId){
        assertParamNotEmpty(userId,"userId is not empty");
        return remove(Wrappers.<UserFuncRel>lambdaQuery().eq(UserFuncRel::getUserId, userId));
    }
    /**
     * 用户绑定功能
     * @param userId 用户id
     * @param checkedFuncIds 已选择的功能id
     * @param uncheckedFuncIds 未选择的功能id
     * @param isLazyLoad 标识页面的可选择数据是否为懒加载
     */
    default void userAssignFunc(String userId, List<String> checkedFuncIds,List<String> uncheckedFuncIds,Boolean isLazyLoad){

        removeAssignRel(userId,checkedFuncIds,uncheckedFuncIds,isLazyLoad,UserFuncRel::getUserId,UserFuncRel::getFuncId);
        // 再添加
        doBind(userId, checkedFuncIds, true);
    }

    /**
     * 获取功能绑定的用户
     * @param funcId
     * @return
     */
    default List<UserFuncRel> getByFuncId(String funcId){
        if (funcId == null) {
            return null;
        }
        return list(Wrappers.<UserFuncRel>lambdaQuery().eq(UserFuncRel::getFuncId,funcId));
    }
    /**
     * 清空功能下的用户
     * @param funcId
     * @return
     */
    default boolean removeByFuncId(String funcId){
        assertParamNotEmpty(funcId,"funcId is not empty");
        return remove(Wrappers.<UserFuncRel>lambdaQuery().eq(UserFuncRel::getFuncId, funcId));
    }
    /**
     * 功能绑定用户
     * @param funcId
     * @param checkedUserIds
     * @param uncheckedUserIds
     */
    default void funcAssignUser(String funcId, List<String> checkedUserIds,List<String> uncheckedUserIds,Boolean isLazyLoad){
        removeAssignRel(funcId,checkedUserIds,uncheckedUserIds,isLazyLoad,UserFuncRel::getFuncId,UserFuncRel::getUserId);
        // 再添加
        doBind(funcId, checkedUserIds, false);
    }

    /**
     * 添加已选数据
     * @param mainId
     * @param checkedIds
     * @param isUserMain
     */
    default void doBind(String mainId,List<String> checkedIds,boolean isUserMain){
        if (!isEmpty(checkedIds)) {
            List<UserFuncRel> insert = new ArrayList<>(checkedIds.size());
            UserFuncRel userFuncRel = null;
            for (String checkedId : checkedIds) {
                userFuncRel = new UserFuncRel();
                if (isUserMain) {
                    userFuncRel.setUserId(mainId);
                    userFuncRel.setFuncId(checkedId);
                }else {
                    userFuncRel.setUserId(checkedId);
                    userFuncRel.setFuncId(mainId);
                }
                insert.add(userFuncRel);
            }
            saveBatch(insert);
        }
    }
}
