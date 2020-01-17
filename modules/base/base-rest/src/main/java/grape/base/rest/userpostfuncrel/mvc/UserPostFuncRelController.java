package grape.base.rest.userpostfuncrel.mvc;

import grape.base.rest.userpostfuncrel.form.FuncAssignUserPostForm;
import grape.base.rest.userpostfuncrel.form.UserPostAssignFuncForm;
import grape.base.rest.userpostfuncrel.mapper.UserPostFuncRelWebMapper;
import grape.base.rest.userpostfuncrel.vo.UserPostFuncRelVo;
import grape.base.service.userpostfuncrel.api.IUserPostFuncRelService;
import grape.base.service.userpostfuncrel.po.UserPostFuncRel;
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
 * 用户岗位功能关系表，多对多 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-12-17
 */
@RestController
@RequestMapping("/userpostfuncrel")
@Api(tags = "用户岗位功能关系表，多对多相关接口")
public class UserPostFuncRelController extends BaseController<UserPostFuncRelVo, UserPostFuncRel> {

    @Autowired
    private UserPostFuncRelWebMapper currentWebMapper;
    @Autowired
    private IUserPostFuncRelService currentService;


    @ApiOperation("用户岗位分配功能")
    @PreAuthorize("hasAuthority('userPostFuncRel:single:userPostAssignFunc')")
    @PostMapping("/userPost/assign/func")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean userPostAssignFunc(@RequestBody @Valid UserPostAssignFuncForm cf) {
        currentService.userPostAssignFunc(cf.getUserPostId(),cf.getCheckedFuncIds(),cf.getUncheckedFuncIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据用户岗位ID查询已分配的功能id")
    @PreAuthorize("hasAuthority('userPostFuncRel:single:queryByUserPostId')")
    @GetMapping("/userPost/{userPostId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByUserPostId(@PathVariable String userPostId) {
        List<UserPostFuncRel> rels = currentService.getByUserPostId(userPostId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }

        return rels.stream().map(item -> item.getFuncId()).collect(Collectors.toList());
    }

    @ApiOperation("清空用户岗位下的所有功能")
    @PreAuthorize("hasAuthority('userPostFuncRel:single:deleteByUserPostId')")
    @DeleteMapping("/userPost/{userPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteByUserPostId(@PathVariable String userPostId) {
        boolean b = currentService.removeByUserPostId(userPostId);
        if (!b) {
            throw ExceptionTools.failRE("删除失败，可能要删除的数据不存在，请刷新数据再试");
        }
        return b;
    }


    @ApiOperation("功能分配用户岗位")
    @PreAuthorize("hasAuthority('userPostFuncRel:single:funcAssignUserPost')")
    @PostMapping("/func/assign/userPost")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean funcAssignUserPost(@RequestBody @Valid FuncAssignUserPostForm cf) {
        currentService.funcAssignUserPost(cf.getFuncId(),cf.getCheckedUserPostIds(),cf.getUncheckedUserPostIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据功能ID查询已分配的功能id")
    @PreAuthorize("hasAuthority('userPostFuncRel:single:queryByFuncId')")
    @GetMapping("/func/{funcId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByFuncId(@PathVariable String funcId) {
        List<UserPostFuncRel> rels = currentService.getByFuncId(funcId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }
        return rels.stream().map(item -> item.getFuncId()).collect(Collectors.toList());
    }

    @ApiOperation("清空功能下的所有用户岗位")
    @PreAuthorize("hasAuthority('userPostFuncRel:single:deleteByFuncId')")
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
