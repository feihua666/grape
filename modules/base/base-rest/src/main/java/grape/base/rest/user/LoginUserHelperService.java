package grape.base.rest.user;

import grape.base.service.BaseLoginUser;
import grape.base.service.application.api.IApplicationService;
import grape.base.service.application.po.Application;
import grape.base.service.comp.api.ICompService;
import grape.base.service.comp.po.Comp;
import grape.base.service.dataconstraint.api.IDataObjectService;
import grape.base.service.dataconstraint.api.IDataScopeService;
import grape.base.service.dataconstraint.dto.DataConstraintDto;
import grape.base.service.dataconstraint.dto.DataObjectAndScopeDto;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.dict.api.IDictService;
import grape.base.service.func.api.IFuncService;
import grape.base.service.func.dto.FuncAssignDto;
import grape.base.service.func.po.Func;
import grape.base.service.postdatascoperel.api.IPostDataScopeRelService;
import grape.base.service.role.api.IRoleService;
import grape.base.service.role.po.Role;
import grape.base.service.roledatascoperel.api.IRoleDataScopeRelService;
import grape.base.service.rolefuncrel.api.IRoleFuncRelService;
import grape.base.service.user.api.IUserIdentifierService;
import grape.base.service.user.api.IUserPwdService;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.po.User;
import grape.base.service.user.po.UserIdentifier;
import grape.base.service.userdatascoperel.api.IUserDataScopeRelService;
import grape.base.service.userfuncrel.api.IUserFuncRelService;
import grape.base.service.userpost.api.IUserPostService;
import grape.base.service.userpost.dto.UserPostInfo;
import grape.base.service.userpost.po.UserPost;
import grape.base.service.userpostdatascoperel.api.IUserPostDataScopeRelService;
import grape.base.service.userpostrolerel.api.IUserPostRoleRelService;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.tools.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yangwei
 * Created at 2019/12/18 14:22
 */
@Component
public class LoginUserHelperService implements ToolService {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IDeptService iDeptService;
    @Autowired
    private ICompService iCompService;
    @Autowired
    private IUserPostService iUserPostService;
    @Autowired
    private IUserIdentifierService iUserIdentifierService;

    @Autowired
    private IDataObjectService iDataObjectService;
    @Autowired
    private IDataScopeService iDataScopeService;

    @Autowired
    private IApplicationService iApplicationService;
    @Autowired
    private IFuncService iFuncService;
    /**
     * 初始化当前登录用户信息用户信息
     * 一般登录成功后调用
     *
     * @param identifier
     * @return
     */
    public BaseLoginUser initLoginUserByIdentifier(String identifier) {
        assertParamNotEmpty(identifier,"identifier 不能为空");
        UserIdentifier userIdentifier = iUserIdentifierService.getByIdentifier(identifier);
        return initLoginUserByUserId(userIdentifier.getUserId(), userIdentifier.getId());
    }

