package grape.base.rest.mvc;


import grape.code.fill.annocations.DomainConvert;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.rest.mvc.BaseUserCreateForm;
import grape.base.rest.mvc.BaseUserUpdateForm;
import grape.base.rest.mvc.BaseUserListPageForm;
import grape.base.rest.mvc.BaseUserVo;
import grape.base.service.api.po.BaseUser;
import grape.base.service.api.service.IBaseUserService;
/**
 * <p>
 * 后台管理系统用户表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-07-28
 */
@RestController
@RequestMapping("/base-user")

public class BaseUserController extends BaseController<IBaseUserService, BaseUserVo, BaseUser, BaseUserCreateForm,BaseUserUpdateForm,BaseUserListPageForm> {

    @Override
    @DomainConvert
    public BaseUser createFormToPo(BaseUserCreateForm cf) {
        BaseUser baseUser = new BaseUser();
        baseUser.setUsername(cf.getUsername());
        baseUser.setNickname(cf.getNickname());
        baseUser.setGender(cf.getGender());
        baseUser.setAvatar(cf.getAvatar());
        baseUser.setSerialNo(cf.getSerialNo());
        baseUser.setIsLock(cf.getIsLock());
        baseUser.setOfficeId(cf.getOfficeId());
        baseUser.setCompanyId(cf.getCompanyId());
        baseUser.setPsw(cf.getPsw());
        baseUser.setSalt(cf.getSalt());
        return baseUser;
    }

    @Override
    public BaseUser updateFormToPo(BaseUserUpdateForm uf) {
        BaseUser baseUser = new BaseUser();
        baseUser.setUsername(uf.getUsername());
        baseUser.setNickname(uf.getNickname());
        baseUser.setGender(uf.getGender());
        baseUser.setAvatar(uf.getAvatar());
        baseUser.setSerialNo(uf.getSerialNo());
        baseUser.setIsLock(uf.getIsLock());
        baseUser.setOfficeId(uf.getOfficeId());
        baseUser.setCompanyId(uf.getCompanyId());
        baseUser.setPsw(uf.getPsw());
        baseUser.setSalt(uf.getSalt());

        return baseUser;

    }

    @Override
    public BaseUser listPageFormToPo(BaseUserListPageForm lf) {
        return null;
    }

    @Override
    public BaseUserVo poToVo(BaseUser po) {
        return null;
    }
}
