package grape.common.rest.advice;

import grape.common.rest.ControllerTools;
import grape.common.rest.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

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
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice {

    private static final Class[] disabledGRM = {
            DisabledGRM.class
    };

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        AnnotatedElement element = returnType.getAnnotatedElement();
        return !Arrays.stream(disabledGRM).anyMatch(anno -> anno.isAnnotation() && element.isAnnotationPresent(anno));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResultMessage){
            return body;
        }
        return ControllerTools.newRm(body);
    }
}
