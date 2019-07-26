package grape.base.rest.mvc;


import grape.base.service.api.po.BaseUser;
import grape.base.service.api.service.IBaseUserService;
import grape.common.rest.form.BaseForm;
import grape.common.rest.vo.BaseVo;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.SuperController;

/**
 * <p>
 * 后台管理系统用户表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-07-24
 */
@RestController
@RequestMapping("/base-user")
public class BaseUserController extends SuperController<IBaseUserService, BaseUserController.BaseUserVo, BaseUser, BaseUserController.BaseUserCreateForm> {

    @Override
    public BaseUser createFormToPo(BaseUserCreateForm cf) {
        return null;
    }

    @Override
    public BaseUserVo poToVo(BaseUser po) {
        return null;
    }

    public class BaseUserCreateForm extends BaseForm {

    }
    public class BaseUserVo extends BaseVo{

    }
}
