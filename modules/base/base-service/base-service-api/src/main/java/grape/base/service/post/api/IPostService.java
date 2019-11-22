package grape.base.service.post.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.post.dto.PostListQuery;
import grape.base.service.post.po.Post;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.common.IBaseService;

import java.util.List;

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

    /**
     * 不分页查询岗位
     * @param query
     * @return
     */
    default List<Post> list(PostListQuery query){
        LambdaQueryWrapper<Post> queryWrapper = Wrappers.<Post>lambdaQuery();
        if (query.getCode() != null) {
            queryWrapper = queryWrapper.eq(Post::getCode, query.getCode());
        }
        if (query.getName() != null) {
            queryWrapper = queryWrapper.like(Post::getName, query.getName());
        }
        if (query.getDeptId() != null) {
            queryWrapper = queryWrapper.eq(Post::getDeptId, query.getDeptId());
        }
        return list(queryWrapper);
    }
}
