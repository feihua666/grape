package grape.base.service.user.impl;

import grape.base.service.user.po.UserPo;
import grape.base.service.user.mapper.UserMapper;
import grape.base.service.user.api.IUserService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台管理用户表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-08-27
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserPo> implements IUserService {

}
