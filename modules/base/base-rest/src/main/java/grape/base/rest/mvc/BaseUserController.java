package grape.base.rest.mvc;


import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.code.fill.annocations.DomainConvert;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.rest.mvc.BaseUserCreateForm;
import grape.base.rest.mvc.BaseUserUpdateForm;
import grape.base.rest.mvc.BaseUserListPageForm;
import grape.base.rest.mvc.BaseUserVo;
import grape.base.service.api.po.BaseUser;
import grape.base.service.api.service.IBaseUserService;

import javax.validation.constraints.NotNull;

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
    @DomainConvert(checkNull = false)
    public BaseUser createFormToPo( BaseUserCreateForm cf) {

        return null;
    }

    @Override
    @DomainConvert
    public BaseUser updateFormToPo(BaseUserUpdateForm uf) {
        return null;
    }

    @Override
    @DomainConvert
    public BaseUser listPageFormToPo(BaseUserListPageForm lf) {
        return null;
    }

    @Override
    @DomainConvert
    public BaseUserVo poToVo(BaseUser po) {
        return null;
    }

    @Override
    @RequiresPermissions("base:user:listPage")
    public IPage<BaseUserVo> listPage(BaseUserListPageForm baseUserListPageForm) {
        return super.listPage(baseUserListPageForm);
    }
}
