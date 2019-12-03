package grape.base.service.dataconstraint.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.dataconstraint.po.DataObject;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.common.IBaseService;
import org.springframework.validation.DataBinder;

/**
 * <p>
 * 数据对象表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
public interface IDataObjectService extends IBaseService<DataObject> {
    /**
     * 判断编码是否已存在
     * @param code
     * @return
     */
    default boolean existCode(String code){
        if (isStrEmpty(code)) {
            throw new InvalidParamsException("code 不能为空");
        }
        DataObject dataObject = new DataObject();
        dataObject.setCode(code);
        int r = count(Wrappers.query(dataObject));
        return r > 0;
    }

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    default DataObject getBuCode(String code){
        assertParamNotEmpty(code,"code 不能为空");
        return getOne(Wrappers.<DataObject>lambdaQuery().eq(DataObject::getCode, code));
    }
}
