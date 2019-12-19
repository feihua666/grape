package grape.base.rest.userpostdatascoperel.mvc;

import grape.base.rest.userpostdatascoperel.form.DataScopeAssignUserPostForm;
import grape.base.rest.userpostdatascoperel.form.UserPostAssignDataScopeForm;
import grape.base.rest.userpostdatascoperel.mapper.UserPostDataScopeRelWebMapper;
import grape.base.rest.userpostdatascoperel.vo.UserPostDataScopeRelVo;
import grape.base.service.dataconstraint.api.IDataScopeService;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.userpostdatascoperel.api.IUserPostDataScopeRelService;
import grape.base.service.userpostdatascoperel.po.UserPostDataScopeRel;
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
 * 用户岗位数据范围关系表，多对多 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@RestController
@RequestMapping("/userpostdatascoperel")
@Api(tags = "用户岗位数据范围关系表，多对多相关接口")
public class UserPostDataScopeRelController extends BaseController<UserPostDataScopeRelVo, UserPostDataScopeRel> {

    @Autowired
    private UserPostDataScopeRelWebMapper currentWebMapper;
    @Autowired
    private IUserPostDataScopeRelService currentService;
    @Autowired
    private IDataScopeService iDataScopeService;

    @ApiOperation("角色分配数据范围")
    @RequiresPermissions("userPostDataScopeRel:single:userPostAssignDataScope")
    @PostMapping("/userpost/assign/datascope")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean userPostAssignDataScope(@RequestBody @Valid UserPostAssignDataScopeForm cf) {
        currentService.userPostAssignDataScope(cf.getUserPostId(),cf.getCheckedDataScopeIds(),cf.getUncheckedDataScopeIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据角色ID查询已分配的数据范围id")
    @RequiresPermissions("userPostDataScopeRel:single:queryByUserPostId")
    @GetMapping("/userpost/{userPostId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByUserPostId(@PathVariable String userPostId) {
        List<UserPostDataScopeRel> rels = currentService.getByUserPostId(userPostId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }

        return rels.stream().map(item -> item.getDataScopeId()).collect(Collectors.toList());
    }

    @ApiOperation("清空角色下的所有数据范围")
    @RequiresPermissions("userPostDataScopeRel:single:deleteByUserPostId")
    @DeleteMapping("/userpost/{userPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteByUserPostId(@PathVariable String userPostId) {
        boolean b = currentService.removeByUserPostId(userPostId);
        if (!b) {
            throw ExceptionTools.failRE("删除失败，可能要删除的数据不存在，请刷新数据再试");
        }
        return b;
    }


    @ApiOperation("数据范围分配角色")
    @RequiresPermissions("userPostDataScopeRel:single:dataScopeAssignUserPost")
    @PostMapping("/datascope/assign/userpost")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean dataScopeAssignUserPost(@RequestBody @Valid DataScopeAssignUserPostForm cf) {
        currentService.dataScopeAssignUserPost(cf.getDataScopeId(),cf.getCheckedUserPostIds(),cf.getUncheckedUserPostIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据数据范围ID查询已分配的数据范围id")
    @RequiresPermissions("userPostDataScopeRel:single:queryByDataScopeId")
    @GetMapping("/datascope/{dataScopeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByDataScopeId(@PathVariable String dataScopeId) {
        List<UserPostDataScopeRel> rels = currentService.getByDataScopeId(dataScopeId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }
        return rels.stream().map(item -> item.getDataScopeId()).collect(Collectors.toList());
    }

    @ApiOperation("清空数据范围下的所有角色")
    @RequiresPermissions("userPostDataScopeRel:single:deleteByDataScopeId")
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
