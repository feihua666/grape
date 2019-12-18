package grape.base.service.userpostfuncrel.impl;

import grape.base.service.userpostfuncrel.po.UserPostFuncRel;
import grape.base.service.userpostfuncrel.mapper.UserPostFuncRelMapper;
import grape.base.service.userpostfuncrel.api.IUserPostFuncRelService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户岗位功能关系表，多对多 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-17
 */
@Service
public class UserPostFuncRelServiceImpl extends BaseServiceImpl<UserPostFuncRelMapper, UserPostFuncRel> implements IUserPostFuncRelService {

}
