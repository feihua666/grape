package grape.base.service.dataconstraint.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.comp.po.Comp;
import grape.base.service.dataconstraint.po.DataObject;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.common.IBaseService;
import grape.common.service.trans.ITransService;
import org.springframework.validation.DataBinder;

import java.util.List;

/**
 * <p>
 * 数据对象表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
public interface IDataObjectService extends IBaseService<DataObject>, ITransService<String,String> {

    public static final String trans_dataObjectName = "dataObjectName";
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
     * 根据数据范围ids查询数据对象
     * @param dataScopeIds
     * @return
     */
   public List<DataObject> getByDataScopeIds(List<String> dataScopeIds);
    /**
     * 根据编码查询
     * @param code
     * @return
     */
    default DataObject getByCode(String code){
        assertParamNotEmpty(code,"code 不能为空");
        return getOne(Wrappers.<DataObject>lambdaQuery().eq(DataObject::getCode, code));
    }
    @Override
    default boolean support(String type){
        return isEqualAny(type,trans_dataObjectName);
    }

    @Override
    default String trans(String type, String key){
        if (isEqual(type,trans_dataObjectName)) {
            DataObject dataObject = getById(key);
            if (dataObject != null) {
                return dataObject.getName();
            }
        }
        return null;
    }

}