    /**
     * 初始化当前登录用户信息用户信息
     * 一般切换角色或切换岗位调用，也可作为初始化当前用户使用
     * 说明:
     * 当前登录用户无非三种信息，1.基本属性信息，2.功能权限信息，3，数据范围约束信息。基本信息没什么可说的，下面着重说明2和3两种信息
     * 一般来说功能权限信息和数据范围约束就是两个东西，但一般又没有功能操作不了数据，这又使两者联系起来了
     * 先说一下用户到功能/数据范围约束的路径
     * 用户 -> 功能/数据范围约束
     * 用户 -> 角色 -> 功能/数据范围约束
     * 用户 -> 用户岗位关系 -> 功能/数据范围约束
     * 用户 -> 用户岗位关系 -> 角色 -> 功能/数据范围约束
     * 用户 -> 用户岗位关系 -> 岗位 -> 功能/数据范围约束
     * 原则：以最短路径优先原则，如果路径长度相等，以上面的顺序优先
     * 如：如果用户直接分配了功能/数据范围约束，那么用户的可用功能/数据范围约束就直接来自用户关系以此类推就行
     * 遇到的问题：除非在同一路径的同一节点上同时分配功能和数据范围约束，要不然会出现下面的不确定情况：
     * 如：如果用户只直接分配了功能，那用户想拥有数据范围约束就可选了（可以给用户分配一个角色而这个角色分配了数据范围约束，也可以给用户分配一个用户岗位而这个用户岗位分配了数据范围约束）
     * 那么问题是分配的功能和数据范围可能不匹配（表现在分配了没有分配的功能的数据范围约束，导致可用的功能没有数据，虽然在分配数据范围约束的时候根据可用的功能限定了范围，但这也是不可控的）
     * 这时候到底是根据数据范围约束切换角色或岗位还是根据功能切换角色或岗位呢。
     * 不纠结了，又不想规定必须在同一个路径节点上同时分配功能和数据范围约束，就取按可用的功能来进行切换
     * 其它应该说不管是切换岗位或是切换角色其应该是本质上切换的功能
     * 决定不再提供切换功能，而是将功能揉合在一起，数据范围约束的每一个数据对象按上面的路径原则提取
     * @param userId 必填
     * @param identifierId 必填 标识用户的登录信息，是以什么帐号方式登录的
     * @return
     */
    public BaseLoginUser initLoginUserByUserId(String userId,String identifierId) {

        assertParamNotEmpty(userId,"userId 不能为空");
        assertParamNotEmpty(identifierId,"identifierId 不能为空");

        BaseLoginUser loginUser = new BaseLoginUser();
        UserIdentifier userIdentifier = iUserIdentifierService.getById(identifierId);

        if(!isEqual(userId,userIdentifier.getUserId())){
            throw new InvalidParamsException("userId=["+ userId +"] 和 identifierId中获取的 userId=["+ userIdentifier.getUserId() +"] 不匹配");
        }
        // 基本信息
        User user = iUserService.getById(userIdentifier.getUserId());
        assertNotNull(user,"根据userId="+ userIdentifier.getUserId() +"没有找到用户");
        loginUser.setUserId(userIdentifier.getUserId());
        loginUser.setUserIdentifier(userIdentifier);
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

        // 设置角色
        setUserRoles(loginUser);
        // 设置岗位
        setUserPostRelations(loginUser);
        // 设置超级管理员
        setSuperAdmin(loginUser);


        // 用户功能与数据范围
        bindUserFunc(loginUser);
        bindUserDataConstraint(loginUser);
        // 用户角色功能与数据范围
        bindUserRolesFunc(loginUser);
        bindUserRolesDataConstraint(loginUser);
        // 用户岗位关系功能与数据范围
        bindUserPostRelationsFunc(loginUser);
        bindUserPostRelationsDataConstraint(loginUser);
        // 用户岗位关系关联的角色功能与数据范围
        bindUserPostRelationsRolesFunc(loginUser);
        bindUserPostRelationsRolesDataConstraint(loginUser);
        //用户岗位关系对应的岗位功能与数据范围
        bindUserPostRelationsPostFunc(loginUser);
        bindUserPostRelationsPostDataConstraint(loginUser);


        // 数据范围约束相关
        // 超级管理员不设置数据范围约束
        setFuncs(loginUser);
        // 设置应用
        setApplications(loginUser);
        // 设置数据范围约束
        setDataObjectAndScopes(loginUser);
        return loginUser;
    }

    /**
     * 设置超级管理员
     * @param loginUser
     */
    private void setSuperAdmin(BaseLoginUser loginUser){
        // 超级管理员设置
        loginUser.setIsSuperAdmin(false);
        List<Role> roles = loginUser.getRoles();
        if (isIncludeSuperAdmin(roles)) {
            loginUser.setIsSuperAdmin(true);
        }
        if(!loginUser.getIsSuperAdmin()){
            List<UserPostInfo> userPostInfos = loginUser.getUserPostInfos();
            if (!isEmpty(userPostInfos)) {
                for (UserPostInfo userPostInfo : userPostInfos) {
                    if (isIncludeSuperAdmin(userPostInfo.getRoles())) {
                        loginUser.setIsSuperAdmin(true);
                        break;
                    }
                }
            }
        }

    }

