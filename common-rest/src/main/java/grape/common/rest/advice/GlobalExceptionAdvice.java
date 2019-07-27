package grape.common.rest.advice;

import grape.common.exception.CBaseException;
import grape.common.exception.ExceptionCode;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.ControllerTools;
import grape.common.rest.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * controller 异常统一处理类
 * Created by yangwei
 * Created at 2019/7/25 20:24
 */
@RestControllerAdvice
@Slf4j
@Configuration
public class GlobalExceptionAdvice {

    private ResultMessage createRM(ExceptionCode code, String msg, Object data, Exception e){
        log.error("{} code:{},msg:{},data:{}",e.getClass().getName(),code,msg,data);
        return ControllerTools.newRm(code,msg == null ? code.getMsg() : msg,data);
    }

    /**
     * 运行时异常
     * 该异常没有打印异常日志，应该是可预知的异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(RBaseException.class)
    public ResultMessage handleRBaseException(HttpServletRequest request, RBaseException ex) {
        return createRM(ex.getCode(),ex.getMessage(),ex.getData(),ex);
    }
    /**
     * 受检查的异常
     * 该异常没有打印异常日志，应该是可预知的异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(CBaseException.class)
    public ResultMessage handleCBaseException(HttpServletRequest request, CBaseException ex) {
        return createRM(ex.getCode(),ex.getMessage(),ex.getData(),ex);
    }

    /**
     * 不支持的媒体类型
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultMessage handleCBaseException(HttpServletRequest request, HttpMediaTypeNotSupportedException ex) {
        return createRM(ExceptionCode.fail,ex.getMessage(),null,ex);
    }

    /**
     * 其它不可预知的异常，通常定义为系统异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultMessage handleException(HttpServletRequest request, Exception ex) {
        // 防止子类的情况
        if(ex instanceof CBaseException){
            return handleCBaseException(request, (CBaseException) ex);
        } else if ( ex instanceof RBaseException){
            return handleRBaseException(request, (RBaseException) ex);
        }

        // 打印异常栈，方便定位问题
        log.error(ex.getMessage(),ex);
        return createRM(ExceptionCode.error,"系统内部异常",null,ex);
    }
}
