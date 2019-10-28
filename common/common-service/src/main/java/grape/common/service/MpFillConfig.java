package grape.common.service;

import grape.common.AbstractLoginUser;
import grape.common.service.po.IDBasePo;
import grape.common.service.po.NormalBasePo;
import grape.common.service.po.TreeBasePo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangwei
 * Created at 2019/9/23 9:05
 */
@Data
@AllArgsConstructor
public class MpFillConfig {
    private String property;
    private Object value;
    private boolean insert;
    private boolean update;

    private static List<MpFillConfig> config;
    static {
        config = new ArrayList<>();
        config.add(new MpFillConfig(TreeBasePo.PROPERTY_LEVEL,TreeBasePo.INIT_LEVEL,true,false));
        config.add(new MpFillConfig(NormalBasePo.PROPERTY_VERSION,NormalBasePo.INIT_VERSION,true,false));

    }

    public static List<MpFillConfig> getConfig(){
        List<MpFillConfig> configDynamic = new ArrayList<>();
        String now = System.currentTimeMillis() + "";
        AbstractLoginUser loginUser = AbstractLoginUser.getLoginUser();
        String currentUserId = loginUser == null ? IDBasePo.systemUserId : loginUser.getUserId();
        configDynamic.add(new MpFillConfig(NormalBasePo.PROPERTY_CREATE_BY,currentUserId,true,false));
        configDynamic.add(new MpFillConfig(NormalBasePo.PROPERTY_CREATE_AT,now,true,false));
        configDynamic.add(new MpFillConfig(NormalBasePo.PROPERTY_UPDATE_BY,currentUserId,false,true));
        configDynamic.add(new MpFillConfig(NormalBasePo.PROPERTY_UPDATE_AT,now,false,true));
        configDynamic.addAll(config);
        return configDynamic;
    }
}
