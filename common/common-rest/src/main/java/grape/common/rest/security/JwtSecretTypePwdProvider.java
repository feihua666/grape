package grape.common.rest.security;

/**
 * 定义了两个获取密钥的方法，其中有一个返回不为空则终止调用
 * Created by yangwei
 * Created at 2020/1/15 15:45
 */
public interface JwtSecretTypePwdProvider {

    String getSecretByUserId(String userId);
    String getSecretByIdentifier(String identifier);

}
