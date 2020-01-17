package grape.common.tools;

import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by yangwei
 * Created at 2019/11/12 17:34
 */
public class RequestIdTool {

    private static final String requestIdKey = "requestIdKey";
    // 请求id，从header中获取
    public static final String reqeustIdKey = "request-id";
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
     *
     * @param requestId
     * @return
     */
    public static String initRequestId(String requestId){
        if (StringUtils.isNotBlank(requestId)) {
            restoreRequestId(requestId);
            return requestId;
        }
        return initRequestId();
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
