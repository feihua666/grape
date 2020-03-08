package grape.common.rest.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 自定义accessToken对象，主要用来微服务自动登录
 */
public class AccessTokenAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public AccessTokenAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public AccessTokenAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
