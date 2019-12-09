package grape.base.service.postdatascoperel.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.postdatascoperel.po.PostDataScopeRel;
import grape.common.service.common.IBaseRelService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 岗位数据范围关系表，多对多 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
public interface IPostDataScopeRelService extends IBaseRelService<PostDataScopeRel> {

    /**
     * 获取岗位绑定的数据范围
     * @param postId
     * @return
     */
    default List<PostDataScopeRel> getByPostId(String postId){
        if (postId == null) {
            return null;
        }
        return list(Wrappers.<PostDataScopeRel>lambdaQuery().eq(PostDataScopeRel::getPostId,postId));
    }

    /**
     * 清空岗位下的数据范围
     * @param postId
     * @return
     */
    default boolean removeByPostId(String postId){
        assertParamNotEmpty(postId,"postId is not empty");
        return remove(Wrappers.<PostDataScopeRel>lambdaQuery().eq(PostDataScopeRel::getPostId, postId));
    }
    /**
     * 岗位绑定数据范围
     * @param postId 岗位id
     * @param checkedDataScopeIds 已选择的数据范围id
     * @param uncheckedDataScopeIds 未选择的数据范围id
     * @param isLazyLoad 标识页面的可选择数据是否为懒加载
     */
    default void postAssignDataScope(String postId, List<String> checkedDataScopeIds,List<String> uncheckedDataScopeIds,Boolean isLazyLoad){

        removeAssignRel(postId,checkedDataScopeIds,uncheckedDataScopeIds,isLazyLoad,PostDataScopeRel::getPostId,PostDataScopeRel::getDataScopeId);
        // 再添加
        doBind(postId, checkedDataScopeIds, true);
    }

    /**
     * 获取数据范围绑定的岗位
     * @param dataScopeId
     * @return
     */
    default List<PostDataScopeRel> getByDataScopeId(String dataScopeId){
        if (dataScopeId == null) {
            return null;
        }
        return list(Wrappers.<PostDataScopeRel>lambdaQuery().eq(PostDataScopeRel::getDataScopeId,dataScopeId));
    }
    /**
     * 清空数据范围下的岗位
     * @param dataScopeId
     * @return
     */
    default boolean removeByDataScopeId(String dataScopeId){
        assertParamNotEmpty(dataScopeId,"dataScopeId is not empty");
        return remove(Wrappers.<PostDataScopeRel>lambdaQuery().eq(PostDataScopeRel::getDataScopeId, dataScopeId));
    }
    /**
     * 数据范围绑定岗位
     * @param dataScopeId
     * @param checkedPostIds
     * @param uncheckedPostIds
     */
    default void dataScopeAssignPost(String dataScopeId, List<String> checkedPostIds,List<String> uncheckedPostIds,Boolean isLazyLoad){
        removeAssignRel(dataScopeId,checkedPostIds,uncheckedPostIds,isLazyLoad,PostDataScopeRel::getDataScopeId,PostDataScopeRel::getPostId);
        // 再添加
        doBind(dataScopeId, checkedPostIds, false);
    }

    /**
     * 添加已选数据
     * @param mainId
     * @param checkedIds
     * @param isPostMain
     */
    void doBind(String mainId,List<String> checkedIds,boolean isPostMain);
}
