package grape.base.rest.userrolerel.mvc;

import grape.base.rest.userrolerel.form.RoleAssignUserForm;
import grape.base.rest.userrolerel.form.UserAssignRoleForm;
import grape.base.rest.userrolerel.mapper.UserRoleRelWebMapper;
import grape.base.rest.userrolerel.vo.UserRoleRelVo;
import grape.base.service.userrolerel.api.IUserRoleRelService;
import grape.base.service.userrolerel.po.UserRoleRel;
import grape.common.exception.ExceptionTools;
import grape.common.rest.mvc.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关系表，多对多 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/userrolerel")
@Api(tags = "用户角色关系相关接口")
public class UserRoleRelController extends BaseController<UserRoleRelVo, UserRoleRel> {

    @Autowired
    private UserRoleRelWebMapper currentWebMapper;
    @Autowired
    private IUserRoleRelService currentService;


    @ApiOperation("用户分配角色")
    @RequiresPermissions("userRoleRel:single:userAssignRole")
    @PostMapping("/user/assign/role")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean userAssignRole(@RequestBody @Valid UserAssignRoleForm cf) {
        currentService.userAssignRole(cf.getUserId(),cf.getCheckedRoleIds(),cf.getUncheckedRoleIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据用户ID查询已分配的角色id")
    @RequiresPermissions("userRoleRel:single:queryByUserId")
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByUserId(@PathVariable String userId) {
        List<UserRoleRel> rels = currentService.getByUserId(userId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }

        return rels.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
    }

    @ApiOperation("清空用户下的所有角色")
    @RequiresPermissions("userRoleRel:single:deleteByUserId")
    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteByUserId(@PathVariable String userId) {
        boolean b = currentService.removeByUserId(userId);
        if (!b) {
            throw ExceptionTools.failRE("删除失败，可能要删除的数据不存在，请刷新数据再试");
        }
        return b;
    }


    @ApiOperation("角色分配用户")
    @RequiresPermissions("userRoleRel:single:roleAssignUser")
    @PostMapping("/role/assign/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean roleAssignUser(@RequestBody @Valid RoleAssignUserForm cf) {
        currentService.roleAssignUser(cf.getRoleId(),cf.getCheckedUserIds(),cf.getUncheckedUserIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据角色id查询已分配的用户id")
    @RequiresPermissions("userRoleRel:single:queryByRoleId")
    @GetMapping("/role/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByRoleId(@PathVariable String roleId) {
        List<UserRoleRel> rels = currentService.getByRoleId(roleId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }
        return rels.stream().map(item -> item.getUserId()).collect(Collectors.toList());
    }

    @ApiOperation("清空角色下的所有用户")
    @RequiresPermissions("userRoleRel:single:deleteByRoleId")
    @DeleteMapping("/role/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteByRoleId(@PathVariable String roleId) {
        boolean b = currentService.removeByRoleId(roleId);
        if (!b) {
            throw ExceptionTools.failRE("删除失败，可能要删除的数据不存在，请刷新数据再试");
        }
        return b;
    }
}
