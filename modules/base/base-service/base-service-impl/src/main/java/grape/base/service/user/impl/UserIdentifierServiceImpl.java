package grape.base.service.user.impl;

import grape.base.service.user.po.UserIdentifier;
import grape.base.service.user.mapper.UserIdentifierMapper;
import grape.base.service.user.api.IUserIdentifierService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录标识表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Service
public class UserIdentifierServiceImpl extends BaseServiceImpl<UserIdentifierMapper, UserIdentifier> implements IUserIdentifierService {

}
