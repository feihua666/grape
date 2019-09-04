package grape.base.rest.user.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.user.form.UserIdentifierPoCreateForm;
import grape.base.rest.user.form.UserIdentifierPoUpdateForm;
import grape.base.rest.user.form.UserIdentifierPoListPageForm;
import grape.base.rest.user.vo.UserIdentifierPoVo;
import grape.base.rest.user.mapperconverter.UserIdentifierPoControllerMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.user.po.UserIdentifierPo;
import grape.base.service.user.api.IUserIdentifierService;
/**
 * <p>
 * 用户登录标识表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-01
 */
@RestController
@RequestMapping("/user-identifier")
@Api(tags = "用户登录标识表")
public class UserIdentifierController extends BaseController<IUserIdentifierService,UserIdentifierPoControllerMapper, UserIdentifierPoVo, UserIdentifierPo, UserIdentifierPoCreateForm,UserIdentifierPoUpdateForm,UserIdentifierPoListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 用户登录标识表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[用户登录标识表]单表创建/添加数据")
     @RequiresPermissions("user-identifier-po:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserIdentifierPoVo create(@RequestBody @Valid UserIdentifierPoCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[用户登录标识表]单表根据ID查询")
     @RequiresPermissions("user-identifier-po:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserIdentifierPoVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[用户登录标识表]单表根据ID删除")
     @RequiresPermissions("user-identifier-po:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[用户登录标识表]单表根据ID更新")
     @RequiresPermissions("user-identifier-po:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserIdentifierPoVo update(@PathVariable Long id,@RequestBody @Valid UserIdentifierPoUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[用户登录标识表]单表分页列表")
    @RequiresPermissions("user-identifier-po:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserIdentifierPoVo> listPage(UserIdentifierPoListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
