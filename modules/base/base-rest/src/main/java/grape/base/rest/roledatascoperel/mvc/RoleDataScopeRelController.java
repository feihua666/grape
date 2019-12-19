package grape.base.rest.roledatascoperel.mvc;

import grape.base.rest.roledatascoperel.form.DataScopeAssignRoleForm;
import grape.base.rest.roledatascoperel.form.RoleAssignDataScopeForm;
import grape.base.rest.roledatascoperel.mapper.RoleDataScopeRelWebMapper;
import grape.base.rest.roledatascoperel.vo.RoleDataScopeRelVo;
import grape.base.service.dataconstraint.api.IDataScopeService;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.roledatascoperel.api.IRoleDataScopeRelService;
import grape.base.service.roledatascoperel.po.RoleDataScopeRel;
import grape.common.exception.ExceptionTools;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.rest.mvc.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色数据范围关系表，多对多 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@RestController
@RequestMapping("/role-data-scope-rel")
@Api(tags = "角色数据范围关系表，多对多相关接口")
public class RoleDataScopeRelController extends BaseController<RoleDataScopeRelVo, RoleDataScopeRel> {

    @Autowired
    private RoleDataScopeRelWebMapper currentWebMapper;
    @Autowired
    private IRoleDataScopeRelService currentService;
    @Autowired
    private IDataScopeService iDataScopeService;

    @ApiOperation("角色分配数据范围")
    @RequiresPermissions("roleDataScopeRel:single:roleAssignDataScope")
    @PostMapping("/role/assign/datascope")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean roleAssignDataScope(@RequestBody @Valid RoleAssignDataScopeForm cf) {
        currentService.roleAssignDataScope(cf.getRoleId(),cf.getCheckedDataScopeIds(),cf.getUncheckedDataScopeIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据角色ID查询已分配的数据范围id")
    @RequiresPermissions("roleDataScopeRel:single:queryByRoleId")
    @GetMapping("/role/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByRoleId(@PathVariable String roleId) {
        List<RoleDataScopeRel> rels = currentService.getByRoleId(roleId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }

        return rels.stream().map(item -> item.getDataScopeId()).collect(Collectors.toList());
    }

    @ApiOperation("清空角色下的所有数据范围")
    @RequiresPermissions("roleDataScopeRel:single:deleteByRoleId")
    @DeleteMapping("/role/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteByRoleId(@PathVariable String roleId) {
        boolean b = currentService.removeByRoleId(roleId);
        if (!b) {
            throw ExceptionTools.failRE("删除失败，可能要删除的数据不存在，请刷新数据再试");
        }
        return b;
    }


    @ApiOperation("数据范围分配角色")
    @RequiresPermissions("roleDataScopeRel:single:dataScopeAssignRole")
    @PostMapping("/datascope/assign/role")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean dataScopeAssignRole(@RequestBody @Valid DataScopeAssignRoleForm cf) {
        currentService.dataScopeAssignRole(cf.getDataScopeId(),cf.getCheckedRoleIds(),cf.getUncheckedRoleIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据数据范围ID查询已分配的数据范围id")
    @RequiresPermissions("roleDataScopeRel:single:queryByDataScopeId")
    @GetMapping("/datascope/{dataScopeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByDataScopeId(@PathVariable String dataScopeId) {
        List<RoleDataScopeRel> rels = currentService.getByDataScopeId(dataScopeId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }
        return rels.stream().map(item -> item.getDataScopeId()).collect(Collectors.toList());
    }

    @ApiOperation("清空数据范围下的所有角色")
    @RequiresPermissions("roleDataScopeRel:single:deleteByDataScopeId")
    @DeleteMapping("/datascope/{dataScopeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteByDataScopeId(@PathVariable String dataScopeId) {
        boolean b = currentService.removeByDataScopeId(dataScopeId);
        if (!b) {
            throw ExceptionTools.failRE("删除失败，可能要删除的数据不存在，请刷新数据再试");
        }
        return b;
    }
}
