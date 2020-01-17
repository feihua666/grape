package grape.auth.rest.user;

import grape.base.service.application.api.IApplicationService;
import grape.base.service.application.po.Application;
import grape.base.service.dataconstraint.api.IDataScopeService;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.func.api.IFuncService;
import grape.base.service.func.po.Func;
import grape.base.service.role.api.IRoleService;
import grape.base.service.role.po.Role;
import grape.base.service.userpost.api.IUserPostService;
import grape.base.service.userpost.po.UserPost;
import grape.common.AbstractLoginUser;
import grape.common.tools.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yangwei
 * Created at 2019/12/18 14:22
 */
@Slf4j
@Component
public class UserLoginHelperService implements ToolService {
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IUserPostService iUserPostService;
    @Autowired
    private IDataScopeService iDataScopeService;

    @Autowired
    private IFuncService iFuncService;

    @Autowired
    private IApplicationService iApplicationService;
    /**
     * 获取用户可用的角色
     * 包括两个部分，用户直接分配的和用户通过岗位关系分配的：
     * 1.用户 -> 角色
     * 2.用户 -> 用户岗位关系 -> 角色
     * @param userId
     * @return
     */
    public List<Role> getRolesByUserId(String userId){
        List<Role> roleList = new ArrayList<>();
        // 用户直接分配的角色 用户 -> 角色
        List<Role> roles = iRoleService.getByUserId(userId,new Role().setIsDisabled(false));
        if (!isEmpty(roles)) {
            roleList.addAll(roles);
        }
        // 间接分配的角色 用户 -> 用户岗位关系 -> 角色
        List<UserPost> userPosts = iUserPostService.getByUserId(userId, null, true);
        if (!isEmpty(userPosts)) {
            for (UserPost userPost : userPosts) {
                roles = iRoleService.getByUserPostId(userPost.getId(),new Role().setIsDisabled(false));
                if (!isEmpty(roles)) {
                    roleList.addAll(roles);
                }
            }
        }
        if (!isEmpty(roleList)) {
            Map<String, Role> map = new HashMap<>(roleList.size());
            for (Role role : roleList) {
                map.put(role.getId(), role);
            }
            roleList = map.values().stream().collect(Collectors.toList());
        }
        return roleList;
    }

