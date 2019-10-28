package grape.base.service.userpost.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.userpost.po.UserPost;
import grape.common.service.IBaseService;

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
}
