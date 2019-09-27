package grape.base.service.userpost.mapper;

import grape.base.service.userpost.po.UserPost;
import grape.common.service.IBaseMapper;

/**
 * <p>
 * 用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职 Mapper 接口
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
public interface UserPostMapper extends IBaseMapper<UserPost> {

}
