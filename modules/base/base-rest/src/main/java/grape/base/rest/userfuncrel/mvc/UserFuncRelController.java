package grape.base.rest.userfuncrel.mvc;

import grape.base.rest.userfuncrel.form.FuncAssignUserForm;
import grape.base.rest.userfuncrel.form.UserAssignFuncForm;
import grape.base.rest.userfuncrel.mapper.UserFuncRelWebMapper;
import grape.base.rest.userfuncrel.vo.UserFuncRelVo;
import grape.base.service.userfuncrel.api.IUserFuncRelService;
import grape.base.service.userfuncrel.po.UserFuncRel;
import grape.common.exception.ExceptionTools;
import grape.common.rest.mvc.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户功能关系表，多对多 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-12-17
 */
@RestController
@RequestMapping("/userfuncrel")
@Api(tags = "用户功能关系相关接口")
public class UserFuncRelController extends BaseController<UserFuncRelVo, UserFuncRel> {

    @Autowired
    private UserFuncRelWebMapper currentWebMapper;
    @Autowired
    private IUserFuncRelService currentService;


    @ApiOperation("用户分配功能")
    @PreAuthorize("hasAuthority('userFuncRel:single:userAssignFunc')")
    @PostMapping("/user/assign/func")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean userAssignFunc(@RequestBody @Valid UserAssignFuncForm cf) {
        currentService.userAssignFunc(cf.getUserId(),cf.getCheckedFuncIds(),cf.getUncheckedFuncIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据用户ID查询已分配的功能id")
    @PreAuthorize("hasAuthority('userFuncRel:single:queryByUserId')")
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByUserId(@PathVariable String userId) {
        List<UserFuncRel> rels = currentService.getByUserId(userId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }

        return rels.stream().map(item -> item.getFuncId()).collect(Collectors.toList());
    }

    @ApiOperation("清空用户下的所有功能")
    @PreAuthorize("hasAuthority('userFuncRel:single:deleteByUserId')")
    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteByUserId(@PathVariable String userId) {
        boolean b = currentService.removeByUserId(userId);
        if (!b) {
            throw ExceptionTools.failRE("删除失败，可能要删除的数据不存在，请刷新数据再试");
        }
        return b;
    }


    @ApiOperation("功能分配用户")
    @PreAuthorize("hasAuthority('userFuncRel:single:funcAssignUser')")
    @PostMapping("/func/assign/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean funcAssignUser(@RequestBody @Valid FuncAssignUserForm cf) {
        currentService.funcAssignUser(cf.getFuncId(),cf.getCheckedUserIds(),cf.getUncheckedUserIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据功能ID查询已分配的功能id")
    @PreAuthorize("hasAuthority('userFuncRel:single:queryByFuncId')")
    @GetMapping("/func/{funcId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByFuncId(@PathVariable String funcId) {
        List<UserFuncRel> rels = currentService.getByFuncId(funcId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }
        return rels.stream().map(item -> item.getFuncId()).collect(Collectors.toList());
    }

    @ApiOperation("清空功能下的所有用户")
    @PreAuthorize("hasAuthority('userFuncRel:single:deleteByFuncId')")
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