    /**
     * 角色中是否包含超级管理员
     * @param roles
     * @return
     */
    private boolean isIncludeSuperAdmin(List<Role> roles){
        if (!isEmpty(roles)) {
            for (Role role : roles) {
                if(isEqual(role.getCode(),BaseLoginUser.superadminCode)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 添加功能分配数据
     * @param loginUser
     * @param funcAssignDto
     */
    private void addFuncAssign(BaseLoginUser loginUser, FuncAssignDto funcAssignDto){
        List<FuncAssignDto> funcAssigns = loginUser.getFuncAssigns();
        if (funcAssigns == null) {
            loginUser.setFuncAssigns(new ArrayList<>());
            addFuncAssign(loginUser, funcAssignDto);
        }else {
            funcAssigns.add(funcAssignDto);
        }
    }

    /**
     * 添加功能分配数据
     * @param loginUser
     * @param funcFrom
     * @param funcs
     */
    private void addFuncAssignDto(BaseLoginUser loginUser,FuncAssignDto.FuncFrom funcFrom,List<Func> funcs){
        if (!isEmpty(funcs)) {
            FuncAssignDto funcAssignDto = new FuncAssignDto();
            funcAssignDto.setFuncs(funcs);
            funcAssignDto.setFuncFrom(FuncAssignDto.FuncFrom.user);
            addFuncAssign(loginUser, funcAssignDto);
        }
    }

    /**
     * 添加数据范围约束
     * @param loginUser
     * @param dataConstraintFrom
     * @param dataScopes
     */
    private void addDataConstraintAssignDto(BaseLoginUser loginUser, DataConstraintDto.DataConstraintFrom dataConstraintFrom, List<DataScope> dataScopes){
        if (!isEmpty(dataScopes)) {
            DataObjectAndScopeDto dataObjectAndScopeDto = null;
            List<DataObjectAndScopeDto> dataObjectAndScopeDtos = new ArrayList<>(dataScopes.size());
            for (DataScope dataScope : dataScopes) {
                dataObjectAndScopeDto = new DataObjectAndScopeDto();
                dataObjectAndScopeDto.setDataObject(iDataObjectService.getById(dataScope.getDataObjectId()));
                dataObjectAndScopeDto.setDataScope(dataScope);
                dataObjectAndScopeDtos.add(dataObjectAndScopeDto);
            }
            DataConstraintDto dataConstraintDto = new DataConstraintDto();
            dataConstraintDto.setDataObjectAndScopeDtos(dataObjectAndScopeDtos);
            dataConstraintDto.setDataConstraintFrom(dataConstraintFrom);
            addDataConstraintAssign(loginUser,dataConstraintDto);
        }
    }
    /**
     * 添加数据约束分配的数据
     * @param loginUser
     * @param dataConstraintDto
     */
    private void addDataConstraintAssign(BaseLoginUser loginUser, DataConstraintDto dataConstraintDto){
        List<DataConstraintDto> dataConstraintAssigns = loginUser.getDataConstraintAssigns();
        if (dataConstraintAssigns == null) {
            loginUser.setDataConstraintAssigns(new ArrayList<>());
            addDataConstraintAssign(loginUser, dataConstraintDto);
        }else {
            dataConstraintAssigns.add(dataConstraintDto);
        }
    }
    /**
     * 用户直接分配的功能
     * @param loginUser
     */
    private void bindUserFunc(BaseLoginUser loginUser){
        Func funcQuery = new Func().setIsDisabled(false);
        List<Func> funcs = iFuncService.getByUserId(loginUser.getUserId(), funcQuery);
        addFuncAssignDto(loginUser, FuncAssignDto.FuncFrom.user, funcs);
    }
    /**
     * 用户直接分配的数据范围约束
     * @param loginUser
     */
    private void bindUserDataConstraint(BaseLoginUser loginUser){
        List<DataScope> dataScopes = iDataScopeService.getByUserId(loginUser.getUserId(), null);
        addDataConstraintAssignDto(loginUser,DataConstraintDto.DataConstraintFrom.user,dataScopes);
    }
    /**
     * 用户分配的角色中分配的功能
     * @param loginUser
     */
    private void bindUserRolesFunc(BaseLoginUser loginUser){
        List<Role> roles = loginUser.getRoles();
        if (!isEmpty(roles)) {
            Func func = new Func().setIsDisabled(false);
            for (Role role : roles) {
                List<Func> funcs = iFuncService.getByRoleId(role.getId(), func);
                addFuncAssignDto(loginUser, FuncAssignDto.FuncFrom.role,funcs);
            }
        }
    }

    /**
     * 用户分配的角色中分配的数据范围
     * @param loginUser
     */
    private void bindUserRolesDataConstraint(BaseLoginUser loginUser){
        List<Role> roles = loginUser.getRoles();
        if (!isEmpty(roles)) {
            for (Role role : roles) {
                List<DataScope> dataScopes = iDataScopeService.getByRoleId(role.getId(), null);
                addDataConstraintAssignDto(loginUser,DataConstraintDto.DataConstraintFrom.role,dataScopes);

            }
        }

    }
    /**
     * 用户分配的岗位关系直接分配的功能
     * @param loginUser
     */
    private void bindUserPostRelationsFunc(BaseLoginUser loginUser){
        List<UserPost> userPosts = loginUser.getUserPosts();
        if (!isEmpty(userPosts)) {
            List<Func> funcs = null;
            Func func = new Func().setIsDisabled(false);
            for (UserPost userPost : userPosts) {
                funcs = iFuncService.getByUserPostId(userPost.getId(), func);
                addFuncAssignDto(loginUser, FuncAssignDto.FuncFrom.userPost,funcs);
            }
        }
    }
    /**
     * 用户分配的岗位关系直接分配的数据范围约束
     * @param loginUser
     */
    private void bindUserPostRelationsDataConstraint(BaseLoginUser loginUser){
        List<UserPost> userPosts = loginUser.getUserPosts();
        if (!isEmpty(userPosts)) {
            List<DataScope> dataScopes = null;
            for (UserPost userPost : userPosts) {
                dataScopes = iDataScopeService.getByUserPostId(userPost.getId(), null);
                addDataConstraintAssignDto(loginUser, DataConstraintDto.DataConstraintFrom.userPost,dataScopes);
            }
        }
    }
    /**
     * 用户分配的岗位关系分配角色分配的功能
     * @param loginUser
     */
    private void bindUserPostRelationsRolesFunc(BaseLoginUser loginUser){
        List<UserPostInfo> userPosts = loginUser.getUserPostInfos();
        if (!isEmpty(userPosts)) {
            Func func = new Func().setIsDisabled(false);
            List<Func> funcs = null;
            for (UserPostInfo userPost : userPosts) {
                List<Role> roles = userPost.getRoles();
                if (!isEmpty(roles)) {
                    for (Role role : roles) {
                       funcs = iFuncService.getByRoleId(role.getId(), func);
                        addFuncAssignDto(loginUser, FuncAssignDto.FuncFrom.userPostRole,funcs);
                    }
                }
            }
        }
    }
    /**
     * 用户分配的岗位关系分配角色分配的功能
     * @param loginUser
     */
    private void bindUserPostRelationsRolesDataConstraint(BaseLoginUser loginUser){
        List<UserPostInfo> userPosts = loginUser.getUserPostInfos();
        if (!isEmpty(userPosts)) {
            List<DataScope> dataScopes = null;
            for (UserPostInfo userPost : userPosts) {
                List<Role> roles = userPost.getRoles();
                if (!isEmpty(roles)) {
                    for (Role role : roles) {
                        dataScopes = iDataScopeService.getByRoleId(role.getId(), null);
                        addDataConstraintAssignDto(loginUser,DataConstraintDto.DataConstraintFrom.userPostRole,dataScopes);
                    }
                }
            }
        }
    }
    /**
     * 用户分配的岗位关系中的岗位分配的功能
     * @param loginUser
     */
    private void bindUserPostRelationsPostFunc(BaseLoginUser loginUser){
        List<UserPost> userPosts = loginUser.getUserPosts();
        if (!isEmpty(userPosts)) {
            List<Func> funcs = null;
            Func func = new Func().setIsDisabled(false);
            for (UserPost userPost : userPosts) {
                funcs = iFuncService.getByPostId(userPost.getPostId(), func);
                addFuncAssignDto(loginUser, FuncAssignDto.FuncFrom.userPost,funcs);
            }
        }
    }
    /**
     * 用户分配的岗位关系中的岗位分配的数据范围约束
     * @param loginUser
     */
    private void bindUserPostRelationsPostDataConstraint(BaseLoginUser loginUser){
        List<UserPost> userPosts = loginUser.getUserPosts();
        if (!isEmpty(userPosts)) {
            List<DataScope> dataScopes = null;
            for (UserPost userPost : userPosts) {
                dataScopes = iDataScopeService.getByPostId(userPost.getPostId(), null);
                addDataConstraintAssignDto(loginUser, DataConstraintDto.DataConstraintFrom.userPostPost,dataScopes);
            }
        }
    }
    /**
     * 用户分配的岗位关系及岗位关系分配的角色
     * @param loginUser
     */
    private void setUserPostRelations(BaseLoginUser loginUser){

        // 担任岗位
        List<UserPost> userPosts = iUserPostService.getByUserId(loginUser.getUserId(),null,true);
        // 设置所有担任岗位
        loginUser.setUserPosts(userPosts);
        loginUser.setUserPostInfos(iUserPostService.getUserPostInfos(userPosts));
    }

    /**
     * 用户直接分配的角色
     * @param loginUser
     */
    private void setUserRoles(BaseLoginUser loginUser){

        // 用户直接关联的角色
        List<Role> roles = iRoleService.getByUserId(loginUser.getUserId(),new Role().setIsDisabled(false));
        loginUser.setRoles(roles);
    }
    /**
     * 绑定可用的应用
     * @param loginUser
     */
    private void setApplications(BaseLoginUser loginUser){
        // 可用的应用们
        List<Application> applications = null;

        if (loginUser.getIsSuperAdmin()) {
            applications = iApplicationService.list();

        }else {
            // 当前用户分配的功能
            List<Func> funcList = loginUser.getFuncs();
            if (!isEmpty(funcList)) {
                // 提取applicationIds
                Set<String> applicationIds = funcList.stream().map(func -> func.getApplicationId()).collect(Collectors.toSet());
                applications = (List<Application>) iApplicationService.listByIds(applicationIds);
            }
        }

        loginUser.setApplications(applications);
    }

    /**
     * 提取可用的功能于一个数组
     * @param loginUser
     */
    private void setFuncs(BaseLoginUser loginUser){
        List<FuncAssignDto> funcAssigns = loginUser.getFuncAssigns();
        if (!isEmpty(funcAssigns)) {
            Map<String, Func> map = new HashMap<>();
            List<Func> funcs = null;
            for (FuncAssignDto funcAssign : funcAssigns) {
                funcs = funcAssign.getFuncs();
                if (!isEmpty(funcs)) {
                    for (Func func : funcs) {
                        map.put(func.getId(), func);
                    }
                }
            }
            if (!map.isEmpty()) {
                loginUser.setFuncs(map.values().stream().collect(Collectors.toList()));
            }
        }
    }
    /**
     * 绑定数据约束
     * @param loginUser
     */
    private void setDataObjectAndScopes(BaseLoginUser loginUser){
        List<DataConstraintDto> dataConstraintAssigns = loginUser.getDataConstraintAssigns();
        if (!isEmpty(dataConstraintAssigns)) {
            // 按order 从大到小排序
            dataConstraintAssigns.sort(Comparator.comparing(DataConstraintDto::getOrder).reversed());
            Map<String,DataObjectAndScopeDto> map = new HashMap<>();
            for (DataConstraintDto dataConstraintAssign : dataConstraintAssigns) {
                List<DataObjectAndScopeDto> dataObjectAndScopeDtos = dataConstraintAssign.getDataObjectAndScopeDtos();
                if (!isEmpty(dataObjectAndScopeDtos)) {
                    for (DataObjectAndScopeDto dataObjectAndScopeDto : dataObjectAndScopeDtos) {
                        map.put(dataObjectAndScopeDto.getDataScope().getId(), dataObjectAndScopeDto);
                    }
                }

            }
            if (!map.isEmpty()) {
                loginUser.setDataObjectAndScopes(map.values().stream().collect(Collectors.toList()));
            }
        }

    }

}
