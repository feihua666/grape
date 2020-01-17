package grape.auth.rest.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by yangwei
 * Created at 2020/1/10 18:12
 */
public class Test {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }
}
