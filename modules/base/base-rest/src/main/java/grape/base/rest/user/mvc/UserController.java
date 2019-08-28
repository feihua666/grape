package grape.base.rest.user.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.user.form.UserPoCreateForm;
import grape.base.rest.user.form.UserPoUpdateForm;
import grape.base.rest.user.form.UserPoListPageForm;
import grape.base.rest.user.vo.UserPoVo;
import grape.base.rest.user.mapperconverter.UserPoControllerMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.user.po.UserPo;
import grape.base.service.user.api.IUserService;
/**
 * <p>
 * 后台管理用户表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-08-27
 */
@RestController
@RequestMapping("/user")
@Api(tags = "后台管理用户表")
public class UserController extends BaseController<IUserService,UserPoControllerMapper, UserPoVo, UserPo, UserPoCreateForm,UserPoUpdateForm,UserPoListPageForm> {

    // 请在这里添加额外的方法
    //todo




    /************************分割线，以下代码为 后台管理用户表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[后台管理用户表]单表创建/添加数据")
     @RequiresPermissions("user-po:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserPoVo create(@RequestBody @Valid UserPoCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[后台管理用户表]单表根据ID查询")
     @RequiresPermissions("user-po:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserPoVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[后台管理用户表]单表根据ID删除")
     @RequiresPermissions("user-po:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[后台管理用户表]单表根据ID更新")
     @RequiresPermissions("user-po:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserPoVo update(@PathVariable Long id,@RequestBody @Valid UserPoUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[后台管理用户表]单表分页列表")
    @RequiresPermissions("user-po:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserPoVo> listPage(UserPoListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
