package grape.base.rest.user.mapper;

import grape.base.service.user.po.User;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.user.form.UserCreateForm;
import grape.base.rest.user.form.UserUpdateForm;
import grape.base.rest.user.form.UserListPageForm;
import grape.base.rest.user.vo.UserVo;
/**
 * <p>
 * 后台管理用户表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-06
 */
@Mapper(componentModel = "spring")
public interface UserWebMapper extends WebMapper<UserVo, User, UserCreateForm,UserUpdateForm,UserListPageForm> {

}
