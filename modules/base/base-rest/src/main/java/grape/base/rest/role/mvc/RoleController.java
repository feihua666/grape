package grape.base.rest.role.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.role.form.RoleCreateForm;
import grape.base.rest.role.form.RoleEnableForm;
import grape.base.rest.role.form.RoleListPageForm;
import grape.base.rest.role.form.RoleUpdateForm;
import grape.base.rest.role.mapper.RoleWebMapper;
import grape.base.rest.role.vo.RoleVo;
import grape.common.service.loginuser.LoginUser;
import grape.base.service.role.api.IRoleService;
import grape.base.service.role.po.Role;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseTreeLoginUserController;
import grape.common.rest.vo.TreeNodeVo;
import grape.common.service.common.dataconstraint.DefaultDataObject;
import grape.common.service.common.dataconstraint.IDataObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class RoleController extends BaseTreeLoginUserController<RoleVo, Role, LoginUser> {
    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject("dataObjectCodeRole");

    @Autowired
    private RoleWebMapper currentWebMapper;
    @Autowired
    private IRoleService currentService;

    /**
     * 开启全局
     * @return
     */
    @Override
    public boolean isEnableDefaultDataObject() {
        // 判断是否存在关闭的情况
        if (getEnableDefaultDataObjectKeyValue() != null) {
            return (boolean) getEnableDefaultDataObjectKeyValue();
        }
        enableDefaultDataObject();
        return super.isEnableDefaultDataObject();
    }

    @Override
    protected String defaultDataObjectCode() {
        return defaultDataObjectCode.dataObjectCode();
    }

     @ApiOperation("添加角色")
     @PreAuthorize("hasAuthority('role:single:create')")
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
     @PreAuthorize("hasAuthority('role:single:queryById')")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public RoleVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除角色")
     @PreAuthorize("hasAuthority('role:single:deleteById')")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("更新角色")
     @PreAuthorize("hasAuthority('role:single:updateById')")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public RoleVo update(@PathVariable String id,@RequestBody @Valid RoleUpdateForm uf) {
         Role po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("分页查询角色")
    @PreAuthorize("hasAuthority('role:single:listPage')")
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
    @PreAuthorize("hasAuthority('role:single:checkTreeStruct')")
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
    @PreAuthorize("hasAuthority('role:single:enable')")
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
    @PreAuthorize("hasAuthority('role:single:getByParentId')")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<TreeNodeVo<RoleVo>> tree(String parentId) {
        return super.listToTreeNodeVo(super.getByParentId(parentId));
    }
}
