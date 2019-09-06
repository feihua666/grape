package grape.base.rest.user.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.user.form.UserCreateForm;
import grape.base.rest.user.form.UserUpdateForm;
import grape.base.rest.user.form.UserListPageForm;
import grape.base.rest.user.vo.UserVo;
import grape.base.rest.user.mapper.UserWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.user.po.User;
import grape.base.service.user.api.IUserService;
/**
 * <p>
 * 后台管理用户表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-06
 */
@RestController
@RequestMapping("/user")
@Api(tags = "后台管理用户表")
public class UserController extends BaseController<IUserService,UserWebMapper, UserVo, User, UserCreateForm,UserUpdateForm,UserListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 后台管理用户表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[后台管理用户表]单表创建/添加数据")
     @RequiresPermissions("user:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserVo create(@RequestBody @Valid UserCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[后台管理用户表]单表根据ID查询")
     @RequiresPermissions("user:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[后台管理用户表]单表根据ID删除")
     @RequiresPermissions("user:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[后台管理用户表]单表根据ID更新")
     @RequiresPermissions("user:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserVo update(@PathVariable Long id,@RequestBody @Valid UserUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[后台管理用户表]单表分页列表")
    @RequiresPermissions("user:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserVo> listPage(UserListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
