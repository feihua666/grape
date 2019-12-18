package grape.base.service.postfuncrel.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.postfuncrel.po.PostFuncRel;
import grape.common.service.common.IBaseRelService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 岗位功能关系表，多对多 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-17
 */
public interface IPostFuncRelService extends IBaseRelService<PostFuncRel> {

    /**
     * 获取岗位绑定的功能
     * @param postId
     * @return
     */
    default List<PostFuncRel> getByPostId(String postId){
        if (postId == null) {
            return null;
        }
        return list(Wrappers.<PostFuncRel>lambdaQuery().eq(PostFuncRel::getPostId,postId));
    }

    /**
     * 清空岗位下的功能
     * @param postId
     * @return
     */
    default boolean removeByPostId(String postId){
        assertParamNotEmpty(postId,"postId is not empty");
        return remove(Wrappers.<PostFuncRel>lambdaQuery().eq(PostFuncRel::getPostId, postId));
    }
    /**
     * 岗位绑定功能
     * @param postId 岗位id
     * @param checkedFuncIds 已选择的功能id
     * @param uncheckedFuncIds 未选择的功能id
     * @param isLazyLoad 标识页面的可选择数据是否为懒加载
     */
    default void postAssignFunc(String postId, List<String> checkedFuncIds,List<String> uncheckedFuncIds,Boolean isLazyLoad){

        removeAssignRel(postId,checkedFuncIds,uncheckedFuncIds,isLazyLoad,PostFuncRel::getPostId,PostFuncRel::getFuncId);
        // 再添加
        doBind(postId, checkedFuncIds, true);
    }

    /**
     * 获取功能绑定的岗位
     * @param funcId
     * @return
     */
    default List<PostFuncRel> getByFuncId(String funcId){
        if (funcId == null) {
            return null;
        }
        return list(Wrappers.<PostFuncRel>lambdaQuery().eq(PostFuncRel::getFuncId,funcId));
    }
    /**
     * 清空功能下的岗位
     * @param funcId
     * @return
     */
    default boolean removeByFuncId(String funcId){
        assertParamNotEmpty(funcId,"funcId is not empty");
        return remove(Wrappers.<PostFuncRel>lambdaQuery().eq(PostFuncRel::getFuncId, funcId));
    }
    /**
     * 功能绑定岗位
     * @param funcId
     * @param checkedPostIds
     * @param uncheckedPostIds
     */
    default void funcAssignPost(String funcId, List<String> checkedPostIds,List<String> uncheckedPostIds,Boolean isLazyLoad){
        removeAssignRel(funcId,checkedPostIds,uncheckedPostIds,isLazyLoad,PostFuncRel::getFuncId,PostFuncRel::getPostId);
        // 再添加
        doBind(funcId, checkedPostIds, false);
    }

    /**
     * 添加已选数据
     * @param mainId
     * @param checkedIds
     * @param isPostMain
     */
    default void doBind(String mainId,List<String> checkedIds,boolean isPostMain){
        if (!isEmpty(checkedIds)) {
            List<PostFuncRel> insert = new ArrayList<>(checkedIds.size());
            PostFuncRel postFuncRel = null;
            for (String checkedId : checkedIds) {
                postFuncRel = new PostFuncRel();
                if (isPostMain) {
                    postFuncRel.setPostId(mainId);
                    postFuncRel.setFuncId(checkedId);
                }else {
                    postFuncRel.setPostId(checkedId);
                    postFuncRel.setFuncId(mainId);
                }
                insert.add(postFuncRel);
            }
            saveBatch(insert);
        }
    }
}
