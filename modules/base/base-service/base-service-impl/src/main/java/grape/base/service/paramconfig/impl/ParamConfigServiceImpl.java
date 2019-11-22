package grape.base.service.paramconfig.impl;

import cn.hutool.core.convert.Convert;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.paramconfig.po.ParamConfig;
import grape.base.service.paramconfig.mapper.ParamConfigMapper;
import grape.base.service.paramconfig.api.IParamConfigService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-11-05
 */
@Service
public class ParamConfigServiceImpl extends BaseServiceImpl<ParamConfigMapper, ParamConfig> implements IParamConfigService {

    @Autowired
    private IDictService iDictService;

    @Override
    public Boolean getBooleanValueByCode(String code, Boolean isDisabled) {
        return convert(code, isDisabled);
    }

    @Override
    public String getStringValueByCode(String code, Boolean isDisabled) {
        return convert(code, isDisabled);
    }

    @Override
    public Integer getIntegerValueByCode(String code, Boolean isDisabled) {
        return convert(code, isDisabled);
    }


    @Override
    public Map<String, Object> getMapValueByCode(String code, Boolean isDisabled) {
        return convert(code, isDisabled);
    }
    private <T> T convert(String code, Boolean isDisabled){
        ParamConfig paramConfig = getByCode(code, isDisabled);
        if (paramConfig != null) {
            Dict dict = iDictService.getById(paramConfig.getValueTypeDictId());

            return Convert.convertByClassName(dict.getCode(), paramConfig.getValue());
        }
        return null;
    }
}
