package grape.common.rest.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * 全局请求参数处理
 * 注意：只能使用@RequestBody接收参数才能被拦截到
 * Created by yangwei
 * Created at 2019/7/27 14:37
 */
@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class GlobalRequestBodyAdvice implements RequestBodyAdvice {

    // 禁用哪些类或注解不处理
    private static final Class[] disabledGRB = {
            DisableGRB.class
    };

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        AnnotatedElement element = methodParameter.getAnnotatedElement();
        Class containingClass = methodParameter.getContainingClass();

        return !Arrays.stream(disabledGRB).anyMatch(anno ->
                anno.isAnnotation() && (element.isAnnotationPresent(anno) || containingClass !=null && containingClass.isAnnotationPresent(anno) )
                        || anno.isAssignableFrom(containingClass)

        );
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
