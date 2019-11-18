package grape.common.tools;

import cn.hutool.core.util.IdUtil;

/**
 * Created by yangwei
 * Created at 2019/11/12 17:34
 */
public class RequestIdTool {

    private static final String requestIdKey = "requestIdKey";

    /**
     * 初始化请求id
     * @return
     */
    public static String initRequestId() {
        String fastSimpleUUID = IdUtil.fastSimpleUUID();
        ThreadContextTool.put(requestIdKey,fastSimpleUUID);
        return fastSimpleUUID;
    }

    /**
     * 获取请求id
     * @return
     */
    public static String getRequestId() {
        return ((String) ThreadContextTool.get(requestIdKey));
    }

    /**
     * 覆盖请求id
     * @param requestId
     */
    public static void restoreRequestId(String requestId){
        ThreadContextTool.remove(requestIdKey);
        ThreadContextTool.put(requestIdKey,requestId);
    }
}
