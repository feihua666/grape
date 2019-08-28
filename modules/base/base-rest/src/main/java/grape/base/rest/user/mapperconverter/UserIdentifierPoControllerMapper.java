package grape.base.rest.user.mapperconverter;

import grape.base.service.user.po.UserIdentifierPo;
import grape.common.rest.mvc.ControllerMapperConverter;
import org.mapstruct.Mapper;
import grape.base.rest.user.form.UserIdentifierPoCreateForm;
import grape.base.rest.user.form.UserIdentifierPoUpdateForm;
import grape.base.rest.user.form.UserIdentifierPoListPageForm;
import grape.base.rest.user.vo.UserIdentifierPoVo;
/**
 * <p>
 * 用户登录标识表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-08-27
 */
@Mapper(componentModel = "spring")
public interface UserIdentifierPoControllerMapper extends ControllerMapperConverter<UserIdentifierPoVo, UserIdentifierPo, UserIdentifierPoCreateForm,UserIdentifierPoUpdateForm,UserIdentifierPoListPageForm> {

}
