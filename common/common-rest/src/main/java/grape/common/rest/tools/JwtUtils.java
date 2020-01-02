package grape.common.rest.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * Created by yangwei
 * Created at 2019/12/31 14:53
 */
public class JwtUtils {

    /**
     * 生成jwt token
     * @param userId 用户id
     * @param nickname 用户昵称
     * @param salt 密钥
     * @param expireTimeMs 过期时间 毫秒
     * @return 生成的token
     */
    public static String createToken(String userId,String nickname, String salt, long expireTimeMs) {
        Date date = new Date(System.currentTimeMillis()+expireTimeMs);
        Algorithm algorithm = Algorithm.HMAC256(salt);
        // 附带username信息
        return JWT.create()
                .withClaim("nickname", nickname)
                .withClaim("userId", userId)
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    /**
     * token 是否过期
     * @param token
     * @return
     */
    public static boolean isTokenExpired(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(new Date());
    }
    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getNickname(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("nickname").asString();
    }

    /**
     * 获取token中的用户id
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("userId").asString();
    }
}
