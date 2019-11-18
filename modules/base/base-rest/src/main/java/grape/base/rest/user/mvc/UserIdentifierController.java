package grape.base.rest.user.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.user.form.UserIdentifierCreateForm;
import grape.base.rest.user.form.UserIdentifierListPageForm;
import grape.base.rest.user.mapper.UserIdentifierWebMapper;
import grape.base.rest.user.vo.UserIdentifierVo;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.user.api.IUserIdentifierService;
import grape.base.service.user.po.UserIdentifier;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
public class UserIdentifierController extends BaseController<UserIdentifierVo, UserIdentifier> {


    @Autowired
    private UserIdentifierWebMapper userIdentifierWebMapper;
    @Autowired
    private IUserIdentifierService iUserIdentifierService;
    @Autowired
    private IDictService iDictService;

     @ApiOperation(value = "添加登录标识",notes = "添加可用来登录的用户标识，如：邮箱、帐号、手机号等")
     @RequiresPermissions("user-identifier:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserIdentifierVo create(@RequestBody @Valid UserIdentifierCreateForm cf) {
         if (iUserIdentifierService.getByIdentifier(cf.getIdentifier()) != null) {
             throw new RBaseException("登录标识已存在");
         }
         UserIdentifier userIdentifier = userIdentifierWebMapper.formToPo(cf);
         return super.create(userIdentifier);
     }

     @ApiOperation("根据id查询登录标识")
     @RequiresPermissions("user-identifier:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserIdentifierVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }


    @ApiOperation("分页查询登录标识")
    @RequiresPermissions("user-identifier:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserIdentifierVo> listPage(UserIdentifierListPageForm listPageForm) {
        UserIdentifier userIdentifier = userIdentifierWebMapper.formToPo(listPageForm);
         return super.listPage(userIdentifier,listPageForm);
     }

    @Override
    public UserIdentifierVo transVo(UserIdentifierVo vo) {
        Dict dict = iDictService.getById(vo.getIdentityTypeDictId());
        if (dict != null) {
            vo.setIdentityTypeDictCode(dict.getCode());
            vo.setIdentityTypeDictName(dict.getName());
        }
        return vo;
    }
}
