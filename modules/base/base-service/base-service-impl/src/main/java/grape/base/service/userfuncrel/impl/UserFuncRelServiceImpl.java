package grape.base.service.userfuncrel.impl;

import grape.base.service.userfuncrel.po.UserFuncRel;
import grape.base.service.userfuncrel.mapper.UserFuncRelMapper;
import grape.base.service.userfuncrel.api.IUserFuncRelService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户功能关系表，多对多 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-17
 */
@Service
public class UserFuncRelServiceImpl extends BaseServiceImpl<UserFuncRelMapper, UserFuncRel> implements IUserFuncRelService {

}
