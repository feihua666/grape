package grape.base.rest.user.mapper;

import grape.base.service.user.po.UserIdentifier;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.user.form.identifier.UserIdentifierCreateForm;
import grape.base.rest.user.form.identifier.UserIdentifierUpdateForm;
import grape.base.rest.user.form.identifier.UserIdentifierListPageForm;
import grape.base.rest.user.vo.UserIdentifierVo;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 用户登录帐号表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserIdentifierWebMapper extends WebMapper<UserIdentifierVo, UserIdentifier> {

    UserIdentifier formToPo(UserIdentifierCreateForm f);
    UserIdentifier formToPo(UserIdentifierUpdateForm f);
    UserIdentifier formToPo(UserIdentifierListPageForm f);
}
