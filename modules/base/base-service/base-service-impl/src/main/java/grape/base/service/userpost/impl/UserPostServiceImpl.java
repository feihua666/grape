package grape.base.service.userpost.impl;

import grape.base.service.userpost.po.UserPost;
import grape.base.service.userpost.mapper.UserPostMapper;
import grape.base.service.userpost.api.IUserPostService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Service
public class UserPostServiceImpl extends BaseServiceImpl<UserPostMapper, UserPost> implements IUserPostService {

}
