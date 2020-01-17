package grape.auth.rest.user;

import grape.base.service.user.api.IUserIdentifierService;
import grape.base.service.user.api.IUserPwdService;
import grape.base.service.user.po.UserIdentifier;
import grape.base.service.user.po.UserPwd;
import grape.common.rest.security.JwtSecretTypePwdProvider;
import grape.common.tools.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 使用用户密码签名jwt token
 * Created by yangwei
 * Created at 2020/1/15 16:50
 */
@Component
public class JwtSecretProvider implements JwtSecretTypePwdProvider, ToolService {

    @Autowired
    private IUserPwdService iUserPwdService;
    @Autowired
    private IUserIdentifierService iUserIdentifierService;
    @Override
    public String getSecretByUserId(String userId) {
        if (isStrEmpty(userId)) {
            return null;
        }
        UserPwd userPwd = iUserPwdService.getByUserId(userId);
        if (userPwd != null) {
            return userPwd.getPwd();
        }
        return null;
    }

    @Override
    public String getSecretByIdentifier(String identifier) {
        if (isStrEmpty(identifier)) {
            return null;
        }
        UserIdentifier byIdentifier = iUserIdentifierService.getByIdentifier(identifier);
        if (byIdentifier != null) {
            return getSecretByUserId(byIdentifier.getUserId());
        }

        return null;
    }
}
