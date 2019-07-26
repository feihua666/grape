package grape.base.service.impl.impl;

import grape.base.service.api.po.BaseUser;
import grape.base.service.impl.mapper.BaseUserMapper;
import grape.base.service.api.service.IBaseUserService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台管理系统用户表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-07-24
 */
@Service
public class BaseUserServiceImpl extends BaseServiceImpl<BaseUserMapper, BaseUser> implements IBaseUserService {

}
