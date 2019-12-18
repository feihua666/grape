package grape.base.rest.setting.shiro;


import grape.base.service.BaseLoginUser;
import grape.base.service.func.api.IFuncService;
import grape.base.service.func.po.Func;
import grape.base.service.user.api.IUserIdentifierService;
import grape.base.service.user.api.IUserPwdService;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.po.User;
import grape.base.service.user.po.UserIdentifier;
import grape.base.service.user.po.UserPwd;
import grape.common.exception.runtime.RBaseException;
import grape.common.exception.runtime.RDataNotExistException;
import grape.common.rest.common.PasswordAndSalt;
import grape.common.tools.RequestIdTool;
import grape.common.tools.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class BaseDbRealm extends AuthorizingRealm implements ToolService {

    @Autowired
    private IUserIdentifierService iUserIdentifierService;
    @Autowired
    private IUserPwdService iUserPwdService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IFuncService iFuncService;
    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected org.apache.shiro.authc.AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        IdentifierPasswordToken token = ((IdentifierPasswordToken) authcToken);

        UserIdentifier userIdentifier = iUserIdentifierService.getByIdentifier(token.getUsername());

        if(userIdentifier == null){
            return null;
        }

        User user = iUserService.getById(userIdentifier.getUserId());
        if(user.getIsLock()){
            throw new LockedAccountException();
        }

        UserPwd userPwd = iUserPwdService.getByUserId(userIdentifier.getUserId());
        if (userPwd == null) {
            throw new RDataNotExistException("用户密码数据不存在");
        }
        byte[] salt = Hex.decode(userPwd.getSalt());
        String password = userPwd.getPwd();

        return new SimpleAuthenticationInfo(userIdentifier.getUserId(),
                password, new SimpleByteSource(salt), getName());
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //principal is a userId
        Object principal = SecurityUtils.getSubject().getPrincipal();
        log.debug("获取授权信息，requestId=[{}]", RequestIdTool.getRequestId());
        if(principal != null){
            BaseLoginUser loginUser = BaseLoginUser.getLoginUser();
            if (loginUser == null) {
                return null;
            }
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Set<String> stringPermissions = new HashSet<>();
            stringPermissions.add("user");
            List<Func> funcs = loginUser.getFuncs();
            if (!isEmpty(funcs)) {
                for (Func func : funcs) {
                    String permissions = func.getPermissions();
                    if (!isStrEmpty(permissions)) {
                        String permissionArray[] = permissions.split(",");
                        for (String permission : permissionArray) {
                            stringPermissions.add(permission);
                        }
                    }
                }
            }
            info.setStringPermissions(stringPermissions);

            if (!isEmpty(loginUser.getRoles())) {
                List<String> roleCodes = loginUser.getRoles().stream().map(role -> role.getCode()).collect(Collectors.toList());
                info.setRoles(new HashSet<>(roleCodes));
            }


            return info;
        }else {
            return null;
        }

    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(Object principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    @Override
    protected Object getAuthenticationCacheKey(AuthenticationToken authcToken) {
        IdentifierPasswordToken token = ((IdentifierPasswordToken) authcToken);
        UserIdentifier userIdentifier = iUserIdentifierService.getByIdentifier(token.getUsername());

        return userIdentifier == null ? null : userIdentifier.getUserId();
    }

    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        return principals.toString();
    }

    @Override
    public Class getAuthenticationTokenClass() {
        return IdentifierPasswordToken.class;
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        super.setCredentialsMatcher(PasswordAndSalt.getCredentialsMatcher());
    }


    @Override
    public boolean isPermitted(PrincipalCollection principals, Permission permission) {
        if (BaseLoginUser.getLoginUser() != null && BaseLoginUser.getLoginUser().getIsSuperAdmin()) {
            return true;
        }
        return super.isPermitted(principals, permission);
    }

}
