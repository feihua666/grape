package grape.common.rest;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2019/12/31 13:36
 */
public class JwtTokenTest {
    static String secret = "secret";// token 密钥
    public static void main(String[] args) {
        verifyToken();
    }
    public static String createToken(){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        // 头部信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Date nowDate = new Date();
        Date expireDate = DateUtil.offsetHour(DateUtil.date(),2);

        String token = JWT.create()
                .withHeader(map)// 设置头部信息 Header
                .withIssuer("SERVICE")//设置 载荷 签名是有谁生成 例如 服务器
                .withSubject("this is test token")//设置 载荷 签名的主题
                // .withNotBefore(new Date())//设置 载荷 定义在什么时间之前，该jwt都是不可用的.
                .withAudience("APP")//设置 载荷 签名的观众 也可以理解谁接受签名的
                .withIssuedAt(nowDate) //设置 载荷 生成签名的时间
                .withExpiresAt(expireDate)//设置 载荷 签名过期的时间
                .sign(algorithm);//签名 Signature
        return token;
    }
    public static void verifyToken() {
        String token = createToken();
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("SERVICE").build(); // Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);

        String subject = jwt.getSubject();
        List<String> audience = jwt.getAudience();
        Map<String, Claim> claims = jwt.getClaims();
        for (Map.Entry<String, Claim> entry : claims.entrySet()) {
            String key = entry.getKey();
            Claim claim = entry.getValue();
        }
    }
}
