package grape.base.rest.setting.shiro;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 用户帐号，密码 token
 * Created by yw on 2016/1/15.
 */
@Data
public class IdentifierPasswordToken extends UsernamePasswordToken {


    public IdentifierPasswordToken(String username, String password){
        super(username,password);
    }
    public IdentifierPasswordToken(String username, String password, boolean rememberMe, String host){
        super(username,password,rememberMe,host);
    }
}
