package grape.common.rest;

import cn.hutool.core.annotation.AnnotationUtil;
import grape.common.AbstractLoginUser;
import grape.common.rest.tools.RequestTool;
import grape.common.tools.RequestIdTool;
import grape.common.tools.ThreadContextTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ThreadContext;
import org.springframework.validation.BindException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 全局拦截
 * Created by yangwei
 * Created at 2019/9/27 15:46
 */
@Slf4j
public class GlobalInterceptor extends HandlerInterceptorAdapter {

    public static final String timeStartKey = "timeStartKey";
    public static final String hasExceptionKey = "hasExceptionKey";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (ThreadContext.getSecurityManager() != null) {
            Object loginUser = SecurityUtils.getSubject().getSession().getAttribute(AbstractLoginUser.loginUserKey);
            if (loginUser != null && loginUser instanceof AbstractLoginUser) {
                AbstractLoginUser.setLoginUser((AbstractLoginUser) loginUser);
            }
        }
        // 初始化请求id
        String parentId = RequestIdTool.getRequestId();
        String requestId = RequestIdTool.initRequestId();
        long start = System.currentTimeMillis();
        ThreadContextTool.put(timeStartKey,start);
        AbstractLoginUser loginUser = AbstractLoginUser.getLoginUser();
        String userId = null;
        String username = null;
        if (loginUser != null) {
            userId = loginUser.getUserId();
            username = loginUser.getNickname();
        }
        log.info("请求开始: {}requestId={},userId={},username={},requestUrl={} ,requestMethod={},requestIp={}",
                parentId == null ? "":"parentId=" + parentId + ",",
                requestId, userId,username,request.getRequestURL(),request.getMethod(), RequestTool.getRemoteAddr(request));
        if(handler instanceof HandlerMethod){
            Object annotationValue = AnnotationUtil.getAnnotationValue(((HandlerMethod) handler).getMethod(), ApiOperation.class);
            log.info("请求接口名称: requestId={},apiName={}",
                    requestId, annotationValue);
        }
        return super.preHandle(request, response, handler);
    }

    /**
     * 全局拦截完成处理
     * 这里一般不会有异常传过来，因为所有的异常都被grape.common.rest.advice.GlobalExceptionAdvice拦截处理
     * 如果这里有异常说明全局拦截异常处理没有关注该异常
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestId = RequestIdTool.getRequestId();
        String msg = "{}: requestId={},requestUrl={} ,duration={}ms";
        String msgHit = "请求正常结束";
        long start = (long) ThreadContextTool.get(timeStartKey);

        Boolean hasExceptionInGlobalExceptionAdvice = ((Boolean) ThreadContextTool.get(hasExceptionKey));
        if (ex != null || (hasExceptionInGlobalExceptionAdvice!=null && hasExceptionInGlobalExceptionAdvice)) {
            msgHit = "请求异常结束";
        }
        long interval = System.currentTimeMillis() - start;
        log.info(msg,msgHit,requestId,request.getRequestURL(),interval);

        super.afterCompletion(request, response, handler, ex);
    }
}
