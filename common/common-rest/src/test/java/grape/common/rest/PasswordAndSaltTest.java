package grape.common.rest;

import grape.common.rest.common.PasswordAndSalt;

/**
 * Created by yangwei
 * Created at 2019/9/26 16:29
 */
class PasswordAndSaltTest {

    public static void main(String[] args) {
        System.out.println(PasswordAndSalt.entryptPassword("123456"));
    }
}