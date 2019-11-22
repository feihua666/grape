package grape.base.service.paramconfig.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.paramconfig.po.ParamConfig;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.common.IBaseService;

import java.util.Map;

/**
 * <p>
 * 参数配置表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-11-05
 */
public interface IParamConfigService extends IBaseService<ParamConfig> {

    /**
     * 判断编码是否已存在
     * @param code
     * @return
     */
    default boolean existCode(String code){
        if (isStrEmpty(code)) {
            throw new InvalidParamsException("code 不能为空");
        }
        ParamConfig paramConfig = new ParamConfig();
        paramConfig.setCode(code);
        int r = count(Wrappers.query(paramConfig));
        return r > 0;
    }

    /**
     * 根据code 查询
     * @param code
     * @param isDisabled 如果为null忽略该参数
     * @return
     */
    default ParamConfig getByCode(String code,Boolean isDisabled){

        ParamConfig paramConfig = new ParamConfig();
        paramConfig.setCode(code);
        if (isDisabled != null) {
            paramConfig.setIsDisabled(isDisabled);
        }
        return getOne(Wrappers.query(paramConfig));
    }

    Boolean getBooleanValueByCode(String code,Boolean isDisabled);

    String getStringValueByCode(String code,Boolean isDisabled);

    Integer getIntegerValueByCode(String code,Boolean isDisabled);

    Map<String,Object> getMapValueByCode(String code, Boolean isDisabled);
}
