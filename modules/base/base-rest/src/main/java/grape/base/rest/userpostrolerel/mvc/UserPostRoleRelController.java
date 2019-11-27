package grape.base.rest.userpostrolerel.mvc;

import grape.base.rest.userpostrolerel.form.RoleAssignUserPostForm;
import grape.base.rest.userpostrolerel.form.UserPostAssignRoleForm;
import grape.base.rest.userpostrolerel.mapper.UserPostRoleRelWebMapper;
import grape.base.rest.userpostrolerel.vo.UserPostRoleRelVo;
import grape.base.service.userpostrolerel.api.IUserPostRoleRelService;
import grape.base.service.userpostrolerel.po.UserPostRoleRel;
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
 * 用户岗位与角色关系表,决定了用户的功能 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/userpostrolerel")
@Api(tags = "用户岗位与角色关系相关接口")
public class UserPostRoleRelController extends BaseController<UserPostRoleRelVo, UserPostRoleRel> {

    @Autowired
    private UserPostRoleRelWebMapper currentWebMapper;
    @Autowired
    private IUserPostRoleRelService currentService;


    @ApiOperation("用户岗位分配角色")
    @RequiresPermissions("userPostRoleRel:single:userPostAssignRole")
    @PostMapping("/userpost/assign/role")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean userPostAssignRole(@RequestBody @Valid UserPostAssignRoleForm cf) {
        currentService.userAssignRole(cf.getUserPostId(),cf.getCheckedRoleId());
        return true;
    }

    @ApiOperation("根据用户岗位ID查询已分配的角色id")
    @RequiresPermissions("userPostRoleRel:single:queryByUserPostId")
    @GetMapping("/userpost/{userPostId}")
    @ResponseStatus(HttpStatus.OK)
    public String queryByUserPostId(@PathVariable String userPostId) {
        UserPostRoleRel rel = currentService.getByUserPostId(userPostId);
        if (rel == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }

        return rel.getRoleId();
    }

    @ApiOperation("清空用户岗位下的所有角色")
    @RequiresPermissions("userPostRoleRel:single:deleteByUserPostId")
    @DeleteMapping("/userpost/{userPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteByUserPostId(@PathVariable String userPostId) {
        boolean b = currentService.removeByUserPostId(userPostId);
        if (!b) {
            throw ExceptionTools.failRE("删除失败，可能要删除的数据不存在，请刷新数据再试");
        }
        return b;
    }


    @ApiOperation("角色分配用户岗位")
    @RequiresPermissions("userPostRoleRel:single:roleAssignUserPost")
    @PostMapping("/role/assign/userpost")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean roleAssignUserPost(@RequestBody @Valid RoleAssignUserPostForm cf) {
        currentService.roleAssignUser(cf.getRoleId(),cf.getCheckedUserPostIds(),cf.getUncheckedUserPostIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据角色id查询已分配的用户岗位id")
    @RequiresPermissions("userPostRoleRel:single:queryByRoleId")
    @GetMapping("/role/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByRoleId(@PathVariable String roleId) {
        List<UserPostRoleRel> rels = currentService.getByRoleId(roleId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }
        return rels.stream().map(item -> item.getUserPostId()).collect(Collectors.toList());
    }

    @ApiOperation("清空角色下的所有用户岗位")
    @RequiresPermissions("userPostRoleRel:single:deleteByRoleId")
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
