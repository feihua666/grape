package grape.auth.rest.service.impl;

import grape.auth.rest.user.UserLoginHelperService;
import grape.base.service.user.api.IUserIdentifierService;
import grape.base.service.user.api.IUserPwdService;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.po.UserIdentifier;
import grape.base.service.user.po.UserPwd;
import grape.common.service.loginuser.LoginUser;
import grape.common.tools.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yangwei
 * Created at 2020/1/8 17:39
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService , ToolService {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IUserIdentifierService iUserIdentifierService;
    @Autowired
    private IUserPwdService iUserPwdService;
    @Autowired
    private UserLoginHelperService loginUserHelperService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserIdentifier byIdentifier = iUserIdentifierService.getByIdentifier(username);
        if (byIdentifier == null) {
            throw new UsernameNotFoundException("登录名不存在");
        }
        UserPwd userPwd = iUserPwdService.getByUserId(byIdentifier.getUserId());
        if (userPwd == null) {
            throw new BadCredentialsException("该用户未设置密码不能登录");
        }
        grape.base.service.user.po.User user = iUserService.getById(byIdentifier.getUserId());
        Set<String> authority = new HashSet<>();
        authority.add("empty");// 登录只是为了获取token 设置一个占位

        LoginUser loginUser = new LoginUser(byIdentifier.getUserId(),
                userPwd.getPwd(),
                true,
                true,
                true,
                !user.getIsLock(),
                AuthorityUtils.createAuthorityList(authority.toArray(new String[0])),
                null // 登录只是为了获取token 这里不设置
        );
        loginUser.setUserId(byIdentifier.getUserId());
        return loginUser;

    }
}
