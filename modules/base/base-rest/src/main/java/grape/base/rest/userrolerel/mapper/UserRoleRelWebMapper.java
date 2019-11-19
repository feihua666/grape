package grape.base.rest.userrolerel.mapper;

import grape.base.rest.userrolerel.vo.UserRoleRelVo;
import grape.base.service.userrolerel.po.UserRoleRel;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 角色菜单功能关系表，多对多 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleRelWebMapper extends WebMapper<UserRoleRelVo, UserRoleRel> {

}
