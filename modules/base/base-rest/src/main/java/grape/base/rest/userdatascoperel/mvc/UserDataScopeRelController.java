package grape.base.rest.userdatascoperel.mvc;

import grape.base.rest.userdatascoperel.form.DataScopeAssignUserForm;
import grape.base.rest.userdatascoperel.form.UserAssignDataScopeForm;
import grape.base.rest.userdatascoperel.mapper.UserDataScopeRelWebMapper;
import grape.base.rest.userdatascoperel.vo.UserDataScopeRelVo;
import grape.base.service.userdatascoperel.api.IUserDataScopeRelService;
import grape.base.service.userdatascoperel.po.UserDataScopeRel;
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
 * 用户数据范围关系表，多对多 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@RestController
@RequestMapping("/userdatascoperel")
@Api(tags = "用户数据范围关系表，多对多相关接口")
public class UserDataScopeRelController extends BaseController<UserDataScopeRelVo, UserDataScopeRel> {

    @Autowired
    private UserDataScopeRelWebMapper currentWebMapper;
    @Autowired
    private IUserDataScopeRelService currentService;


    @ApiOperation("用户分配数据范围")
    @RequiresPermissions("userDataScopeRel:single:userAssignDataScope")
    @PostMapping("/user/assign/datascope")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean userAssignDataScope(@RequestBody @Valid UserAssignDataScopeForm cf) {
        currentService.userAssignDataScope(cf.getUserId(),cf.getCheckedDataScopeIds(),cf.getUncheckedDataScopeIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据用户ID查询已分配的数据范围id")
    @RequiresPermissions("userDataScopeRel:single:queryByUserId")
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByUserId(@PathVariable String userId) {
        List<UserDataScopeRel> rels = currentService.getByUserId(userId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }

        return rels.stream().map(item -> item.getDataScopeId()).collect(Collectors.toList());
    }

    @ApiOperation("清空用户下的所有数据范围")
    @RequiresPermissions("userDataScopeRel:single:deleteByUserId")
    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteByUserId(@PathVariable String userId) {
        boolean b = currentService.removeByUserId(userId);
        if (!b) {
            throw ExceptionTools.failRE("删除失败，可能要删除的数据不存在，请刷新数据再试");
        }
        return b;
    }


    @ApiOperation("数据范围分配用户")
    @RequiresPermissions("userDataScopeRel:single:dataScopeAssignUser")
    @PostMapping("/datascope/assign/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean dataScopeAssignUser(@RequestBody @Valid DataScopeAssignUserForm cf) {
        currentService.dataScopeAssignUser(cf.getDataScopeId(),cf.getCheckedUserIds(),cf.getUncheckedUserIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据数据范围id查询已分配的用户id")
    @RequiresPermissions("userDataScopeRel:single:queryByDataScopeId")
    @GetMapping("/datascope/{dataScopeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByDataScopeId(@PathVariable String dataScopeId) {
        List<UserDataScopeRel> rels = currentService.getByDataScopeId(dataScopeId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }
        return rels.stream().map(item -> item.getUserId()).collect(Collectors.toList());
    }

    @ApiOperation("清空数据范围下的所有用户")
    @RequiresPermissions("userDataScopeRel:single:deleteByDataScopeId")
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
