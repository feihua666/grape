package grape.common.rest.common;

import grape.common.exception.ExceptionCode;
import grape.common.tools.RequestIdTool;

/**
 * 控制器工具类
 * Created by yangwei
 * Created at 2019/7/24 19:41
 */
public class ControllerTools {

    /**
     * 生成一个结果响应对象
     * @param data
     * @return
     */
    public static ResultMessage newRm(Object data){
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setRequestId(RequestIdTool.getRequestId());
        resultMessage.setData(data);
        return resultMessage;
    }

    /**
     * 生成一个完成的结果响应对象
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ResultMessage newRm(ExceptionCode code, String msg, Object data){
        ResultMessage resultMessage = newRm(data);
        resultMessage.setCode(code);
        resultMessage.setMsg(msg);
        return resultMessage;
    }
}
