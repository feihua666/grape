package grape.base.rest.dataconstraint.mapper;

import grape.base.rest.dataconstraint.vo.DataScopeCustomRelVo;
import grape.base.service.dataconstraint.po.DataScopeCustomRel;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 数据范围约束自定义表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DataScopeCustomRelWebMapper extends WebMapper<DataScopeCustomRelVo, DataScopeCustomRel> {

}
