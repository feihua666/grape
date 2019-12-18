package grape.base.service.user.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.BaseLoginUser;
import grape.base.service.application.api.IApplicationService;
import grape.base.service.application.po.Application;
import grape.base.service.comp.api.ICompService;
import grape.base.service.comp.po.Comp;
import grape.base.service.dataconstraint.api.IDataObjectService;
import grape.base.service.dataconstraint.api.IDataScopeService;
import grape.base.service.dataconstraint.dto.DataConstraintDto;
import grape.base.service.dataconstraint.dto.DataObjectAndScopeDto;
import grape.base.service.dataconstraint.po.DataObject;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.func.api.IFuncService;
import grape.base.service.func.dto.FuncAssignDto;
import grape.base.service.func.po.Func;
import grape.base.service.postdatascoperel.api.IPostDataScopeRelService;
import grape.base.service.postdatascoperel.po.PostDataScopeRel;
import grape.base.service.role.api.IRoleService;
import grape.base.service.role.po.Role;
import grape.base.service.roledatascoperel.api.IRoleDataScopeRelService;
import grape.base.service.roledatascoperel.po.RoleDataScopeRel;
import grape.base.service.rolefuncrel.api.IRoleFuncRelService;
import grape.base.service.rolefuncrel.po.RoleFuncRel;
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
import grape.base.service.userfuncrel.api.IUserFuncRelService;
import grape.base.service.userfuncrel.po.UserFuncRel;
import grape.base.service.userpost.api.IUserPostService;
import grape.base.service.userpost.dto.UserPostInfo;
import grape.base.service.userpost.po.UserPost;
import grape.base.service.userpostdatascoperel.api.IUserPostDataScopeRelService;
import grape.base.service.userpostdatascoperel.po.UserPostDataScopeRel;
import grape.base.service.userpostrolerel.api.IUserPostRoleRelService;
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
    private IUserIdentifierService iUserIdentifierService;
    @Autowired
    private UserServiceMapper userServiceMapper;
    @Autowired
    private IDeptService iDeptService;
    @Autowired
    private IDictService iDictService;
    @Autowired
    private IUserPwdService iUserPwdService;
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
