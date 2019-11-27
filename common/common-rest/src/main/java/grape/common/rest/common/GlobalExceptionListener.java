package grape.common.rest.common;

import javax.servlet.http.HttpServletRequest;

/**
 * 由来：common-rest建立之初就是提供一个基础方便的公共处理工具，被其它单点项目依赖，但想把全局异常单单独记录下来，所以加了一个接口，谁依赖谁想异步处理异常谁实现
 * 实现一般是mq发消息记录异常信息
 * Created by yangwei
 * Created at 2019/11/12 14:37
 */
public interface GlobalExceptionListener {

    public void onException(HttpServletRequest request, Exception e);
    public boolean support(HttpServletRequest request);
}
