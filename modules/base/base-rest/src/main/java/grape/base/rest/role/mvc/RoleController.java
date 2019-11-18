package grape.base.rest.role.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.role.form.RoleCreateForm;
import grape.base.rest.role.form.RoleEnableForm;
import grape.base.rest.role.form.RoleListPageForm;
import grape.base.rest.role.form.RoleUpdateForm;
import grape.base.rest.role.mapper.RoleWebMapper;
import grape.base.rest.role.vo.RoleVo;
import grape.base.service.role.api.IRoleService;
import grape.base.service.role.po.Role;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseTreeController;
import grape.common.rest.vo.TreeNodeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@Api(tags = "角色相关接口")
public class RoleController extends BaseTreeController<RoleVo, Role> {

    @Autowired
    private RoleWebMapper currentWebMapper;
    @Autowired
    private IRoleService currentService;



     @ApiOperation("添加角色")
     @RequiresPermissions("role:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public RoleVo create(@RequestBody @Valid RoleCreateForm cf) {
         // code 唯一检查
         if (currentService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
         }
         Role po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("根据ID查询角色")
     @RequiresPermissions("role:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public RoleVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除角色")
     @RequiresPermissions("role:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("更新角色")
     @RequiresPermissions("role:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public RoleVo update(@PathVariable String id,@RequestBody @Valid RoleUpdateForm uf) {
         Role po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("分页查询角色")
    @RequiresPermissions("role:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<RoleVo> listPage(RoleListPageForm listPageForm) {
         Role po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }
    /**
     * 检查树结构是否完整
     * @return
     */
    @ApiOperation(value = "检查树结构是否完整",notes = "主要用于检查树结构的完整性")
    @RequiresPermissions("role:single:checkTreeStruct")
    @GetMapping("/tree/check/struct")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkTreeStruct() {
        return super.checkTreeStruct();
    }

    /**
     * 启用或禁用
     * @param id
     * @param form
     * @return
     */
    @ApiOperation("启用或禁用")
    @RequiresPermissions("role:single:enable")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleVo enable(@PathVariable String id, @RequestBody @Valid RoleEnableForm form) {
        Role role = new Role();
        role.setId(id);
        role.setIsDisabled(form.getDisabled());
        role.setVersion(form.getVersion());
        role.setDisabledReason(form.getDisabledReason());
        return super.update(role);
    }
    @ApiOperation("角色树")
    @RequiresPermissions("role:single:getByParentId")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<TreeNodeVo<RoleVo>> tree(String parentId) {
        return super.listToTreeNodeVo(super.getByParentId(parentId));
    }

    @Override
    public RoleVo transVo(RoleVo roleVo) {

        Role role = getService().getById(roleVo.getParentId());
        if (role != null) {
            roleVo.setParentName(role.getName());
        }

        return roleVo;
    }
}
