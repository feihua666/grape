package grape.common.rest;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.Digester;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.util.ByteSource;

/**
 * 加密后的密码字符串和盐
 * Created by yw on 2016/1/13.
 */
@Data
@EqualsAndHashCode(callSuper=false)
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
        byte[] salt = RandomUtil.randomBytes(SALT_SIZE);

        ps.setSalt(new String(HexUtil.encodeHex(salt)));
        Digester sha1 = new Digester(HASH_ALGORITHM);
        sha1.setDigestCount(HASH_INTERATIONS);
        sha1.setSalt(salt);
        byte[] hashPassword = sha1.digest(plainPassword.getBytes());
        ps.setPassword(new String(HexUtil.encodeHex(hashPassword)));
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

        byte[] _salt = HexUtil.decodeHex(ps.getSalt());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(ps,
                ps.getPassword(), ByteSource.Util.bytes(_salt), "");
        return getCredentialsMatcher().doCredentialsMatch(token, info);
    }

}
