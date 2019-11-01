package grape.base.service.post.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.post.po.Post;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.IBaseService;

/**
 * <p>
 * 岗位表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
public interface IPostService extends IBaseService<Post> {

    /**
     * 判断编码是否已存在
     * @param code
     * @return
     */
    default boolean existCode(String code){
        if (isStrEmpty(code)) {
            throw new InvalidParamsException("code 不能为空");
        }
        Post post = new Post();
        post.setCode(code);
        int r = count(Wrappers.query(post));
        return r > 0;
    }
}
