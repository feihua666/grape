package grape.base.service.user.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.BaseLoginUser;
import grape.base.service.comp.api.ICompService;
import grape.base.service.comp.po.Comp;
import grape.base.service.dataconstraint.api.IDataObjectService;
import grape.base.service.dataconstraint.api.IDataScopeService;
import grape.base.service.dataconstraint.dto.DataConstraintDto;
import grape.base.service.dataconstraint.po.DataObject;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.postdatascoperel.api.IPostDataScopeRelService;
import grape.base.service.postdatascoperel.po.PostDataScopeRel;
import grape.base.service.role.api.IRoleService;
import grape.base.service.role.po.Role;
import grape.base.service.roledatascoperel.api.IRoleDataScopeRelService;
import grape.base.service.roledatascoperel.po.RoleDataScopeRel;
import grape.base.service.user.api.IUserIdentifierService;
import grape.base.service.user.api.IUserPwdService;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.dto.UserCreateParam;
import grape.base.service.user.map.UserServiceMapper;
import grape.base.service.user.mapper.UserMapper;
import grape.base.service.user.po.User;
import grape.base.service.user.po.UserIdentifier;
import grape.base.service.user.po.UserPwd;
import grape.base.service.userdatascoperel.api.IUserDataScopeRelService;
import grape.base.service.userdatascoperel.po.UserDataScopeRel;
import grape.base.service.userpost.api.IUserPostService;
import grape.base.service.userpost.po.UserPost;
import grape.base.service.userpostdatascoperel.api.IUserPostDataScopeRelService;
import grape.base.service.userpostdatascoperel.po.UserPostDataScopeRel;
import grape.base.service.userpostrolerel.api.IUserPostRoleRelService;
import grape.base.service.userpostrolerel.po.UserPostRoleRel;
import grape.common.AbstractLoginUser;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.exception.runtime.RBaseException;
import grape.common.service.common.BaseServiceImpl;
import grape.common.tools.RequestIdTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台管理用户表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Slf4j
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

    @Autowired
    private IDictService iDictService;
    @Autowired
    private IUserPwdService iUserPwdService;
    @Autowired
    private UserServiceMapper userServiceMapper;
    @Autowired
    private IUserDataScopeRelService iUserDataScopeRelService;
    @Autowired
    private IRoleDataScopeRelService iRoleDataScopeRelService;
    @Autowired
    private IPostDataScopeRelService iPostDataScopeRelService;
    @Autowired
    private IUserPostDataScopeRelService iUserPostDataScopeRelService;
    @Autowired
    private IDataObjectService iDataObjectService;
    @Autowired
    private IDataScopeService iDataScopeService;

    @Override
    public BaseLoginUser initLoginUserByIdentifier(String identifier) {
        assertParamNotEmpty(identifier,"identifier 不能为空");
        UserIdentifier userIdentifier = iUserIdentifierService.getByIdentifier(identifier);
        return initLoginUserByUserId(userIdentifier.getUserId(), userIdentifier.getId(), null, null);
    }

    @Override
    public BaseLoginUser initLoginUserByUserId(String userId,String identifierId, String roleId, String postId) {

        assertParamNotEmpty(userId,"userId 不能为空");
        assertParamNotEmpty(identifierId,"identifierId 不能为空");

        BaseLoginUser loginUser = new BaseLoginUser();
        UserIdentifier userIdentifier = iUserIdentifierService.getById(identifierId);

        if(!isEqual(userId,userIdentifier.getUserId())){
            throw new InvalidParamsException("userId=["+ userId +"] 和 identifierId中获取的 userId=["+ userIdentifier.getUserId() +"] 不匹配");
        }

        // 基本信息
        User user = getById(userIdentifier.getUserId());

        assertNotNull(user,"根据userId="+ userIdentifier.getUserId() +"没有找到用户");

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

            if(!isStrEmpty(roleId)){
                Role currentRole = roles.stream().filter(item -> isEqual(item.getId(), roleId)).findFirst().orElse(null);
                if (currentRole != null) {
                    loginUser.setCurrentRole(currentRole);
                }else{
                    throw new InvalidParamsException("角色切换模式，用户分配的角色["+ roles.stream().map(item->item.getId()).collect(Collectors.joining()) +"]不包含指定的角色roleId=["+roleId+"]");
                }
            }else {
                // 默认取第一个角色作为初始角色
                loginUser.setCurrentRole(roles.get(0));
            }
            loginUser.setRoles(roles);
        }else {
            if(!isStrEmpty(roleId)){
                throw new InvalidParamsException("角色切换模式，用户没有分配角色，不能指定角色roleId=["+roleId+"]");
            }
        }

        // 担任岗位
        List<UserPost> userPosts = iUserPostService.getByUserId(userIdentifier.getUserId(),null,true);
        // 设置所有担任岗位
        loginUser.setUserPosts(userPosts);
        loginUser.setUserPostInfos(iUserPostService.getUserPostInfos(userPosts));
        if (loginUser.getIsSwitchRole() == null || !loginUser.getIsSwitchRole()) {
            // false代表需要切换岗位
            loginUser.setIsSwitchRole(false);
            if (!isListEmpty(userPosts)) {

                if(!isStrEmpty(postId)){
                    UserPost defaultUserPost = userPosts.stream().filter(userPost -> isEqual(userPost.getPostId(),postId)).findFirst().orElse(null);
                    if (defaultUserPost != null) {
                        loginUser.setCurrentUserPost(defaultUserPost);
                    }else {
                        throw new InvalidParamsException("岗位切换模式，用户分配的岗位["+ userPosts.stream().map(item->item.getPostId()).collect(Collectors.joining()) +"]不包含指定的岗位postId=["+postId+"]");
                    }

                }else{
                    // 如果没有主岗位，默认取第一个岗位
                    UserPost defaultUserPost = userPosts.stream().filter(userPost -> userPost.getIsMain()).findFirst().orElse(userPosts.get(0));
                    loginUser.setCurrentUserPost(defaultUserPost);
                }

                loginUser.setCurrentUserPostInfo(iUserPostService.getUserPostInfo(loginUser.getCurrentUserPost()));

                // 当前使用的角色从用户岗位关系而来
                UserPostRoleRel userPostRoleRel = iUserPostRoleRelService.getByUserPostId(loginUser.getCurrentUserPost().getId());
                loginUser.setCurrentUserPostRoleRel(userPostRoleRel);
                loginUser.setCurrentRole(iRoleService.getById(userPostRoleRel.getRoleId()));
            }else {
                if(!isStrEmpty(postId)){
                    throw new InvalidParamsException("岗位切换模式，用户没有分配岗位，不能指定岗位postId=["+postId+"]");
                }
            }

        }

        loginUser.setUserIdentifier(userIdentifier);
        loginUser.setIsSuperAdmin(loginUser.getCurrentRole()!=null && AbstractLoginUser.superadminCode.equals(loginUser.getCurrentRole().getCode()) );

        // 数据范围约束相关
        // 超级管理员不设置数据范围
        if(!loginUser.getIsSuperAdmin()){
            bindDataConstraint(loginUser);
        }


        return loginUser;
    }

    private void bindDataConstraint(BaseLoginUser loginUser){
        // 用户分配的数据范围
        List<DataConstraintDto> dataConstraintDtos = new ArrayList<>();
        // key=数据对象id，value=数据范围id
        Map<String, String> dataObjectAndDataScope = new HashMap<>();
        List<UserDataScopeRel> userDataScopeRels = iUserDataScopeRelService.list(Wrappers.<UserDataScopeRel>lambdaQuery().eq(UserDataScopeRel::getUserId, loginUser.getUserId()));
        if (!isListEmpty(userDataScopeRels)) {
            Map<String, String> dataObjectAndDataScopeUser = new HashMap<>();
            userDataScopeRels.forEach(item -> putMap(item.getDataObjectId(),item.getDataScopeId(),dataObjectAndDataScopeUser));
            dataConstraintDtos.addAll(convertDataConstraint(dataObjectAndDataScopeUser,DataConstraintDto.DataConstraintFrom.user));
            dataObjectAndDataScope.putAll(dataObjectAndDataScopeUser);
        }
        // 用户角色的数据范围
        // 切换角色时
        if(loginUser.getIsSwitchRole()){
            Role currentRole = loginUser.getCurrentRole();
            if(currentRole != null){
                List<RoleDataScopeRel> roleDataScopeRels = iRoleDataScopeRelService.list(Wrappers.<RoleDataScopeRel>lambdaQuery().eq(RoleDataScopeRel::getRoleId,currentRole.getId()));
                if (!isListEmpty(roleDataScopeRels)) {
                    Map<String, String> dataObjectAndDataScopeRole = new HashMap<>();
                    roleDataScopeRels.stream().filter(item -> !dataObjectAndDataScope.containsKey(item.getDataObjectId())).forEach(item ->putMap(item.getDataObjectId(), item.getDataScopeId(),dataObjectAndDataScopeRole));

                    dataConstraintDtos.addAll(convertDataConstraint(dataObjectAndDataScopeRole,DataConstraintDto.DataConstraintFrom.role));
                    dataObjectAndDataScope.putAll(dataObjectAndDataScopeRole);
                }else {
                    log.warn("当前用户初始化:requestId=[{}]切换角色模式，roleId=[{}]没有分配数据范围", RequestIdTool.getRequestId(),currentRole.getId());
                }
            }else {
                log.warn("当前用户初始化:requestId=[{}]切换角色模式没有可用角色", RequestIdTool.getRequestId());
            }

        }
        // 切换岗位时
        else {
            UserPost currentUserPost = loginUser.getCurrentUserPost();
            if (currentUserPost != null) {
                // 用户岗位关系直接分配的数据范围
                List<UserPostDataScopeRel> userPostDataScopeRels = iUserPostDataScopeRelService.list(Wrappers.<UserPostDataScopeRel>lambdaQuery().eq(UserPostDataScopeRel::getUserPostId,currentUserPost.getId()));
                if (!isListEmpty(userPostDataScopeRels)) {
                    Map<String, String> dataObjectAndDataScopeUserPost = new HashMap<>();
                    userPostDataScopeRels.stream().filter(item -> !dataObjectAndDataScope.containsKey(item.getDataObjectId())).forEach(item ->putMap(item.getDataObjectId(), item.getDataScopeId(),dataObjectAndDataScopeUserPost));
                    dataConstraintDtos.addAll(convertDataConstraint(dataObjectAndDataScopeUserPost,DataConstraintDto.DataConstraintFrom.userPost));
                    dataObjectAndDataScope.putAll(dataObjectAndDataScopeUserPost);
                }else {
                    log.warn("当前用户初始化:requestId=[{}]切换岗位模式，userPostId=[{}]没有分配数据范围，如果该关系对应的岗位没有分配数据范围，那用户将会无数据约束可用", RequestIdTool.getRequestId(),currentUserPost.getId());
                }
                // 岗位直接分配的数据范围
                List<PostDataScopeRel> postDataScopeRels = iPostDataScopeRelService.list(Wrappers.<PostDataScopeRel>lambdaQuery().eq(PostDataScopeRel::getPostId,currentUserPost.getPostId()));
                if (!isListEmpty(postDataScopeRels)) {
                    Map<String,String> dataObjectAndDataScopePost = new HashMap<>();
                    postDataScopeRels.stream().filter(item -> !dataObjectAndDataScope.containsKey(item.getDataObjectId())).forEach(item ->putMap(item.getDataObjectId(), item.getDataScopeId(),dataObjectAndDataScopePost));

                    dataConstraintDtos.addAll(convertDataConstraint(dataObjectAndDataScopePost,DataConstraintDto.DataConstraintFrom.post));
                    dataObjectAndDataScope.putAll(dataObjectAndDataScopePost);
                }else {
                    log.debug("当前用户初始化:requestId=[{}]切换岗位模式，postId=[{}]没有分配数据范围", RequestIdTool.getRequestId(),currentUserPost.getPostId());
                }
            }else {
                log.warn("当前用户初始化:requestId=[{}]切换岗位模式没有可用用户岗位关系", RequestIdTool.getRequestId());
            }
        }

    }

    private void putMap(String dataObjectId,String dataScopeId,Map<String,String>  map){

        map.put(dataObjectId,dataScopeId);

    }
    /**
     * 转为数据约束dto
     * @param dataObjectAndDataScope key为数据对象id，value为数据范围id
     * @return
     */
    private List<DataConstraintDto> convertDataConstraint(Map<String, String> dataObjectAndDataScope,DataConstraintDto.DataConstraintFrom dataConstraintFrom){
        List<DataConstraintDto> dataConstraintDtos = new ArrayList<>();
        Set<String> dataObjectIds = dataObjectAndDataScope.keySet();
        List<DataObject> dataObjects = (List<DataObject>) iDataObjectService.listByIds(dataObjectIds);
        for (DataObject dataObject : dataObjects) {
            String dataScopeId = dataObjectAndDataScope.get(dataObject.getId());
            DataConstraintDto dataConstraintDto = new DataConstraintDto(dataObject,
                   iDataScopeService.getById(dataScopeId),
                    dataConstraintFrom);
            dataConstraintDtos.add(dataConstraintDto);
        }
        return dataConstraintDtos;
    }

    @Override
    public User createUser(UserCreateParam cp) {

        //检查帐号是否存在
        if (iUserIdentifierService.getByIdentifier(cp.getAccount()) != null) {
            throw new RBaseException("帐号已存在");
        }

        User user = userServiceMapper.paramToPo(cp);
        user.setIsLock(false);
        user.setCompId(iDeptService.getById(cp.getDeptId()).getCompId());
        user = create(user);
        // 添加用户帐号
        UserIdentifier userIdentifier = new UserIdentifier();
        userIdentifier.setUserId(user.getId());
        userIdentifier.setIdentifier(cp.getAccount());
        Dict identifierDict = iDictService.getByCode(UserIdentifier.TypeDictItem.account_str.name());
        userIdentifier.setIdentityTypeDictId(identifierDict.getId());
        userIdentifier.setIsLock(false);
        iUserIdentifierService.create(userIdentifier);
        // 添加密码
        UserPwd userPwd = new UserPwd();
        userPwd.setUserId(user.getId());
        userPwd.setPwd(cp.getPassword());
        userPwd.setSalt(cp.getSalt());
        Dict pwdStatusDict = iDictService.getByCode(UserPwd.PwdStatusDictItem.normal.name());
        userPwd.setPwdStatusDictId(pwdStatusDict.getId());
        iUserPwdService.create(userPwd);
        return user;
    }
}
