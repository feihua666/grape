package grape.base.rest.userpostrolerel.mapper;

import grape.base.service.userpostrolerel.po.UserPostRoleRel;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.userpostrolerel.form.UserPostRoleRelCreateForm;
import grape.base.rest.userpostrolerel.form.UserPostRoleRelUpdateForm;
import grape.base.rest.userpostrolerel.form.UserPostRoleRelListPageForm;
import grape.base.rest.userpostrolerel.vo.UserPostRoleRelVo;
/**
 * <p>
 * 用户岗位与角色关系表,决定了用户的功能 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Mapper(componentModel = "spring")
public interface UserPostRoleRelWebMapper extends WebMapper<UserPostRoleRelVo, UserPostRoleRel, UserPostRoleRelCreateForm,UserPostRoleRelUpdateForm,UserPostRoleRelListPageForm> {

}