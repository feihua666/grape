package grape.base.service.userrolerel.impl;

import grape.base.service.userrolerel.po.UserRoleRel;
import grape.base.service.userrolerel.mapper.UserRoleRelMapper;
import grape.base.service.userrolerel.api.IUserRoleRelService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单功能关系表，多对多 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-27
 */
@Service
public class UserRoleRelServiceImpl extends BaseServiceImpl<UserRoleRelMapper, UserRoleRel> implements IUserRoleRelService {

}
