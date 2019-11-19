package grape.base.rest.rolefuncrel.mvc;

import grape.base.rest.rolefuncrel.form.*;
import grape.common.exception.ExceptionTools;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;

import grape.base.rest.rolefuncrel.vo.RoleFuncRelVo;
import grape.base.rest.rolefuncrel.mapper.RoleFuncRelWebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.rolefuncrel.po.RoleFuncRel;
import grape.base.service.rolefuncrel.api.IRoleFuncRelService;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单功能关系表，多对多 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/rolefuncrel")
@Api(tags = "角色功能关系相关接口")
public class RoleFuncRelController extends BaseController<RoleFuncRelVo, RoleFuncRel> {

    @Autowired
    private RoleFuncRelWebMapper currentWebMapper;
    @Autowired
    private IRoleFuncRelService currentService;

     @ApiOperation("角色分配功能")
     @RequiresPermissions("rolefuncrel:single:roleAssignFunc")
     @PostMapping("/role/assign/func")
     @ResponseStatus(HttpStatus.CREATED)
     public Boolean roleAssignFunc(@RequestBody @Valid RoleAssignFuncForm cf) {
         currentService.roleAssignFunc(cf.getRoleId(),cf.getCheckedFuncIds(),cf.getUncheckedFuncIds(),cf.getIsLazyLoad());
         return true;
     }

     @ApiOperation("根据角色ID查询已分配的功能id")
     @RequiresPermissions("rolefuncrel:single:queryByRoleId")
     @GetMapping("/role/{roleId}")
     @ResponseStatus(HttpStatus.OK)
     public List<String> queryByRoleId(@PathVariable String roleId) {
         List<RoleFuncRel> rels = currentService.getByRoleId(roleId);
         if (rels == null) {
             ExceptionTools.dataNotExistRE("数据不存在");
         }

         return rels.stream().map(item -> item.getFuncId()).collect(Collectors.toList());
     }

     @ApiOperation("清空角色下的所有功能")
     @RequiresPermissions("rolefuncrel:single:deleteByRoleId")
     @DeleteMapping("/role/{roleId}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public Boolean deleteByRoleId(@PathVariable String roleId) {
         boolean b = currentService.removeByRoleId(roleId);
         if (!b) {
             throw ExceptionTools.failRE("删除失败，可能要删除的数据不存在，请刷新数据再试");
         }
         return b;
     }


    @ApiOperation("功能分配角色")
    @RequiresPermissions("rolefuncrel:single:funcAssignRole")
    @PostMapping("/func/assign/role")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean funcAssignRole(@RequestBody @Valid FuncAssignRoleForm cf) {
        currentService.funcAssignRole(cf.getFuncId(),cf.getCheckedRoleIds(),cf.getUncheckedRoleIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据功能ID查询已分配的功能id")
    @RequiresPermissions("rolefuncrel:single:queryByFuncId")
    @GetMapping("/func/{funcId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByFuncId(@PathVariable String funcId) {
        List<RoleFuncRel> rels = currentService.getByFuncId(funcId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }
        return rels.stream().map(item -> item.getFuncId()).collect(Collectors.toList());
    }

    @ApiOperation("清空功能下的所有角色")
    @RequiresPermissions("rolefuncrel:single:deleteByFuncId")
    @DeleteMapping("/func/{funcId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteByFuncId(@PathVariable String funcId) {
        boolean b = currentService.removeByFuncId(funcId);
        if (!b) {
            throw ExceptionTools.failRE("删除失败，可能要删除的数据不存在，请刷新数据再试");
        }
        return b;
    }
}
