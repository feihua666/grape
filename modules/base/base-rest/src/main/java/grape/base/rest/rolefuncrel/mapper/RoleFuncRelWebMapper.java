package grape.base.rest.rolefuncrel.mapper;

import grape.base.service.rolefuncrel.po.RoleFuncRel;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.rolefuncrel.form.RoleFuncRelCreateForm;
import grape.base.rest.rolefuncrel.form.RoleFuncRelUpdateForm;
import grape.base.rest.rolefuncrel.form.RoleFuncRelListPageForm;
import grape.base.rest.rolefuncrel.vo.RoleFuncRelVo;
/**
 * <p>
 * 角色菜单功能关系表，多对多 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Mapper(componentModel = "spring")
public interface RoleFuncRelWebMapper extends WebMapper<RoleFuncRelVo, RoleFuncRel, RoleFuncRelCreateForm,RoleFuncRelUpdateForm,RoleFuncRelListPageForm> {

}
