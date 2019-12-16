package grape.base.service.application.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.application.po.Application;
import grape.base.service.application.po.Application;
import grape.common.service.common.IBaseService;
import grape.common.service.trans.ITransService;

/**
 * <p>
 * 应用表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-13
 */
public interface IApplicationService extends IBaseService<Application>  , ITransService<String,String> {

    public static final String trans_type_applicationName = "applicationName";
    public static final String trans_type_applicationCode = "applicationCode";
    /**
     * 判断编码是否已存在
     * @param code
     * @return
     */
    default boolean existCode(String code){
        assertParamNotEmpty(code,"code 不能为空");
        Application comp = new Application();
        comp.setCode(code);
        int r = count(Wrappers.query(comp));
        return r > 0;
    }

    @Override
    default boolean support(String type){
        return isEqualAny(type,trans_type_applicationName,trans_type_applicationCode);
    }

    @Override
    default String trans(String type, String key){
        Application application = getById(key);
        if (application != null) {
            if(isEqual(type,trans_type_applicationName)){
                return application.getName();
            }else if(isEqual(type,trans_type_applicationCode)){
                return application.getCode();
            }
        }
        return null;
    }
}
