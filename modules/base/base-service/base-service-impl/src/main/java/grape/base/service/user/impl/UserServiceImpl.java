package grape.base.service.user.impl;

import grape.base.service.BaseLoginUser;
import grape.base.service.userpost.dto.UserPostInfo;
import grape.base.service.comp.api.ICompService;
import grape.base.service.comp.po.Comp;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.job.api.IJobService;
import grape.base.service.joblevel.api.IJobLevelService;
import grape.base.service.post.api.IPostService;
import grape.base.service.role.api.IRoleService;
import grape.base.service.role.po.Role;
import grape.base.service.user.api.IUserIdentifierService;
import grape.base.service.user.mapper.UserMapper;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.po.User;
import grape.base.service.user.po.UserIdentifier;
import grape.base.service.userpost.api.IUserPostService;
import grape.base.service.userpost.po.UserPost;
import grape.base.service.userpostrolerel.api.IUserPostRoleRelService;
import grape.base.service.userpostrolerel.po.UserPostRoleRel;
import grape.common.AbstractLoginUser;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.exception.runtime.RDataNotExistException;
import grape.common.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 后台管理用户表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IDeptService iDeptService;
    @Autowired
    private ICompService iCompService;
    @Autowired
    private IUserPostService iUserPostService;
    @Autowired
    private IUserPostRoleRelService iUserPostRoleRelService;
    @Autowired
    private IUserIdentifierService iUserIdentifierService;

    @Override
    public BaseLoginUser initLoginUserByIdentifier(String identifier) {
        if (identifier == null) {
            throw new InvalidParamsException("identifier 不能为空");
        }
        BaseLoginUser loginUser = new BaseLoginUser();

        UserIdentifier userIdentifier = iUserIdentifierService.getByIdentifier(identifier);
        // 基本信息
        User user = getById(userIdentifier.getUserId());
        if (user == null) {
            throw new RDataNotExistException("根据userId="+ userIdentifier.getUserId() +"没有找到用户");
        }
        loginUser.setUserId(userIdentifier.getUserId());
        loginUser.setAvatar(user.getAvatar());
        loginUser.setNickname(user.getNickname());

        // 部门
        if (user.getDeptId() != null) {
            Dept dept = iDeptService.getById(user.getDeptId());
            loginUser.setDept(dept);
        }
        // 公司
        if (user.getCompId() != null) {
            Comp comp = iCompService.getById(user.getCompId());
            loginUser.setComp(comp);
        }

        // 用户直接关联的角色
        List<Role> roles = iRoleService.getByUserId(userIdentifier.getUserId());
        if (!isListEmpty(roles)) {
            // 如果用户直接绑定了角色则按切换角色
            loginUser.setIsSwitchRole(true);
            // 默认取第一个角色作为初始角色
            loginUser.setCurrentRole(roles.get(0));
            loginUser.setRoles(roles);
        }

        // 担任岗位
        List<UserPost> userPosts = iUserPostService.getByUserId(userIdentifier.getUserId(),null,true);
        // 设置所有担任岗位
        loginUser.setUserPosts(userPosts);
        loginUser.setUserPostInfos(iUserPostService.getUserPostInfos(userPosts));
        if (!isListEmpty(userPosts) && loginUser.getIsSwitchRole() == null) {
            // false代表需要切换岗位
            loginUser.setIsSwitchRole(false);
            // 如果没有主岗位，默认取第一个岗位
            UserPost defaultUserPost = userPosts.stream().filter(userPost -> userPost.getIsMain()).findFirst().orElse(userPosts.get(0));
            loginUser.setCurrentUserPost(defaultUserPost);
            loginUser.setCurrentUserPostInfo(iUserPostService.getUserPostInfo(defaultUserPost));

            UserPostRoleRel userPostRoleRel = iUserPostRoleRelService.getByUserPostId(loginUser.getCurrentUserPost().getId());
            loginUser.setCurrentUserPostRoleRel(userPostRoleRel);
            loginUser.setCurrentRole(iRoleService.getById(userPostRoleRel.getRoleId()));
        }

        loginUser.setUserIdentifier(userIdentifier);
        loginUser.setIsSuperAdmin(loginUser.getCurrentRole()!=null && AbstractLoginUser.superadminCode.equals(loginUser.getCurrentRole().getCode()) );
        return loginUser;
    }


}
