package grape.base.service.userpostfuncrel.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.userpostfuncrel.po.UserPostFuncRel;
import grape.common.service.common.IBaseRelService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户岗位功能关系表，多对多 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-17
 */
public interface IUserPostFuncRelService extends IBaseRelService<UserPostFuncRel> {

    /**
     * 获取用户岗位绑定的功能
     * @param userPostId
     * @return
     */
    default List<UserPostFuncRel> getByUserPostId(String userPostId){
        if (userPostId == null) {
            return null;
        }
        return list(Wrappers.<UserPostFuncRel>lambdaQuery().eq(UserPostFuncRel::getUserPostId,userPostId));
    }

    /**
     * 清空用户岗位下的功能
     * @param userPostId
     * @return
     */
    default boolean removeByUserPostId(String userPostId){
        assertParamNotEmpty(userPostId,"userPostId is not empty");
        return remove(Wrappers.<UserPostFuncRel>lambdaQuery().eq(UserPostFuncRel::getUserPostId, userPostId));
    }
    /**
     * 用户岗位绑定功能
     * @param userPostId 用户岗位id
     * @param checkedFuncIds 已选择的功能id
     * @param uncheckedFuncIds 未选择的功能id
     * @param isLazyLoad 标识页面的可选择数据是否为懒加载
     */
    default void userPostAssignFunc(String userPostId, List<String> checkedFuncIds,List<String> uncheckedFuncIds,Boolean isLazyLoad){

        removeAssignRel(userPostId,checkedFuncIds,uncheckedFuncIds,isLazyLoad,UserPostFuncRel::getUserPostId,UserPostFuncRel::getFuncId);
        // 再添加
        doBind(userPostId, checkedFuncIds, true);
    }

    /**
     * 获取功能绑定的用户岗位
     * @param funcId
     * @return
     */
    default List<UserPostFuncRel> getByFuncId(String funcId){
        if (funcId == null) {
            return null;
        }
        return list(Wrappers.<UserPostFuncRel>lambdaQuery().eq(UserPostFuncRel::getFuncId,funcId));
    }
    /**
     * 清空功能下的用户岗位
     * @param funcId
     * @return
     */
    default boolean removeByFuncId(String funcId){
        assertParamNotEmpty(funcId,"funcId is not empty");
        return remove(Wrappers.<UserPostFuncRel>lambdaQuery().eq(UserPostFuncRel::getFuncId, funcId));
    }
    /**
     * 功能绑定用户岗位
     * @param funcId
     * @param checkedUserPostIds
     * @param uncheckedUserPostIds
     */
    default void funcAssignUserPost(String funcId, List<String> checkedUserPostIds,List<String> uncheckedUserPostIds,Boolean isLazyLoad){
        removeAssignRel(funcId,checkedUserPostIds,uncheckedUserPostIds,isLazyLoad,UserPostFuncRel::getFuncId,UserPostFuncRel::getUserPostId);
        // 再添加
        doBind(funcId, checkedUserPostIds, false);
    }

    /**
     * 添加已选数据
     * @param mainId
     * @param checkedIds
     * @param isUserPostMain
     */
    default void doBind(String mainId,List<String> checkedIds,boolean isUserPostMain){
        if (!isEmpty(checkedIds)) {
            List<UserPostFuncRel> insert = new ArrayList<>(checkedIds.size());
            UserPostFuncRel userPostFuncRel = null;
            for (String checkedId : checkedIds) {
                userPostFuncRel = new UserPostFuncRel();
                if (isUserPostMain) {
                    userPostFuncRel.setUserPostId(mainId);
                    userPostFuncRel.setFuncId(checkedId);
                }else {
                    userPostFuncRel.setUserPostId(checkedId);
                    userPostFuncRel.setFuncId(mainId);
                }
                insert.add(userPostFuncRel);
            }
            saveBatch(insert);
        }
    }
}
