package grape.base.rest.user.mapper;

import grape.base.service.user.po.UserPwd;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.user.form.pwd.UserPwdCreateForm;
import grape.base.rest.user.form.pwd.UserPwdUpdateForm;
import grape.base.rest.user.form.pwd.UserPwdListPageForm;
import grape.base.rest.user.vo.UserPwdVo;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 用户密码表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserPwdWebMapper extends WebMapper<UserPwdVo, UserPwd> {

    UserPwd formToPo(UserPwdCreateForm f);
    UserPwd formToPo(UserPwdUpdateForm f);
    UserPwd formToPo(UserPwdListPageForm f);
}
