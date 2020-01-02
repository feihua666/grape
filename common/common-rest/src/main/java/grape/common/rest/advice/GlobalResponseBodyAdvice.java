package grape.common.rest.advice;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import grape.common.AbstractLoginUser;
import grape.common.rest.common.ControllerTools;
import grape.common.rest.shiro.JwtToken;
import grape.common.rest.shiro.TokenInject;
import grape.common.rest.tools.JwtUtils;
import grape.common.rest.tools.RequestTool;
import grape.common.service.trans.TransHelper;
import grape.common.tools.RequestIdTool;
import grape.common.tools.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springfox.documentation.swagger.web.ApiResourceController;
import springfox.documentation.swagger2.web.Swagger2Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

/**
 * 全局响应格式封装
 * 注意：使用@RestController注解或@ResponseBody注解才能被拦截到
 * Created by yangwei
 * Created at 2019/7/26 19:42
 */
@RestControllerAdvice
@Slf4j
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice, ToolService {


    @Autowired
    private TransHelper transHelper;


    // 禁用哪些类或注解不处理
    private static final Class[] disabledGRM = {
            DisableGRM.class,
            ApiResourceController.class, // swagger的controller 这里不拦截
            Swagger2Controller.class // swagger的controller 这里不拦截
    };

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        AnnotatedElement element = returnType.getAnnotatedElement();
        Class containingClass = returnType.getContainingClass();

        return !Arrays.stream(disabledGRM).anyMatch(anno ->
                anno.isAnnotation() && (element.isAnnotationPresent(anno) || containingClass !=null && containingClass.isAnnotationPresent(anno) )
                        || anno.isAssignableFrom(containingClass)

        );
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        // 对String 处理一下，参见：https://my.oschina.net/u/1757225/blog/1543715
        if(body instanceof String){
            return JSONUtil.toJsonStr(new JSONObject(ControllerTools.newRm(body),false));
        }
        body = transHelper.trans(body);
        // 注入token
        if(body instanceof TokenInject){
            AbstractLoginUser loginUser = AbstractLoginUser.getLoginUser();
            if (loginUser != null) {
                log.debug("requestId=[{}],注入token",RequestIdTool.getRequestId());
                String token = JwtUtils.createToken(loginUser.getUserId(), loginUser.getNickname(), loginUser.salt(), 2 * 60 * 60 * 1000);
                ((TokenInject) body).inject(token);
                if (response != null) {
                    response.getHeaders().add(JwtToken.token_header_name,token);
                }
            }else{
                log.warn("requestId=[{}],[{}]实现了TokenInject接口，但未获取到当前用户信息，未能注入jwt token", RequestIdTool.getRequestId(),body.getClass().getName());
            }

        }
        // 设置允许跨域
        fillCorsHeader(request, response);
        return ControllerTools.newRm(body);
    }

    /**
     * 添加允许跨域请求头
     * @param serverHttpRequest
     * @param serverHttpResponse
     */
    private void fillCorsHeader( ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse){
        ServletServerHttpRequest serverRequest = (ServletServerHttpRequest)serverHttpRequest;
        ServletServerHttpResponse serverResponse = (ServletServerHttpResponse)serverHttpResponse;
        if(serverRequest == null || serverResponse == null
                || serverRequest.getServletRequest() == null || serverResponse.getServletResponse() == null) {

        }

        // 对于未添加跨域消息头的响应进行处理
        HttpServletRequest request = serverRequest.getServletRequest();
        HttpServletResponse response = serverResponse.getServletResponse();
        String originHeader = "Access-Control-Allow-Origin";
        if(!response.containsHeader(originHeader)) {
            String origin = request.getHeader("Origin");
            if(origin == null) {
                String referer = request.getHeader("Referer");
                if(referer != null)
                    origin = referer.substring(0, referer.indexOf("/", 7));
            }
            response.setHeader("Access-Control-Allow-Origin", origin);
        }

        String allowHeaders = "Access-Control-Allow-Headers";
        if(!response.containsHeader(allowHeaders))
            response.setHeader(allowHeaders, request.getHeader(allowHeaders));

        String allowMethods = "Access-Control-Allow-Methods";
        if(!response.containsHeader(allowMethods))
            response.setHeader(allowMethods, "GET,POST,OPTIONS,HEAD");
        //这个很关键，要不然ajax调用时浏览器默认不会把这个token的头属性返给JS
        String exposeHeaders = "access-control-expose-headers";
        if(!response.containsHeader(exposeHeaders))
            response.setHeader(exposeHeaders, "x-auth-token");
    }
}
