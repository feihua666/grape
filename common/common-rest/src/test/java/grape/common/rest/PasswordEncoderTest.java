package grape.common.rest;

import cn.hutool.core.util.ZipUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by yangwei
 * Created at 2020/1/13 16:47
 */
public class PasswordEncoderTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
        byte[] gzip = ZipUtil.gzip("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NzkxNzAxMjYsImlhdCI6MTU3OTE2MTEyNiwidXNlcklkIjoiMiJ9.oPDXVGRdMLiwl1GEXX-UZY-2IHVGIrO3hXaBWqGL08U", "utf-8");
        String s = new String(gzip, "utf-8");
        byte[] bytes = ZipUtil.unGzip(gzip);
        String s1 = new String(bytes, "utf-8");
        System.out.println(s1);

    }
}
