package grape.base.rest.userrolerel.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.userrolerel.form.UserRoleRelCreateForm;
import grape.base.rest.userrolerel.form.UserRoleRelUpdateForm;
import grape.base.rest.userrolerel.form.UserRoleRelListPageForm;
import grape.base.rest.userrolerel.vo.UserRoleRelVo;
import grape.base.rest.userrolerel.mapper.UserRoleRelWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.userrolerel.po.UserRoleRel;
import grape.base.service.userrolerel.api.IUserRoleRelService;
import java.util.List;
/**
 * <p>
 * 角色菜单功能关系表，多对多 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-27
 */
@RestController
@RequestMapping("/user-role-rel")
@Api(tags = "角色菜单功能关系表，多对多")
public class UserRoleRelController extends BaseController<IUserRoleRelService,UserRoleRelWebMapper, UserRoleRelVo, UserRoleRel, UserRoleRelCreateForm,UserRoleRelUpdateForm,UserRoleRelListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 角色菜单功能关系表，多对多 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[角色菜单功能关系表，多对多]单表创建/添加数据")
     @RequiresPermissions("user-role-rel:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserRoleRelVo create(@RequestBody @Valid UserRoleRelCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[角色菜单功能关系表，多对多]单表根据ID查询")
     @RequiresPermissions("user-role-rel:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserRoleRelVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[角色菜单功能关系表，多对多]单表根据ID删除")
     @RequiresPermissions("user-role-rel:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[角色菜单功能关系表，多对多]单表根据ID更新")
     @RequiresPermissions("user-role-rel:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserRoleRelVo update(@PathVariable Long id,@RequestBody @Valid UserRoleRelUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[角色菜单功能关系表，多对多]单表分页列表")
    @RequiresPermissions("user-role-rel:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserRoleRelVo> listPage(UserRoleRelListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }

}
