package grape.common.rest.mvc;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 全局异常处理，当异常被@ControllerAdvice时不会走到这个处理类，没被处理时会走到这里
 *
 */
//@RestController
@ApiIgnore
@Slf4j
public class GolablExceptionEndpoint implements ErrorController {

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return PATH;
    }

    /**
     * 全局异常处理
     * 增加@ResponseStatus注解将非200的http状态码转成200
     * @return
     */
    @RequestMapping(value = PATH)
    @ResponseStatus(code=HttpStatus.OK)
    public void error(HttpServletRequest request) throws Throwable {
        handlerError(request, false);
    }

    /**
     * 具体的处理
     * @param request
     * @param includeStackTrace
     * @return
     */
    private void handlerError(HttpServletRequest request, boolean includeStackTrace) throws Throwable {
        WebRequest webRequest = new ServletWebRequest(request);
        Throwable e = errorAttributes.getError((WebRequest) webRequest);

        throw e;
    }

    /**
     * 获取错误编码
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}