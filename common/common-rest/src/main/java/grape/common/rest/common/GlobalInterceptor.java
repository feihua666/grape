package grape.common.rest.common;

import cn.hutool.core.annotation.AnnotationUtil;
import grape.common.AbstractLoginUser;
import grape.common.rest.security.AccessTokenAuthenticationToken;
import grape.common.rest.tools.RequestTool;
import grape.common.service.loginuser.GrapeUserDetails;
import grape.common.tools.RequestIdTool;
import grape.common.tools.ThreadContextTool;
import grape.common.tools.ToolService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局拦截
 * Created by yangwei
 * Created at 2019/9/27 15:46
 */
@Slf4j
public class GlobalInterceptor extends HandlerInterceptorAdapter implements ToolService {

    // 计时器key
    public static final String timeStartKey = "timeStartKey";
    // 记录是否有异常key
    public static final String hasExceptionKey = "hasExceptionKey";
    /**
     * Authorization认证开头是"bearer "
     */
    private static final String BEARER = "Bearer ";
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 初始化请求id
        String requestId = RequestIdTool.initRequestId(request.getHeader(RequestIdTool.reqeustIdKey));

        long start = System.currentTimeMillis();
        ThreadContextTool.put(timeStartKey,start);
        // 尝试认证用户
        tryAuthentication(request, response);


        GrapeUserDetails loginUser = AbstractLoginUser.getLoginUser();
        String userId = null;
        String username = null;
        if (loginUser != null) {
            userId = loginUser.userId();
        }
        log.info("请求开始: requestId=[{}],userId=[{}],username=[{}],requestUrl=[{}] ,requestMethod=[{}],requestIp=[{}]",
                requestId, userId,username,request.getRequestURL(),request.getMethod(), RequestTool.getRemoteAddr(request));
        if(handler instanceof HandlerMethod){
            Object annotationValue = AnnotationUtil.getAnnotationValue(((HandlerMethod) handler).getMethod(), ApiOperation.class);
            log.info("请求接口名称: requestId=[{}],apiName=[{}]",
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
        String msg = "{}: requestId=[{}],requestUrl=[{}] ,duration=[{}]ms";
        String msgHit = "请求正常结束";
        long start = (long) ThreadContextTool.get(timeStartKey);

        Boolean hasExceptionInGlobalExceptionAdvice = ((Boolean) ThreadContextTool.get(hasExceptionKey));
        if (ex != null || (hasExceptionInGlobalExceptionAdvice != null && hasExceptionInGlobalExceptionAdvice)) {
            msgHit = "请求异常结束";
        }
        long interval = System.currentTimeMillis() - start;
        log.info(msg,msgHit,requestId,request.getRequestURL(),interval);


        ThreadContextTool.remove();
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     *
     * @param request
     * @param response
     */
    private void tryAuthentication(HttpServletRequest request, HttpServletResponse response){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // 如果没有被认证，尝试认证
            if (authentication == null || !authentication.isAuthenticated()) {
                String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
                if(!isStrEmpty(headerAuth)){
                    log.info("尝试认证：requestId=[{}],headerAuth=[{}]",RequestIdTool.getRequestId(),headerAuth);

                    if(isStrStart(headerAuth,BEARER)){
                        String accessToken = headerAuth.substring(BEARER.length());
                        AccessTokenAuthenticationToken accessTokenAuthenticationToken = new AccessTokenAuthenticationToken(accessToken, "N/A");
                        authenticationManager.authenticate(accessTokenAuthenticationToken);
                       //AbstractLoginUser.setLoginUser(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

                    }
                }
            }
        }catch (Exception e){
            log.error("尝试认证失败: requestId=[{}]",
                    RequestIdTool.getRequestId(), e);
        }

    }
}
