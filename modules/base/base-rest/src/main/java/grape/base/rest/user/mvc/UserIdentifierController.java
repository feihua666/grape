package grape.base.rest.user.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.user.form.identifier.UserIdentifierCreateForm;
import grape.base.rest.user.form.identifier.UserIdentifierEnableForm;
import grape.base.rest.user.form.identifier.UserIdentifierListPageForm;
import grape.base.rest.user.form.identifier.UserIdentifierUpdateForm;
import grape.base.rest.user.mapper.UserIdentifierWebMapper;
import grape.base.rest.user.vo.UserIdentifierVo;
import grape.base.service.dict.api.IDictService;
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
 * 用户登录帐号 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/useridentifier")
@Api(tags = "用户登录帐号相关接口")
public class UserIdentifierController extends BaseController<UserIdentifierVo, UserIdentifier> {


    @Autowired
    private UserIdentifierWebMapper userIdentifierWebMapper;
    @Autowired
    private IUserIdentifierService iUserIdentifierService;

     @ApiOperation(value = "添加登录帐号",notes = "添加可用来登录的用户标识，如：邮箱、帐号、手机号等")
     @RequiresPermissions("useridentifier:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserIdentifierVo create(@RequestBody @Valid UserIdentifierCreateForm cf) {
         // 判断用户是否已有帐号
         if (iUserIdentifierService.getByUserIdAndTypeDictId(cf.getUserId(), cf.getIdentityTypeDictId()) != null) {
             throw new RBaseException("该用户已存在该类型登录帐号，不可重复添加");
         }

         if (iUserIdentifierService.getByIdentifier(cf.getIdentifier()) != null) {
             throw new RBaseException("登录帐号已存在");
         }
         UserIdentifier userIdentifier = userIdentifierWebMapper.formToPo(cf);
         userIdentifier.setIsLock(false);
         return super.create(userIdentifier);
     }

     @ApiOperation("根据id查询登录帐号")
     @RequiresPermissions("useridentifier:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserIdentifierVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("根据ID删除用户登录帐号")
     @RequiresPermissions("useridentifier:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

    @ApiOperation("根据ID更新用户登录帐号")
    @RequiresPermissions("useridentifier:single:updateById")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserIdentifierVo update(@PathVariable String id,@RequestBody @Valid UserIdentifierUpdateForm uf) {
        UserIdentifier userIdentifier = userIdentifierWebMapper.formToPo(uf);
        userIdentifier.setId(id);
        UserIdentifier userIdentifierDb = iUserIdentifierService.getByIdentifier(uf.getIdentifier());
        if (userIdentifier != null && !isEqual(id,userIdentifierDb.getId())) {
            throw new RBaseException("登录帐号已存在");
        }
        return super.update(userIdentifier);
    }
    @ApiOperation("分页查询登录帐号")
    @RequiresPermissions("useridentifier:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserIdentifierVo> listPage(UserIdentifierListPageForm listPageForm) {
        UserIdentifier userIdentifier = userIdentifierWebMapper.formToPo(listPageForm);
         return super.listPage(userIdentifier,listPageForm);
     }

    /**
     * 启用或锁定
     * @param id
     * @param form
     * @return
     */
    @ApiOperation("启用或锁定")
    @RequiresPermissions("useridentifier:single:enable")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserIdentifierVo enable(@PathVariable String id, @RequestBody @Valid UserIdentifierEnableForm form) {
        UserIdentifier userIdentifier = new UserIdentifier();
        userIdentifier.setId(id);
        userIdentifier.setIsLock(form.getIsLock());
        userIdentifier.setVersion(form.getVersion());
        userIdentifier.setLockReason(form.getLockReason());
        return super.update(userIdentifier);
    }
}
