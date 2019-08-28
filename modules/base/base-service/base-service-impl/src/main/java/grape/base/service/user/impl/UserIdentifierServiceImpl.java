package grape.base.service.user.impl;

import grape.base.service.user.po.UserIdentifierPo;
import grape.base.service.user.mapper.UserIdentifierMapper;
import grape.base.service.user.api.IUserIdentifierService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录标识表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-08-27
 */
@Service
public class UserIdentifierServiceImpl extends BaseServiceImpl<UserIdentifierMapper, UserIdentifierPo> implements IUserIdentifierService {

}
