package grape.base.service.post.impl;

import grape.base.service.post.po.Post;
import grape.base.service.post.mapper.PostMapper;
import grape.base.service.post.api.IPostService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Service
public class PostServiceImpl extends BaseServiceImpl<PostMapper, Post> implements IPostService {

}
