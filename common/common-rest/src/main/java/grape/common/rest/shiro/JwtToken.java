package grape.common.rest.shiro;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.HostAuthenticationToken;

/**
 * Created by yangwei
 * Created at 2019/12/31 13:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class JwtToken implements HostAuthenticationToken {

    public static String token_header_name = "x-auth-token";

    private String token;
    private String host;

    public JwtToken(String token) {
        this(token, null);
    }

    public JwtToken(String token, String host) {
        this.token = token;
        this.host = host;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
    @Override
    public String toString(){
        return token + ':' + host;
    }
}
