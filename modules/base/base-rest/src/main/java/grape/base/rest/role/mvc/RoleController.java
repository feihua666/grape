package grape.base.rest.role.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.role.form.RoleCreateForm;
import grape.base.rest.role.form.RoleUpdateForm;
import grape.base.rest.role.form.RoleListPageForm;
import grape.base.rest.role.vo.RoleVo;
import grape.base.rest.role.mapper.RoleWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.role.po.Role;
import grape.base.service.role.api.IRoleService;
import java.util.List;
/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色表")
public class RoleController extends BaseController<IRoleService,RoleWebMapper, RoleVo, Role, RoleCreateForm,RoleUpdateForm,RoleListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 角色表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[角色表]单表创建/添加数据")
     @RequiresPermissions("role:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public RoleVo create(@RequestBody @Valid RoleCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[角色表]单表根据ID查询")
     @RequiresPermissions("role:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public RoleVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[角色表]单表根据ID删除")
     @RequiresPermissions("role:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[角色表]单表根据ID更新")
     @RequiresPermissions("role:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public RoleVo update(@PathVariable Long id,@RequestBody @Valid RoleUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[角色表]单表分页列表")
    @RequiresPermissions("role:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<RoleVo> listPage(RoleListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }

    @ApiOperation("[菜单功能表]根据父级查询")
    @RequiresPermissions("role:single:tree")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleVo> tree( Long parentId) {
        return super.tree(parentId);
    }
}
