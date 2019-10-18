package grape.base.rest.user.mvc;

import grape.base.rest.BaseRestSuperController;
import grape.base.service.dict.po.Dict;
import grape.common.exception.runtime.RBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.user.form.UserIdentifierCreateForm;
import grape.base.rest.user.form.UserIdentifierUpdateForm;
import grape.base.rest.user.form.UserIdentifierListPageForm;
import grape.base.rest.user.vo.UserIdentifierVo;
import grape.base.rest.user.mapper.UserIdentifierWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.user.po.UserIdentifier;
import grape.base.service.user.api.IUserIdentifierService;
/**
 * <p>
 * 用户登录标识 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/user-identifier")
@Api(tags = "用户登录标识相关接口")
public class UserIdentifierController extends BaseRestSuperController<IUserIdentifierService,UserIdentifierWebMapper, UserIdentifierVo, UserIdentifier, UserIdentifierCreateForm,UserIdentifierUpdateForm,UserIdentifierListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 用户登录标识表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation(value = "添加登录标识",notes = "添加可用来登录的用户标识，如：邮箱、帐号、手机号等")
     @RequiresPermissions("user-identifier:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserIdentifierVo create(@RequestBody @Valid UserIdentifierCreateForm cf) {
         if (getService().getByIdentifier(cf.getIdentifier()) != null) {
             throw new RBaseException("登录标识已存在");
         }
         return super.create(cf);
     }

     @ApiOperation("根据id查询登录标识")
     @RequiresPermissions("user-identifier:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserIdentifierVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }


    @ApiOperation("分页查询登录标识")
    @RequiresPermissions("user-identifier:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserIdentifierVo> listPage(UserIdentifierListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }

    @Override
    public UserIdentifierVo transVo(UserIdentifierVo vo) {
        Dict dict = getDictById(vo.getIdentityTypeDictId());
        if (dict != null) {
            vo.setIdentityTypeDictCode(dict.getCode());
            vo.setIdentityTypeDictName(dict.getName());
        }
        return vo;
    }
}
