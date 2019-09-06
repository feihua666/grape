package grape.base.service.user.impl;

import grape.base.service.user.po.UserPwd;
import grape.base.service.user.mapper.UserPwdMapper;
import grape.base.service.user.api.IUserPwdService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户密码表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-06
 */
@Service
public class UserPwdServiceImpl extends BaseServiceImpl<UserPwdMapper, UserPwd> implements IUserPwdService {

}
