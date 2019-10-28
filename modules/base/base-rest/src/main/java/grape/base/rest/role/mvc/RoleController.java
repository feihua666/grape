package grape.base.rest.role.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.role.form.RoleCreateForm;
import grape.base.rest.role.form.RoleUpdateForm;
import grape.base.rest.role.form.RoleListPageForm;
import grape.base.rest.role.vo.RoleVo;
import grape.base.rest.role.mapper.RoleWebMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色表")
public class RoleController extends BaseController<RoleVo, Role> {

    @Autowired
    private RoleWebMapper currentWebMapper;
    @Autowired
    private IRoleService currentService;



     @ApiOperation("[角色表]单表创建/添加数据")
     @RequiresPermissions("role:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public RoleVo create(@RequestBody @Valid RoleCreateForm cf) {
         Role po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("[角色表]单表根据ID查询")
     @RequiresPermissions("role:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public RoleVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("[角色表]单表根据ID删除")
     @RequiresPermissions("role:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("[角色表]单表根据ID更新")
     @RequiresPermissions("role:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public RoleVo update(@PathVariable String id,@RequestBody @Valid RoleUpdateForm uf) {
         Role po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("[角色表]单表分页列表")
    @RequiresPermissions("role:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<RoleVo> listPage(RoleListPageForm listPageForm) {
         Role po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }

    @ApiOperation("[菜单功能表]根据父级查询")
    @RequiresPermissions("role:single:getByParentId")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleVo> getByParentId(String parentId) {
        return super.getByParentId(parentId);
    }
}
