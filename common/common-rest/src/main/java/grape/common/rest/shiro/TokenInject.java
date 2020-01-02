package grape.common.rest.shiro;

/**
 * 标识一个token注入接口，主要用来注入token
 * 主要是为了解藕
 * Created by yangwei
 * Created at 2019/12/31 14:25
 */
public interface TokenInject {
    public void inject(String token);
}
