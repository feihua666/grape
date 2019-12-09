package grape.base.service.userpostdatascoperel.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.userpostdatascoperel.po.UserPostDataScopeRel;
import grape.common.service.common.IBaseRelService;
import grape.common.service.common.IBaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户岗位数据范围关系表，多对多 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
public interface IUserPostDataScopeRelService extends IBaseRelService<UserPostDataScopeRel> {

    /**
     * 根据用户岗位id查询
     * @param userPostId
     * @return
     */
    default List<UserPostDataScopeRel> getByUserPostId(String userPostId){
        if (userPostId == null) {
            return null;
        }
        UserPostDataScopeRel userPostDataScopeRel = new UserPostDataScopeRel();
        userPostDataScopeRel.setUserPostId(userPostId);
        return list(Wrappers.query(userPostDataScopeRel));
    }


    /**
     * 清空用户岗位下的数据范围
     * @param userPostId
     * @return
     */
    default boolean removeByUserPostId(String userPostId){
        assertParamNotEmpty(userPostId,"userPostId is not empty");
        return remove(Wrappers.<UserPostDataScopeRel>lambdaQuery().eq(UserPostDataScopeRel::getUserPostId, userPostId));
    }
    /**
     * 用户岗位绑定数据范围
     * @param userPostId
     */
    default void userPostAssignDataScope(String userPostId,List<String> checkedDataScopeIds,List<String> uncheckedDataScopeIds,Boolean isLazyLoad){
        removeAssignRel(userPostId,checkedDataScopeIds,uncheckedDataScopeIds,isLazyLoad,UserPostDataScopeRel::getUserPostId,UserPostDataScopeRel::getDataScopeId);

        // 再添加
        doBind(userPostId, checkedDataScopeIds, false);
    }

    /**
     * 根据数据范围id查询
     * @param dataScopeId
     * @return
     */
    default List<UserPostDataScopeRel> getByDataScopeId(String dataScopeId){
        if (dataScopeId == null) {
            return null;
        }
        UserPostDataScopeRel userDataScopeRel = new UserPostDataScopeRel();
        userDataScopeRel.setDataScopeId(dataScopeId);
        return list(Wrappers.query(userDataScopeRel));
    }
    /**
     * 清空数据范围下的用户岗位
     * @param dataScopeId
     * @return
     */
    default boolean removeByDataScopeId(String dataScopeId){
        assertParamNotEmpty(dataScopeId,"dataScopeId is not empty");
        return remove(Wrappers.<UserPostDataScopeRel>lambdaQuery().eq(UserPostDataScopeRel::getDataScopeId, dataScopeId));
    }
    /**
     * 数据范围绑定用户岗位
     * @param dataScopeId 数据范围id
     * @param checkedUserPostIds 已选择的用户岗位id
     * @param uncheckedUserPostIds 未选择的用户岗位id
     * @param isLazyLoad 标识页面的可选择数据是否为懒加载
     */
    default void dataScopeAssignUserPost(String dataScopeId, List<String> checkedUserPostIds,List<String> uncheckedUserPostIds,Boolean isLazyLoad){

        removeAssignRel(dataScopeId,checkedUserPostIds,uncheckedUserPostIds,isLazyLoad,UserPostDataScopeRel::getDataScopeId,UserPostDataScopeRel::getUserPostId);
        // 再添加
        doBind(dataScopeId, checkedUserPostIds, true);
    }
    /**
     * 添加已选数据
     * @param mainId
     * @param checkedIds
     * @param isDataScopeMain
     */
    void doBind(String mainId,List<String> checkedIds,boolean isDataScopeMain);
}
