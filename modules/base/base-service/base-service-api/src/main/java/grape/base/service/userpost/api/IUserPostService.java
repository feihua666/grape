package grape.base.service.userpost.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.userpost.dto.UserPostInfo;
import grape.base.service.userpost.po.UserPost;
import grape.common.service.common.IBaseService;

import java.util.List;

/**
 * <p>
 * 用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
public interface IUserPostService extends IBaseService<UserPost> {

    /**
     * 根据用户id获取岗位
     * @param userId
     * @param isMain
     * @param isEffect
     * @return
     */
    default List<UserPost> getByUserId(String userId,Boolean isMain,Boolean isEffect){
        if (userId == null) {
            return null;
        }
        UserPost userPost = new UserPost();
        userPost.setUserId(userId);
        if (isMain != null) {
            userPost.setIsMain(isMain);
        }
        if (isEffect != null) {
            userPost.setIsEffect(isEffect);
        }
        return list(Wrappers.query(userPost));
    }

    /**
     * 获取用户的主岗，一个用户只能有一个主岗
     * @param userId
     * @return
     */
    default UserPost getMainByUserId(String userId){
        List<UserPost> list = getByUserId(userId, true, null);
        if (!isListEmpty(list)) {
            return  list.get(0);
        }
        return null;
    }

     UserPostInfo getUserPostInfo(UserPost userPost);
     List<UserPostInfo> getUserPostInfos(List<UserPost> userPosts);
}
