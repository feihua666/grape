package grape.common.rest;

import grape.common.AbstractLoginUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局拦截
 * Created by yangwei
 * Created at 2019/9/27 15:46
 */
public class GlobalInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = SecurityUtils.getSubject().getSession().getAttribute(AbstractLoginUser.loginUserKey);
        if (loginUser != null && loginUser instanceof AbstractLoginUser) {
            AbstractLoginUser.setLoginUser((AbstractLoginUser) loginUser);
        }
        return super.preHandle(request, response, handler);
    }
}
