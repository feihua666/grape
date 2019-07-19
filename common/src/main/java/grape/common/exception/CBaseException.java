package grape.common.exception;

/**
 * 受检查的异常基类
 * 业务中所有抛出的异常应使用该异常或其子类，以方便以后扩展
 * Created by yangwei
 * Created at 2019/7/18 18:01
 */
public class CBaseException extends Exception {
    /**
     * 异常数据
     */
    private Object data;
    /**
     * 异常代码，如果需要可以赋值异常代码
     */
    private String code;
    public CBaseException(String message, Throwable cause) {
        super(message,cause);
    }
    public CBaseException(String message) {
        super(message);
    }
    public CBaseException(String message, Object data) {
        super(message);
        this.data = data;
    }
    /**
     * @param message 错误信息
     * @param code    错误代码
     */
    public CBaseException(String message, String code) {
        super(message);
        this.code = code;
    }
}
