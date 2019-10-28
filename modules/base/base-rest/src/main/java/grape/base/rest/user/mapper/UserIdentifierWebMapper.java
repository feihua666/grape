package grape.base.rest.user.mapper;

import grape.base.service.user.po.UserIdentifier;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.user.form.UserIdentifierCreateForm;
import grape.base.rest.user.form.UserIdentifierUpdateForm;
import grape.base.rest.user.form.UserIdentifierListPageForm;
import grape.base.rest.user.vo.UserIdentifierVo;
/**
 * <p>
 * 用户登录标识表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@Mapper(componentModel = "spring")
public interface UserIdentifierWebMapper extends WebMapper<UserIdentifierVo, UserIdentifier> {

    UserIdentifier formToPo(UserIdentifierCreateForm f);
    UserIdentifier formToPo(UserIdentifierUpdateForm f);
    UserIdentifier formToPo(UserIdentifierListPageForm f);
}
