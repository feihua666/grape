package grape.common.rest.advice;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import grape.common.rest.common.ControllerTools;
import grape.common.service.trans.TransHelper;
import grape.common.tools.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springfox.documentation.swagger.web.ApiResourceController;
import springfox.documentation.swagger2.web.Swagger2Controller;

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
        return ControllerTools.newRm(body);
    }
}
