package grape.common.exception;

import grape.common.exception.runtime.RBaseException;

/**
 * 异常处理工具类
 * Created by yangwei
 * Created at 2019/7/24 20:13
 */
public class ExceptionTools {
    /**
     * 运行时异常生成一个异常
     * @param msg
     * @return
     */
    public static RBaseException newRE(String msg){
        return new RBaseException(msg);
    }

    /**
     * 运行时异常生成一个异常
     * @param code
     * @param msg
     * @return
     */
    public static RBaseException newRE(ExceptionCode code, String msg){
        return new RBaseException(code,msg);
    }

    /**
     * 数据不存在异常
     * @param msg
     * @return
     */
    public static RBaseException dataNotExistRE(String msg){
        return newRE(ExceptionCode.notExist,msg == null ? ExceptionCode.notExist.getMsg() : msg);
    }

    /**
     * 这个应该是最常用的失败异常
     * @param msg
     * @return
     */
    public static RBaseException failRE(String msg){
        return newRE(ExceptionCode.fail,msg == null ? ExceptionCode.fail.getMsg() : msg);
    }
}
