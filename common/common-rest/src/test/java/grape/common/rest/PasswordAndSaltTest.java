package grape.common.rest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by yangwei
 * Created at 2019/9/26 16:29
 */
class PasswordAndSaltTest {

    public static void main(String[] args) {
        System.out.println(PasswordAndSalt.entryptPassword("123456"));
    }
}