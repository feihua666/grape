package grape.base.service.userpostrolerel.impl;

import grape.base.service.userpostrolerel.po.UserPostRoleRel;
import grape.base.service.userpostrolerel.mapper.UserPostRoleRelMapper;
import grape.base.service.userpostrolerel.api.IUserPostRoleRelService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户岗位与角色关系表,决定了用户的功能 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Service
public class UserPostRoleRelServiceImpl extends BaseServiceImpl<UserPostRoleRelMapper, UserPostRoleRel> implements IUserPostRoleRelService {

}
