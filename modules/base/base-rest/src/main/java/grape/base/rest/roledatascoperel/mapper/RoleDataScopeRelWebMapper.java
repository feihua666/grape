package grape.base.rest.roledatascoperel.mapper;

import grape.base.rest.roledatascoperel.vo.RoleDataScopeRelVo;
import grape.base.service.roledatascoperel.po.RoleDataScopeRel;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 角色数据范围关系表，多对多 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleDataScopeRelWebMapper extends WebMapper<RoleDataScopeRelVo, RoleDataScopeRel> {

}
