package grape.common.rest.advice;

import grape.common.exception.CBaseException;
import grape.common.exception.ExceptionCode;
import grape.common.exception.runtime.BadRequestException;
import grape.common.exception.runtime.RBaseException;
import grape.common.exception.runtime.RDataNotExistException;
import grape.common.rest.ControllerTools;
import grape.common.rest.GlobalExceptionListener;
import grape.common.rest.GlobalInterceptor;
import grape.common.rest.ResultMessage;
import grape.common.tools.RequestIdTool;
import grape.common.tools.ThreadContextTool;
import grape.common.tools.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * controller 异常统一处理类
 * 不要在该类的任何地方抛出异常，因为这里就是处理异常的地方，如果这里抛出异常，将会被转发到BasicErrorController.error方法处理
 * 注意这里处理的返回结果也会进入到GlobalResponseBodyAdvice里处理
 * Created by yangwei
 * Created at 2019/7/25 20:24
 */
@RestControllerAdvice
@Slf4j
@Configuration
// 禁用统一响应消息拦截
@DisableGRM
public class GlobalExceptionAdvice implements ToolService {

    @Autowired(required = false)
    private List<GlobalExceptionListener> globalExceptionListeners;


    private ResultMessage createRM(ExceptionCode code, String msg, Object data, Exception e) {
        log.error("请求错误信息: requestId={},code={},msg={},data={},exception={} ",RequestIdTool.getRequestId(), code, msg, data, e.getClass().getName());
        return ControllerTools.newRm(code, msg == null ? code.getMsg() : msg, data);
    }

    /**
     * 运行时异常
     * 该异常没有打印异常日志，应该是可预知的异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(RBaseException.class)

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultMessage handleRBaseException(HttpServletRequest request, RBaseException ex) {
        return createRM(ex.getCode(), ex.getMessage(), ex.getData(), ex);
    }

    /**
     * 受检查的异常
     * 该异常没有打印异常日志，应该是可预知的异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(CBaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultMessage handleCBaseException(HttpServletRequest request, CBaseException ex) {
        return createRM(ex.getCode(), ex.getMessage(), ex.getData(), ex);
    }

    /**
     * 客户端请求异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(BadRequestException.class)
    // 我认为这能走到这里应该是可预知的异常，一般认为是客户端异常，请求错误导致
    // 如果真是有数据对不上的情况抛出的异常，属于内部系统异常@see RBaseException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultMessage handleBadRequestException(HttpServletRequest request, BadRequestException ex) {
        return createRM(ex.getCode(), ex.getMessage(), ex.getData(), ex);
    }

    /**
     * 自定义的404异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(RDataNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultMessage handleRDataNotExistException(HttpServletRequest request, RDataNotExistException ex) {
        return createRM(ex.getCode(), ex.getMessage(), ex.getData(), ex);
    }

    /**
     * 不支持的媒体类型
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResultMessage handleHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException ex) {
        return createRM(ExceptionCode.fail, ex.getMessage(), null, ex);
    }

    /**
     * 表单验证不对过异常响应
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultMessage handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
        return createRM(ExceptionCode.fail, msg, null, ex);
    }
    /**
     * 表单验证不对过异常响应
     * 发现非requestBody 的get方法表单中有验证时会抛出这个异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultMessage handleBindException(HttpServletRequest request, BindException ex) {
        String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
        return createRM(ExceptionCode.fail, msg, null, ex);
    }

    /**
     * shiro 异常没有权限的异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultMessage handleAuthorizationException(HttpServletRequest request, AuthorizationException ex) {
        return createRM(ExceptionCode.unAuthorized, null, request.getRequestURI().toString(), ex);
    }

    /**
     * shiro 用户未登录异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultMessage handleUnauthenticatedException(HttpServletRequest request, UnauthenticatedException ex) {
        return createRM(ExceptionCode.notLogin, ExceptionCode.notLogin.getMsg(), request.getRequestURI().toString(), ex);
    }

    /**
     * shiro 登录帐号未知异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(UnknownAccountException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultMessage handleUnknownAccountException(HttpServletRequest request, UnknownAccountException ex) {
        return createRM(ExceptionCode.fail, "帐号不正确", request.getRequestURI().toString(), ex);
    }

    /**
     * shiro 登录密码不正确
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultMessage handleIncorrectCredentialsException(HttpServletRequest request, IncorrectCredentialsException ex) {
        return createRM(ExceptionCode.fail, "密码不正确", request.getRequestURI().toString(), ex);
    }

    /**
     * shiro 登录帐号已被锁定
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(LockedAccountException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultMessage handleLockedAccountException(HttpServletRequest request, LockedAccountException ex) {
        return createRM(ExceptionCode.fail, "帐号已被锁定", request.getRequestURI().toString(), ex);
    }


    /**
     * 不支持的请求方法
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResultMessage handleHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {
        return createRM(ExceptionCode.fail, "不支持的请求方法" + request.getMethod(), request.getRequestURI().toString(), ex);
    }

    /**
     * sql异常，也可能是数据不对导致的sql执行问题
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultMessage handleSQLException(HttpServletRequest request, DataIntegrityViolationException ex) {
        return createRM(ExceptionCode.fail, "数据库请求错误" + ex.getCause().getMessage(), request.getRequestURI().toString(), ex);
    }

    /**
     * sql异常，违反数据库约束，如：唯一索引
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultMessage handleSQLIntegrityConstraintViolationException(HttpServletRequest request, DuplicateKeyException ex) {
        return createRM(ExceptionCode.fail, "违反数据库约束" + ex.getCause().getMessage(), request.getRequestURI().toString(), ex);
    }

    /**
     * 一般是后台@RequestBody接收参数，但客户端没有传导致
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultMessage handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException ex) {
        return createRM(ExceptionCode.fail, "没有可用参数", request.getRequestURI().toString(), ex);
    }

    /**
     * 其它不可预知的异常，通常定义为系统异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultMessage handleException(HttpServletRequest request, Exception ex) {
        // 防止子类的情况
        String warnMsg = "如果有必要，你需要定义一个异常类，并继承 {}，以达到响应状态码的需求";
        if (ex instanceof CBaseException) {
            log.warn(warnMsg, CBaseException.class.getName());
            return handleCBaseException(request, (CBaseException) ex);
        } else if (ex instanceof RBaseException) {
            log.warn(warnMsg, RBaseException.class.getName());
            return handleRBaseException(request, (RBaseException) ex);
        }
        // 打印异常栈，方便定位问题
        log.info("请求有异常：requestId={},errorDigest={},more information refer error log.",
                RequestIdTool.getRequestId(),ex.getMessage());
        log.error("请求异常信息：requestId={},errorMsg={}",
                RequestIdTool.getRequestId(),ex.getMessage(), ex);
        ThreadContextTool.put(GlobalInterceptor.hasExceptionKey,true);
        if (!isListEmpty(globalExceptionListeners)) {
            for (GlobalExceptionListener globalExceptionListener : globalExceptionListeners) {
                if (globalExceptionListener.support(request)) {
                    globalExceptionListener.onException(request,ex);
                }
            }
        }
        return createRM(ExceptionCode.error, "系统内部异常", null, ex);
    }
}
