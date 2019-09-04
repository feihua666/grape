package grape.base.rest.user.mapperconverter;

import grape.base.service.user.po.UserPwdPo;
import grape.common.rest.mvc.ControllerMapperConverter;
import org.mapstruct.Mapper;
import grape.base.rest.user.form.UserPwdPoCreateForm;
import grape.base.rest.user.form.UserPwdPoUpdateForm;
import grape.base.rest.user.form.UserPwdPoListPageForm;
import grape.base.rest.user.vo.UserPwdPoVo;
/**
 * <p>
 * 用户密码表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-01
 */
@Mapper(componentModel = "spring")
public interface UserPwdPoControllerMapper extends ControllerMapperConverter<UserPwdPoVo, UserPwdPo, UserPwdPoCreateForm,UserPwdPoUpdateForm,UserPwdPoListPageForm> {

}
