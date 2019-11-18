package grape.base.service;

import grape.base.service.comp.po.Comp;
import grape.base.service.dept.po.Dept;
import grape.base.service.role.po.Role;
import grape.base.service.user.po.UserIdentifier;
import grape.base.service.userpost.dto.UserPostInfo;
import grape.base.service.userpost.po.UserPost;
import grape.base.service.userpostrolerel.po.UserPostRoleRel;
import grape.common.AbstractLoginUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 后台管理当前登录用户对象
 * 重要概念：当前用户支持切换角色或岗位，从数据上来看，如果用户直接绑定了角色即是切角色，否则切岗位
 * Created by yangwei
 * Created at 2019/9/26 10:17
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BaseLoginUser extends AbstractLoginUser {

    // 用户登录的帐号信息
    private UserIdentifier userIdentifier;
    // 是否超级管理员
    private Boolean isSuperAdmin;
    // 是否切换角色true=切换角色，false=切换岗位
    private Boolean isSwitchRole;
    // 用户所属公司
    private Comp comp;
    // 用户所属部门,用户表的部门并不是担任岗位的部门
    private Dept dept;
    // 用户担任岗位,正在使用的岗位关系
    private UserPost currentUserPost;
    // 用户担任岗位,正在使用的岗位关系对应实体
    private UserPostInfo currentUserPostInfo;

    // 用户担任岗位对应的角色关系
    private UserPostRoleRel currentUserPostRoleRel;
    // 当前正在使用的角色，isSwitchRole=false时由currentUserPostRoleRel派生，isSwitchRole=true时由用户角色关系派生
    private Role currentRole;
    // 用户担任的所有岗位关系
    private List<UserPost> userPosts;
    // 用户担任的所有岗位关系对应实体
    private List<UserPostInfo> userPostInfos;
    // 用户的可用角色，用户角色关系而来
    private List<Role> roles;


    public static BaseLoginUser getLoginUser(){
        return (BaseLoginUser) AbstractLoginUser.getLoginUser();
    }
}
