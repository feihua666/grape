package grape.common.rest.security;

import grape.auth.rest.client.auth.AuthClient;
import grape.base.rest.client.user.UserClient;
import grape.common.AbstractLoginUser;
import grape.common.service.loginuser.LoginUser;
import grape.common.tools.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccessTokenAuthenticationProvider implements AuthenticationProvider, ToolService {

    @Autowired
    private AuthClient authClient;
    @Autowired
    private UserClient userClient;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AccessTokenAuthenticationToken accessTokenAuthenticationToken = (AccessTokenAuthenticationToken) authentication;
        Map<String, ?> checkTokenResult = authClient.checkToken(accessTokenAuthenticationToken.getPrincipal().toString());
        // 按目前（2020年3月7日 22:25:44）的版本返回数据，如果没有包含error key则认为成功
        if(!checkTokenResult.containsKey("error")){
            String userId = (String) checkTokenResult.get("user_name");
            List<String> authorities = (ArrayList) checkTokenResult.get("authorities");

            LoginUser userDetails = loadUserByUsername(userId);
            // 设置登录用户方便获取
            AbstractLoginUser.setLoginUser(userDetails);
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userId,
                            "N/A",
                            AuthorityUtils.createAuthorityList(authorities.toArray(new String[0])));
            token.setDetails(authentication.getDetails());
            SecurityContextHolder.getContext().setAuthentication(token);

            return token;

        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (AccessTokenAuthenticationToken.class
                .isAssignableFrom(authentication));
    }


    public LoginUser loadUserByUsername(String userId) throws UsernameNotFoundException {
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
