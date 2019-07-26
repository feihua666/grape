package grape.common.rest;

import grape.common.exception.ExceptionCode;
import grape.common.rest.vo.BaseVo;

/**
 * 控制器工具类
 * Created by yangwei
 * Created at 2019/7/24 19:41
 */
public class ControllerTools {

    public static ResultMessage newRm(Object data){
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setData(data);
        return resultMessage;
    }
    public static ResultMessage newRm(ExceptionCode code, String msg, Object data){
        ResultMessage resultMessage = newRm(data);
        resultMessage.setCode(code);
        resultMessage.setMsg(msg);
        return resultMessage;
    }
}
