package grape.base.rest.user.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.user.form.UserPwdCreateForm;
import grape.base.rest.user.form.UserPwdUpdateForm;
import grape.base.rest.user.form.UserPwdListPageForm;
import grape.base.rest.user.vo.UserPwdVo;
import grape.base.rest.user.mapper.UserPwdWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.user.po.UserPwd;
import grape.base.service.user.api.IUserPwdService;
/**
 * <p>
 * 用户密码表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-06
 */
@RestController
@RequestMapping("/user-pwd")
@Api(tags = "用户密码表")
public class UserPwdController extends BaseController<IUserPwdService,UserPwdWebMapper, UserPwdVo, UserPwd, UserPwdCreateForm,UserPwdUpdateForm,UserPwdListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 用户密码表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[用户密码表]单表创建/添加数据")
     @RequiresPermissions("user-pwd:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserPwdVo create(@RequestBody @Valid UserPwdCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[用户密码表]单表根据ID查询")
     @RequiresPermissions("user-pwd:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserPwdVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[用户密码表]单表根据ID删除")
     @RequiresPermissions("user-pwd:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[用户密码表]单表根据ID更新")
     @RequiresPermissions("user-pwd:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserPwdVo update(@PathVariable Long id,@RequestBody @Valid UserPwdUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[用户密码表]单表分页列表")
    @RequiresPermissions("user-pwd:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserPwdVo> listPage(UserPwdListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
