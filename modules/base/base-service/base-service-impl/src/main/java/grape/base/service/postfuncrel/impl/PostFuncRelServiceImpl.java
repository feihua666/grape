package grape.base.service.postfuncrel.impl;

import grape.base.service.postfuncrel.po.PostFuncRel;
import grape.base.service.postfuncrel.mapper.PostFuncRelMapper;
import grape.base.service.postfuncrel.api.IPostFuncRelService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位功能关系表，多对多 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-17
 */
@Service
public class PostFuncRelServiceImpl extends BaseServiceImpl<PostFuncRelMapper, PostFuncRel> implements IPostFuncRelService {

}
