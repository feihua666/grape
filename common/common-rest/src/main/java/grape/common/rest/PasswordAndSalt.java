package grape.common.rest;

import grape.common.tools.DigestUtils;
import grape.common.tools.EncodeUtils;
import lombok.Data;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.util.ByteSource;

/**
 * 加密后的密码字符串和盐
 * Created by yw on 2016/1/13.
 */
@Data
public class PasswordAndSalt {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;


    private String password;
    private String salt;

    /**
     * 加密密码
     *
     * @param plainPassword
     * @return
     */
    public static PasswordAndSalt entryptPassword(String plainPassword) {
        PasswordAndSalt ps = new PasswordAndSalt();
        byte[] salt = DigestUtils.generateSalt(SALT_SIZE);
        ps.setSalt(EncodeUtils.encodeHex(salt));

        byte[] hashPassword = DigestUtils.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        ps.setPassword(EncodeUtils.encodeHex(hashPassword));
        return ps;
    }
    /**
     * 生成密码校验器
     *
     * @return
     */
    public static HashedCredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
        matcher.setHashIterations(HASH_INTERATIONS);
        return matcher;
    }

    /**
     * 验证密码是否正确
     * @param tokenPassword 输入的密码
     * @param ps 数据存储的密码和盐
     * @return
     */
    public static boolean validatePassword(String tokenPassword, PasswordAndSalt ps) {
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setPassword(tokenPassword.toCharArray());

        byte[] _salt = EncodeUtils.decodeHex(ps.getSalt());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(ps,
                ps.getPassword(), ByteSource.Util.bytes(_salt), "");
        return getCredentialsMatcher().doCredentialsMatch(token, info);
    }

}
