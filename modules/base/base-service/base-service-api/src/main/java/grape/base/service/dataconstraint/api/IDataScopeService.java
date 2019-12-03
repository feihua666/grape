package grape.base.service.dataconstraint.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.dataconstraint.po.DataScope;
import grape.common.service.common.IBaseService;

import java.util.List;

/**
 * <p>
 * 数据范围约束表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
public interface IDataScopeService extends IBaseService<DataScope> {
    /**
     * 判断编码是否已存在
     * @param code
     * @return
     */
    default boolean existCode(String code){
        assertParamNotEmpty(code,"code 不能为空");
        DataScope comp = new DataScope();
        comp.setCode(code);
        int r = count(Wrappers.query(comp));
        return r > 0;
    }
    /**
     * 根据编码查询
     * @param code
     * @return
     */
    default DataScope getByCode(String code){
        assertParamNotEmpty(code,"code 不能为空");
        return getOne(Wrappers.<DataScope>lambdaQuery().eq(DataScope::getCode, code));
    }

    /**
     * 根据数据对象id查询
     * @param dataObjectId
     * @return
     */
    default List<DataScope> getByDataObjectId(String dataObjectId){
        assertParamNotEmpty(dataObjectId,"dataObjectId 不能为空");
        return list(Wrappers.<DataScope>lambdaQuery().eq(DataScope::getDataObjectId, dataObjectId));

    }
}
