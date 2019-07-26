package grape.common.exception.runtime;

import grape.common.exception.ExceptionCode;
import lombok.Data;

/**
 * 未检查异常基类
 * 业务中所有抛出的异常应使用该异常或其子类，以方便以后扩展
 * Created by yangwei
 * Created at 2018/1/26 15:38
 */
@Data
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
