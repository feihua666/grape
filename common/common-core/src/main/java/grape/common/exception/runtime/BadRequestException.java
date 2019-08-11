package grape.common.exception.runtime;

import grape.common.exception.ExceptionCode;

/**
 * 错误的请求异常
 * Created by yangwei
 * Created at 2019/7/28 16:25
 */
public class BadRequestException extends RBaseException {
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Object data) {
        super(message, data);
    }

    public BadRequestException(ExceptionCode code, String message) {
        super(code, message);
    }
}
