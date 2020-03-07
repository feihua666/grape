package grape.auth.rest.service.impl;

import grape.base.rest.client.user.UserClient;
import grape.common.AbstractLoginUser;
import grape.common.service.loginuser.LoginUser;
import grape.common.tools.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by yangwei
 * Created at 2020/1/8 17:39
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService , ToolService {

    @Autowired
    private UserClient userClient;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        boolean superAdmin = false;
        Set<String> authority = new HashSet<>();
        authority.add("user");
        List<String> roles = userClient.getRolesCodeByUserId(userId);
        roles.add("empty");
        List<String> funcs = userClient.getFuncsCodeByUserId(userId);
        funcs.add("empty");
        if (!isEmpty(roles)) {
            for (String role : roles) {
                if (isEqual(role, AbstractLoginUser.superadminCode)) {
                    superAdmin = true;
                    break;
                }
            }
            authority.addAll(roles.stream().map(role-> "ROLE_" + role).collect(Collectors.toSet()));
        }
        if (!isEmpty(funcs)) {
            authority.addAll(funcs);
        }
        LoginUser loginUser = new LoginUser(userId,
                userClient.getPwdByUserId(userId),
                true,
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList(authority.toArray(new String[0])),
                userClient.getDataScopesByUserId(userId)
        );
        loginUser.setUserId(userId);
        loginUser.setSuperAdmin(superAdmin);
        return loginUser;
    }
}
