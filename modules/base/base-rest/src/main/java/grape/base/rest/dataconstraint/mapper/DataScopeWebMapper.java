package grape.base.rest.dataconstraint.mapper;

import grape.base.service.dataconstraint.po.DataScope;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.dataconstraint.form.DataScopeCreateForm;
import grape.base.rest.dataconstraint.form.DataScopeUpdateForm;
import grape.base.rest.dataconstraint.form.DataScopeListPageForm;
import grape.base.rest.dataconstraint.vo.DataScopeVo;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 数据范围约束表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DataScopeWebMapper extends WebMapper<DataScopeVo, DataScope> {

    DataScope formToPo(DataScopeCreateForm f);
    DataScope formToPo(DataScopeUpdateForm f);
    DataScope formToPo(DataScopeListPageForm f);
}
