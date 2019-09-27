package grape.common.exception.runtime;

import grape.common.exception.ExceptionCode;

/**
 * 错误的请求异常
 * Created by yangwei
 * Created at 2019/7/28 16:25
 */
public class InvalidParamsException extends RBaseException {
    public InvalidParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParamsException(String message) {
        super(message);
    }

    public InvalidParamsException(String message, Object data) {
        super(message, data);
    }

    public InvalidParamsException(ExceptionCode code, String message) {
        super(code, message);
    }
}