    /**
     * 获取用户可用的功能
     * 用户 -> 功能
     * 用户 -> 角色 -> 功能
     * 用户 -> 用户岗位关系 -> 功能
     * 用户 -> 用户岗位关系 -> 角色 -> 功能
     * 用户 -> 用户岗位关系 -> 岗位 -> 功能
     * @param userId 用户id
     * @return
     */
    public List<Func> getFuncsByUserId(String userId){

        List<Func> funcList = new ArrayList<>();
        List<Func> funcs = null;
        Func funcQuery = new Func().setIsDisabled(false);

        // 用户 -> 功能
        funcs = iFuncService.getByUserId(userId, funcQuery);
        if (!isEmpty(funcs)) {
            funcList.addAll(funcs);
        }
        // 用户 -> 用户岗位关系 -> 功能
        // 用户 -> 用户岗位关系 -> 岗位 -> 功能
        List<UserPost> userPosts = iUserPostService.getByUserId(userId, null, true);
        if (!isEmpty(userPosts)) {
            for (UserPost userPost : userPosts) {
                // 用户 -> 用户岗位关系 -> 功能
                funcs = iFuncService.getByUserPostId(userPost.getId(), funcQuery);
                if (!isEmpty(funcs)) {
                    funcList.addAll(funcs);
                }
                // 用户 -> 用户岗位关系 -> 岗位 -> 功能
                funcs = iFuncService.getByPostId(userPost.getPostId(), funcQuery);
                if (!isEmpty(funcs)) {
                    funcList.addAll(funcs);
                }
            }
        }
        // 用户 -> 角色 -> 功能
        // 用户 -> 用户岗位关系 -> 角色 -> 功能
        List<Role> roles = getRolesByUserId(userId);
        if (!isEmpty(roles)) {
            for (Role role : roles) {
                funcs = iFuncService.getByRoleId(role.getId(), funcQuery);
            }
            if (!isEmpty(funcs)) {
                funcList.addAll(funcs);
            }
        }
// 去重
        if (!isEmpty(funcList)) {
            Map<String, Func> map = new HashMap<>(funcList.size());
            for (Func func : funcList) {
                map.put(func.getId(), func);
            }
            funcList = map.values().stream().collect(Collectors.toList());
        }
        return funcList;
    }
    /**
     * 获取用户可用的数据范围约束
     * 用户 -> 数据范围约束
     * 用户 -> 角色 -> 数据范围约束
     * 用户 -> 用户岗位关系 -> 数据范围约束
     * 用户 -> 用户岗位关系 -> 角色 -> 数据范围约束
     * 用户 -> 用户岗位关系 -> 岗位 -> 数据范围约束
     * @param userId 用户id
     * @return
     */
    public List<DataScope> getDataScopesByUserId(String userId){

        List<DataScope> dataScopeList = new ArrayList<>();
        List<DataScope> dataScopes = null;
        // 用户 -> 数据范围约束
        dataScopes = iDataScopeService.getByUserId(userId, null);
        if (!isEmpty(dataScopes)) {
            dataScopeList.addAll(dataScopes);
        }
        // 用户 -> 用户岗位关系 -> 数据范围约束
        // 用户 -> 用户岗位关系 -> 岗位 -> 数据范围约束
        List<UserPost> userPosts = iUserPostService.getByUserId(userId, null, true);
        if (!isEmpty(userPosts)) {
            for (UserPost userPost : userPosts) {
                // 用户 -> 用户岗位关系 -> 数据范围约束
                dataScopes = iDataScopeService.getByUserPostId(userPost.getId(), null);
                if (!isEmpty(dataScopes)) {
                    dataScopeList.addAll(dataScopes);
                }
                // 用户 -> 用户岗位关系 -> 岗位 -> 数据范围约束
                dataScopes = iDataScopeService.getByPostId(userPost.getPostId(), null);
                if (!isEmpty(dataScopes)) {
                    dataScopeList.addAll(dataScopes);
                }
            }
        }
        // 用户 -> 角色 -> 数据范围约束
        // 用户 -> 用户岗位关系 -> 角色 -> 数据范围约束
        List<Role> roles = getRolesByUserId(userId);
        if (!isEmpty(roles)) {
            for (Role role : roles) {
                dataScopes = iDataScopeService.getByRoleId(role.getId(), null);
            }
            if (!isEmpty(dataScopes)) {
                dataScopeList.addAll(dataScopes);
            }
        }
        // 去重
        if (!isEmpty(dataScopeList)) {
            Map<String, DataScope> map = new HashMap<>(dataScopeList.size());
            for (DataScope dataScope : dataScopeList) {
                map.put(dataScope.getId(), dataScope);
            }
            dataScopeList = map.values().stream().collect(Collectors.toList());
        }
        return dataScopeList;
    }

    /**
     * 判断是否超级管理员
     * @param roles 用户已分配的角色 可以调用 grape.auth.rest.user.UserLoginHelperService2#getRolesByUserId(java.lang.String) 获取
     * @return
     */
    public boolean superAdmin(List<Role> roles){
        boolean admin = false;
        if (!isEmpty(roles)) {
            for (Role role : roles) {
                if(isEqual(role.getCode(), AbstractLoginUser.superadminCode)){
                    return true;
                }
            }
        }
        return admin;
    }

    /**
     * 用户已分配的功能包括的应用
     * @param funcs 用户已分配的功能 可以调用 grape.auth.rest.user.UserLoginHelperService2#getFuncsByUserId(java.lang.String) 获取
     * @return
     */
    public List<Application> getApplications(List<Func> funcs){
        // 可用的应用们
        List<Application> applications = null;

        if (!isEmpty(funcs)) {
            // 提取applicationIds
            Set<String> applicationIds = funcs.stream().map(func -> func.getApplicationId()).collect(Collectors.toSet());
            applications = (List<Application>) iApplicationService.listByIds(applicationIds);

        }
        return applications;
    }
}
