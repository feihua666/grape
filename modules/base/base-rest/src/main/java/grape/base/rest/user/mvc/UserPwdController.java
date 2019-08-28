package grape.base.rest.user.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.user.form.UserPwdPoCreateForm;
import grape.base.rest.user.form.UserPwdPoUpdateForm;
import grape.base.rest.user.form.UserPwdPoListPageForm;
import grape.base.rest.user.vo.UserPwdPoVo;
import grape.base.rest.user.mapperconverter.UserPwdPoControllerMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.user.po.UserPwdPo;
import grape.base.service.user.api.IUserPwdService;
/**
 * <p>
 * 用户密码表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-08-27
 */
@RestController
@RequestMapping("/user-pwd")
@Api(tags = "用户密码表")
public class UserPwdController extends BaseController<IUserPwdService,UserPwdPoControllerMapper, UserPwdPoVo, UserPwdPo, UserPwdPoCreateForm,UserPwdPoUpdateForm,UserPwdPoListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 用户密码表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[用户密码表]单表创建/添加数据")
     @RequiresPermissions("user-pwd-po:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserPwdPoVo create(@RequestBody @Valid UserPwdPoCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[用户密码表]单表根据ID查询")
     @RequiresPermissions("user-pwd-po:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserPwdPoVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[用户密码表]单表根据ID删除")
     @RequiresPermissions("user-pwd-po:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[用户密码表]单表根据ID更新")
     @RequiresPermissions("user-pwd-po:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserPwdPoVo update(@PathVariable Long id,@RequestBody @Valid UserPwdPoUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[用户密码表]单表分页列表")
    @RequiresPermissions("user-pwd-po:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserPwdPoVo> listPage(UserPwdPoListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
