package grape.base.service.role.impl;

import grape.base.service.role.api.IRoleService;
import grape.base.service.role.mapper.RoleMapper;
import grape.base.service.role.po.Role;
import grape.base.service.userrolerel.api.IUserRoleRelService;
import grape.base.service.userrolerel.po.UserRoleRel;
import grape.common.service.common.BaseServiceImpl;
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
    public List<Role> getByUserId(String userId,Boolean roleDisabled) {
        if (userId == null) {
            return null;
        }
        List<UserRoleRel> userRoleRelList = iUserRoleRelService.getByUserId(userId);
        if (!isListEmpty(userRoleRelList)) {
            Set<String> roleIds = userRoleRelList.stream().map(userRoleRel -> userRoleRel.getRoleId()).collect(Collectors.toSet());
            List<Role> roles = (List<Role>) listByIds(roleIds);
            if (roleDisabled == null) {
                return roles;
            }else {
                // 过滤一下状态
                return roles.stream().filter(role -> role.getIsDisabled().equals(roleDisabled)).collect(Collectors.toList());
            }
        }
        return null;
    }
}
