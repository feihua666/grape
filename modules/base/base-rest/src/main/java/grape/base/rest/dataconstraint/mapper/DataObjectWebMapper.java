package grape.base.rest.dataconstraint.mapper;

import grape.base.rest.dataconstraint.form.DataObjectListForm;
import grape.base.service.dataconstraint.po.DataObject;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.dataconstraint.form.DataObjectCreateForm;
import grape.base.rest.dataconstraint.form.DataObjectUpdateForm;
import grape.base.rest.dataconstraint.form.DataObjectListPageForm;
import grape.base.rest.dataconstraint.vo.DataObjectVo;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 数据对象表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DataObjectWebMapper extends WebMapper<DataObjectVo, DataObject> {

    DataObject formToPo(DataObjectCreateForm f);
    DataObject formToPo(DataObjectUpdateForm f);
    DataObject formToPo(DataObjectListPageForm f);
    DataObject formToPo(DataObjectListForm f);
}
