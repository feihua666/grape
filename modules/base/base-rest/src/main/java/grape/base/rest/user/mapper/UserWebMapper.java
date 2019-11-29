package grape.base.rest.user.mapper;

import grape.base.rest.user.form.UserListForm;
import grape.base.service.user.dto.UserCreateParam;
import grape.base.service.user.po.User;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.user.form.UserCreateForm;
import grape.base.rest.user.form.UserUpdateForm;
import grape.base.rest.user.form.UserListPageForm;
import grape.base.rest.user.vo.UserVo;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 后台管理用户表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserWebMapper extends WebMapper<UserVo, User> {

    UserCreateParam formToParam(UserCreateForm f);
    User formToPo(UserUpdateForm f);
    User formToPo(UserListPageForm f);
    User formToPo(UserListForm f);
}
