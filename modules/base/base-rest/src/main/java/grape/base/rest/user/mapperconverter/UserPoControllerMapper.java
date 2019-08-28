package grape.base.rest.user.mapperconverter;

import grape.base.service.user.po.UserPo;
import grape.common.rest.mvc.ControllerMapperConverter;
import org.mapstruct.Mapper;
import grape.base.rest.user.form.UserPoCreateForm;
import grape.base.rest.user.form.UserPoUpdateForm;
import grape.base.rest.user.form.UserPoListPageForm;
import grape.base.rest.user.vo.UserPoVo;
/**
 * <p>
 * 后台管理用户表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-08-27
 */
@Mapper(componentModel = "spring")
public interface UserPoControllerMapper extends ControllerMapperConverter<UserPoVo, UserPo, UserPoCreateForm,UserPoUpdateForm,UserPoListPageForm> {

}
