package grape.base.rest.user.mvc;

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
 * 用户登录标识表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/user-identifier")
@Api(tags = "用户登录标识表")
public class UserIdentifierController extends BaseController<IUserIdentifierService,UserIdentifierWebMapper, UserIdentifierVo, UserIdentifier, UserIdentifierCreateForm,UserIdentifierUpdateForm,UserIdentifierListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 用户登录标识表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[用户登录标识表]单表创建/添加数据")
     @RequiresPermissions("user-identifier:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserIdentifierVo create(@RequestBody @Valid UserIdentifierCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[用户登录标识表]单表根据ID查询")
     @RequiresPermissions("user-identifier:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserIdentifierVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[用户登录标识表]单表根据ID删除")
     @RequiresPermissions("user-identifier:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[用户登录标识表]单表根据ID更新")
     @RequiresPermissions("user-identifier:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserIdentifierVo update(@PathVariable Long id,@RequestBody @Valid UserIdentifierUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[用户登录标识表]单表分页列表")
    @RequiresPermissions("user-identifier:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserIdentifierVo> listPage(UserIdentifierListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
