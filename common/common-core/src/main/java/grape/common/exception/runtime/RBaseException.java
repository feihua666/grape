package grape.common.exception.runtime;

import grape.common.exception.ExceptionCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 未检查异常基类
 * 业务中所有抛出的异常应使用该异常或其子类，以方便以后扩展
 * 一般情况下写代码该种异常是最多的，因为可能懒得声明检查的异常，不过思想要说一下：
 * 系统本着常用所有的异常响应状态码应该有对应的一个异常，其实常用的状态码也不多，一般都是400+或500+的异常
 * 这样一个状态码就能定位一部分问题，要后端问题还是前端问题
 * Created by yangwei
 * Created at 2018/1/26 15:38
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class RBaseException extends RuntimeException {
    /**
     * 异常数据
     */
    private Object data;
    /**
     * 异常代码，如果需要可以赋值异常代码
     */
    private ExceptionCode code = ExceptionCode.fail;

    public RBaseException(String message, Throwable cause) {
        super(message,cause);
    }
    public RBaseException(String message) {
        super(message);
    }
    public RBaseException(String message, Object data) {
        super(message);
        this.data = data;
    }
    /**
     * @param code    错误代码
     * @param message 错误信息
     */
    public RBaseException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
    }
}
