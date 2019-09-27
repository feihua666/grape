package grape.base.rest.role.mapper;

import grape.base.service.role.po.Role;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.role.form.RoleCreateForm;
import grape.base.rest.role.form.RoleUpdateForm;
import grape.base.rest.role.form.RoleListPageForm;
import grape.base.rest.role.vo.RoleVo;
/**
 * <p>
 * 角色表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Mapper(componentModel = "spring")
public interface RoleWebMapper extends WebMapper<RoleVo, Role, RoleCreateForm,RoleUpdateForm,RoleListPageForm> {

}
