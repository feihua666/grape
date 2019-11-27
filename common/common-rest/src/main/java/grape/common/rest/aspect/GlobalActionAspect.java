package grape.common.rest.aspect;

import cn.hutool.json.JSONUtil;
import grape.common.rest.tools.RequestTool;
import grape.common.tools.RequestIdTool;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * interceptor 的preHandle比aspect先执行
 * Created by yangwei
 * Created at 2019/11/13 9:41
 */
@Aspect
@Component
@Slf4j
public class GlobalActionAspect {
    @Pointcut("execution(public * *..mvc.*Controller.*(..))")
    public void pointcut() {}

    /**
     * 在切点之前织入
     * @param joinPoint
     * @throws Throwable
     */
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint){
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 打印请求相关参数
        log.info("请求参数: requestId=[{}],requestParam=[{}]",
                RequestIdTool.getRequestId(), JSONUtil.toJsonStr(joinPoint.getArgs()));
        log.info("请求头: requestId=[{}],requestHeader=[{}]",
                RequestIdTool.getRequestId(), RequestTool.getHeaderText(request).toString());
        log.info("请求方法: requestId=[{}],method=[{}.{}]",
                RequestIdTool.getRequestId(),joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }
    @AfterReturning(value="pointcut()",returning="result")
    public void afterReturningMethod(JoinPoint joinPoint,Object result){
        log.info("请求结果: requestId=[{}],result=[{}]",
                RequestIdTool.getRequestId(),JSONUtil.toJsonStr(result));
    }
}
