package grape.common.tools;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2019/11/1 15:55
 */
public class BaiduMapTool {

    /**
     * 根据地址获取经伟度
     * @param address
     * @param ak
     * @return {result:{
     *     location:{
     *         lng:xxx,
     *         lat:xxx
     *     }
     * }}
     */
    public static Map<String,Object> getGeo(String address,String ak){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("address", address);
        params.put("output", "json");
        params.put("ak", ak);
        String r = HttpUtil.get("http://api.map.baidu.com/geocoder/v2/", params);
        return JSONUtil.toBean(r, Map.class);
    }
}
