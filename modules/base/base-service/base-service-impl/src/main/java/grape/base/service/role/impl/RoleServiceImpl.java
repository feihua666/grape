package grape.base.service.role.impl;

import grape.base.service.role.api.IRoleService;
import grape.base.service.role.mapper.RoleMapper;
import grape.base.service.role.po.Role;
import grape.base.service.userpostrolerel.api.IUserPostRoleRelService;
import grape.base.service.userpostrolerel.po.UserPostRoleRel;
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
    @Autowired
    private IUserPostRoleRelService iUserPostRoleRelService;
    @Override
    public List<Role> getByUserId(String userId,Role query) {
        assertParamNotEmpty(userId,"用户id不能为空");
        List<UserRoleRel> userRoleRelList = iUserRoleRelService.getByUserId(userId);
        if (!isEmpty(userRoleRelList)) {
            Set<String> roleIds = userRoleRelList.stream().map(userRoleRel -> userRoleRel.getRoleId()).collect(Collectors.toSet());
            return listByIds(roleIds, query);
        }
        return null;
    }

    @Override
    public List<Role> getByUserPostId(String userPostId,Role query) {
        assertParamNotEmpty(userPostId,"用户岗位id不能为空");
        List<UserPostRoleRel> userPostRoleRelList = iUserPostRoleRelService.getByUserPostId(userPostId);
        if (!isEmpty(userPostRoleRelList)) {
            Set<String> roleIds = userPostRoleRelList.stream().map(userPostRoleRel -> userPostRoleRel.getRoleId()).collect(Collectors.toSet());
            return listByIds(roleIds, query);
        }
        return null;
    }
}
