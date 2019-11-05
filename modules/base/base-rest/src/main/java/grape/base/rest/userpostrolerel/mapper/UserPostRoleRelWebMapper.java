package grape.base.rest.userpostrolerel.mapper;

import grape.base.service.userpostrolerel.po.UserPostRoleRel;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.userpostrolerel.form.UserPostRoleRelCreateForm;
import grape.base.rest.userpostrolerel.form.UserPostRoleRelUpdateForm;
import grape.base.rest.userpostrolerel.form.UserPostRoleRelListPageForm;
import grape.base.rest.userpostrolerel.vo.UserPostRoleRelVo;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 用户岗位与角色关系表,决定了用户的功能 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserPostRoleRelWebMapper extends WebMapper<UserPostRoleRelVo, UserPostRoleRel> {

    UserPostRoleRel formToPo(UserPostRoleRelCreateForm f);
    UserPostRoleRel formToPo(UserPostRoleRelUpdateForm f);
    UserPostRoleRel formToPo(UserPostRoleRelListPageForm f);
}
