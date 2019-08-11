package grape.common.exception.runtime;

import grape.common.exception.ExceptionCode;

/**
 * 数据不存在异常
 * Created by yangwei
 * Created at 2019/7/28 15:19
 */
public class RDataNotExistException extends BadRequestException{

    public RDataNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public RDataNotExistException(String message) {
        super(message);
    }

    public RDataNotExistException(String message, Object data) {
        super(message, data);
    }

    public RDataNotExistException(ExceptionCode code, String message) {
        super(code, message);
    }
}
