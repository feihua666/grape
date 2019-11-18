package grape.common.rest.advice;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.common.rest.ControllerTools;
import grape.common.rest.ResultMessage;
import grape.common.rest.vo.BaseVo;
import grape.common.service.ITransService;
import grape.common.service.Trans;
import grape.common.tools.RequestIdTool;
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
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * 全局响应格式封装
 * 注意：使用@RestController注解或@ResponseBody注解才能被拦截到
 * Created by yangwei
 * Created at 2019/7/26 19:42
 */
@RestControllerAdvice
@Slf4j
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice, ToolService {


    @Autowired(required = false)
    private List<ITransService> transServices;


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
        // 翻译支持
        /*if (!isListEmpty(transServices)){
            if (body instanceof IPage) {
                List records = ((IPage) body).getRecords();
                if (!isListEmpty(records)) {
                    for (Object record : records) {
                        trans(record);
                    }
                }
            }else {
                trans(body);
            }
        }*/
        return ControllerTools.newRm(body);
    }

    private void trans(Object body){
        // 还可以添加一个TransPower注解用在需要翻译的类上，来标识该类的属性是否需要翻译，来加速性能，暂时未实现
        for (Field field : ReflectUtil.getFields(body.getClass())) {
            Trans trans = AnnotationUtil.getAnnotation(field, Trans.class);
            if (trans != null) {
                for (ITransService transService : transServices) {
                    if (transService.support(trans.type())) {
                        Object fieldValue = ReflectUtil.getFieldValue(body, trans.keyFieldName());
                        if (fieldValue != null) {
                            Object transValue = transService.trans(fieldValue);
                            ReflectUtil.setFieldValue(body,field,transValue);
                        }
                        break;
                    }
                }
            }
        }
    }
}
