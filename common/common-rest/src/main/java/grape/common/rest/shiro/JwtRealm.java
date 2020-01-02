package grape.common.rest.shiro;

import grape.common.tools.ToolService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;

/**
 * Created by yangwei
 * Created at 2019/12/31 13:06
 */
public class JwtRealm extends AuthorizingRealm implements ToolService {


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = ((JwtToken) authenticationToken);

        return null;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    @Override
    public Class getAuthenticationTokenClass() {
        return JwtToken.class;
    }

}
