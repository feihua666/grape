package grape.common.rest.security;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import grape.common.tools.ToolService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2020/1/15 9:20
 */
@ConfigurationProperties(prefix = "grape.token.jwt")
@Data
@Component
public class JwtHelper implements ToolService {

    private static String config_secret_type = "pwd";

    private static String identifier_key = "identifier";
    private static String userId_key = "userId";


    @Autowired
    private UserDetailsClient userDetailsClient;

    // 加密密钥
    private String secret;
    // 加密密钥方式，建议默认使用config_secret_type用户密码加密，这样用户修改了密码token自动失效
    private String secretType = config_secret_type;
    // 过期时长
    private Long expire;

    // 默认的头信息key value=token
    private String headerName = HttpHeaders.AUTHORIZATION;


    @Autowired(required = false)
    private JwtSecretTypePwdProvider jwtSecretTypePwdProvider;

    /**
     * 直接创建一个token
     * @param identifier 用户帐号标识，如：手机号帐号
     * @return
     */
    public String createTokenWithIdentifier(String identifier){
        return token(basicBuilder().withClaim(identifier_key, identifier));
    }

    /**
     * 直接创建一个token
     * @param userId  用户的唯一id
     * @return
     */
    public String createTokenWithUserId(String userId){
        return token(basicBuilder().withClaim(userId_key, userId));
    }

    /**
     * 同时带用户id和标识
     * @param userId
     * @param identifier
     * @return
     */
    public String createToken(String userId,String identifier){
        return token(basicBuilder().withClaim(identifier_key, identifier).withClaim(userId_key, userId));
    }
    /**
     * 获取identifier
     * @param token
     * @return
     */
    public String getIdentifier(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim(identifier_key).asString();
    }

    /**
     * 获取用户id
     * @param token
     * @return
     */
    public String getUserId(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim(userId_key).asString();
    }
    /**
     *  基本的builder 主要方便可以自定义claim
     * @return
     */
    public JWTCreator.Builder basicBuilder() {
        Date date = new Date(System.currentTimeMillis() + expire);
        JWTCreator.Builder builder = JWT.create()
                .withExpiresAt(date)
                .withIssuedAt(new Date());
        return builder;
    }

    /**
     * 添加负载 以一个对象添加整个负载，暂时想到的不常用
     * @param obj
     * @param builder
     * @return
     */
    public JWTCreator.Builder payload(Object obj, JWTCreator.Builder builder) {
        return builder.withClaim("payload", JSONUtil.toJsonStr(obj));
    }

    /**
     * 签名，获取token
     * @param builder
     * @return
     */
    public String token(JWTCreator.Builder builder){
        // 先临时随机一个生成一个临时token 再从token中获取userId和identifier
        String tempToken =  builder.sign(Algorithm.HMAC256(RandomUtil.randomString(5)));
        Algorithm algorithm = Algorithm.HMAC256(getSecret(getUserId(tempToken),getIdentifier(tempToken)));
        return builder.sign(algorithm);
    }
    /**
     * 获取claim
     * @param token
     * @param key
     * @return
     */
    public Claim getClaim(String token, String key) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(key);
    }

    /**
     * 获取所有添加的claim
     * @param token
     * @return
     */
    public Map<String, Claim>  getClaims(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaims();
    }
    /**
     * 验证token
     * @param token
     * @return
     */
    public boolean verifyToken(String token)  throws JWTVerificationException {

        Algorithm algorithm = Algorithm.HMAC256(getSecret(getUserId(token),getIdentifier(token)));
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(token);

        return true;
    }
    /**
     * token 是否过期
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(new Date());
    }


    private String getSecret(String userId,String identifier){
        String secret = null;
        if (isEqual(config_secret_type,secretType)) {
            if (jwtSecretTypePwdProvider == null) {
                if (!isStrEmpty(userId)) {
                    secret = userDetailsClient.getPwdByUserId(userId);
                }
                if (isStrEmpty(secret) && !isStrEmpty(identifier)) {
                    secret = jwtSecretTypePwdProvider.getSecretByIdentifier(identifier);
                }
                //throw new RuntimeException("配置错误，配置了使用用户密码加密token但没实现grape.common.rest.security.JwtSecretTypePwdProvider");
            }else{
                if (!isStrEmpty(userId)) {
                    secret = jwtSecretTypePwdProvider.getSecretByUserId(userId);
                }
                if (isStrEmpty(secret) && !isStrEmpty(identifier)) {
                    secret = jwtSecretTypePwdProvider.getSecretByIdentifier(identifier);
                }
            }

            if (isStrEmpty(secret)){
                throw new RuntimeException("数据错误，配置了使用用户密码加密token但没实现但未获取到密钥，userId=["+ userId +"],identifier=["+ identifier +"]");
            }
            return secret;
        }
        return this.secret;
    }
}
