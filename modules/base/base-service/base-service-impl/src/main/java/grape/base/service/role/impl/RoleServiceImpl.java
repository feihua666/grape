package grape.base.service.role.impl;

import grape.base.service.role.api.IRoleService;
import grape.base.service.role.mapper.RoleMapper;
import grape.base.service.role.po.Role;
import grape.base.service.userrolerel.api.IUserRoleRelService;
import grape.base.service.userrolerel.po.UserRoleRel;
import grape.common.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private IUserRoleRelService iUserRoleRelService;
    @Override
    public List<Role> getByUserId(String userId) {
        if (userId == null) {
            return null;
        }
        List<UserRoleRel> userRoleRelList = iUserRoleRelService.getByUserId(userId);
        if (!isListEmpty(userRoleRelList)) {
            Set<String> roleIds = userRoleRelList.stream().map(userRoleRel -> userRoleRel.getRoleId()).collect(Collectors.toSet());
            return (List<Role>) listByIds(roleIds);
        }
        return null;
    }
}
