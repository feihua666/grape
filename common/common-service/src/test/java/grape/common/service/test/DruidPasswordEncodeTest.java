package grape.common.service.test;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


/**
 * 数据库加密
 * Created by yangwei
 * Created at 2020/1/3 16:53
 */
public class DruidPasswordEncodeTest {

    /**
     * 实现参见 com.alibaba.druid.filter.config.ConfigTools#main(java.lang.String[])
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // 记得生成完成后，修改一下原始密码
        String password = "123456";
        String[] arr = ConfigTools.genKeyPair(512);
        System.out.println("privateKey:" + arr[0]);
        System.out.println("publicKey:" + arr[1]);
        System.out.println("password:" + ConfigTools.encrypt(arr[0], password));

        System.out.println(ConfigTools.decrypt(arr[1], ConfigTools.encrypt(arr[0], password)));
    }
}
