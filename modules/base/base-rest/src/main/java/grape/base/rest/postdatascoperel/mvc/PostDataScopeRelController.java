package grape.base.rest.postdatascoperel.mvc;

import grape.base.rest.postdatascoperel.form.DataScopeAssignPostForm;
import grape.base.rest.postdatascoperel.form.PostAssignDataScopeForm;
import grape.base.rest.postdatascoperel.mapper.PostDataScopeRelWebMapper;
import grape.base.rest.postdatascoperel.vo.PostDataScopeRelVo;
import grape.base.service.dataconstraint.api.IDataScopeService;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.postdatascoperel.api.IPostDataScopeRelService;
import grape.base.service.postdatascoperel.po.PostDataScopeRel;
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
 * 岗位数据范围关系表，多对多 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@RestController
@RequestMapping("/postdatascoperel")
@Api(tags = "岗位数据范围关系表，多对多相关接口")
public class PostDataScopeRelController extends BaseController<PostDataScopeRelVo, PostDataScopeRel> {

    @Autowired
    private PostDataScopeRelWebMapper currentWebMapper;
    @Autowired
    private IPostDataScopeRelService currentService;
    @Autowired
    private IDataScopeService iDataScopeService;


    @ApiOperation("岗位分配数据范围")
    @RequiresPermissions("postDataScopeRel:single:postAssignDataScope")
    @PostMapping("/post/assign/datascope")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean postAssignDataScope(@RequestBody @Valid PostAssignDataScopeForm cf) {
        // 检查每一个数据对象只能选中一个数据范围
        Collection<DataScope> dataScopes = iDataScopeService.listByIds(cf.getCheckedDataScopeIds());
        Set<String> stringSet = dataScopes.stream().map(item -> item.getDataObjectId()).collect(Collectors.toSet());
        if (stringSet.size() != dataScopes.size()) {
            throw new InvalidParamsException("每个数据对象只能选择一个数据范围");
        }
        currentService.postAssignDataScope(cf.getPostId(),cf.getCheckedDataScopeIds(),cf.getUncheckedDataScopeIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据岗位ID查询已分配的数据范围id")
    @RequiresPermissions("postDataScopeRel:single:queryByPostId")
    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByPostId(@PathVariable String postId) {
        List<PostDataScopeRel> rels = currentService.getByPostId(postId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }

        return rels.stream().map(item -> item.getDataScopeId()).collect(Collectors.toList());
    }

    @ApiOperation("清空岗位下的所有数据范围")
    @RequiresPermissions("postDataScopeRel:single:deleteByPostId")
    @DeleteMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteByPostId(@PathVariable String postId) {
        boolean b = currentService.removeByPostId(postId);
        if (!b) {
            throw ExceptionTools.failRE("删除失败，可能要删除的数据不存在，请刷新数据再试");
        }
        return b;
    }


    @ApiOperation("数据范围分配岗位")
    @RequiresPermissions("postDataScopeRel:single:dataScopeAssignPost")
    @PostMapping("/datascope/assign/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean dataScopeAssignPost(@RequestBody @Valid DataScopeAssignPostForm cf) {
        currentService.dataScopeAssignPost(cf.getDataScopeId(),cf.getCheckedPostIds(),cf.getUncheckedPostIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据数据范围ID查询已分配的数据范围id")
    @RequiresPermissions("postDataScopeRel:single:queryByDataScopeId")
    @GetMapping("/datascope/{dataScopeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByDataScopeId(@PathVariable String dataScopeId) {
        List<PostDataScopeRel> rels = currentService.getByDataScopeId(dataScopeId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }
        return rels.stream().map(item -> item.getDataScopeId()).collect(Collectors.toList());
    }

    @ApiOperation("清空数据范围下的所有岗位")
    @RequiresPermissions("postDataScopeRel:single:deleteByDataScopeId")
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
