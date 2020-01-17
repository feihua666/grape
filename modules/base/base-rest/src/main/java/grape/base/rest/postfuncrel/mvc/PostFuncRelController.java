package grape.base.rest.postfuncrel.mvc;

import grape.base.rest.postfuncrel.form.FuncAssignPostForm;
import grape.base.rest.postfuncrel.form.PostAssignFuncForm;
import grape.base.rest.postfuncrel.mapper.PostFuncRelWebMapper;
import grape.base.rest.postfuncrel.vo.PostFuncRelVo;
import grape.base.service.postfuncrel.api.IPostFuncRelService;
import grape.base.service.postfuncrel.po.PostFuncRel;
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
 * 岗位功能关系表，多对多 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-12-17
 */
@RestController
@RequestMapping("/postfuncrel")
@Api(tags = "岗位功能关系表，多对多相关接口")
public class PostFuncRelController extends BaseController<PostFuncRelVo, PostFuncRel> {

    @Autowired
    private PostFuncRelWebMapper currentWebMapper;
    @Autowired
    private IPostFuncRelService currentService;



    @ApiOperation("岗位分配功能")
    @PreAuthorize("hasAuthority('postFuncRel:single:postAssignFunc')")
    @PostMapping("/post/assign/func")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean postAssignFunc(@RequestBody @Valid PostAssignFuncForm cf) {
        currentService.postAssignFunc(cf.getPostId(),cf.getCheckedFuncIds(),cf.getUncheckedFuncIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据岗位ID查询已分配的功能id")
    @PreAuthorize("hasAuthority('postFuncRel:single:queryByPostId')")
    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByPostId(@PathVariable String postId) {
        List<PostFuncRel> rels = currentService.getByPostId(postId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }

        return rels.stream().map(item -> item.getFuncId()).collect(Collectors.toList());
    }

    @ApiOperation("清空岗位下的所有功能")
    @PreAuthorize("hasAuthority('postFuncRel:single:deleteByPostId')")
    @DeleteMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteByPostId(@PathVariable String postId) {
        boolean b = currentService.removeByPostId(postId);
        if (!b) {
            throw ExceptionTools.failRE("删除失败，可能要删除的数据不存在，请刷新数据再试");
        }
        return b;
    }


    @ApiOperation("功能分配岗位")
    @PreAuthorize("hasAuthority('postFuncRel:single:funcAssignPost')")
    @PostMapping("/func/assign/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean funcAssignPost(@RequestBody @Valid FuncAssignPostForm cf) {
        currentService.funcAssignPost(cf.getFuncId(),cf.getCheckedPostIds(),cf.getUncheckedPostIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据功能ID查询已分配的功能id")
    @PreAuthorize("hasAuthority('postFuncRel:single:queryByFuncId')")
    @GetMapping("/func/{funcId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByFuncId(@PathVariable String funcId) {
        List<PostFuncRel> rels = currentService.getByFuncId(funcId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }
        return rels.stream().map(item -> item.getFuncId()).collect(Collectors.toList());
    }

    @ApiOperation("清空功能下的所有岗位")
    @PreAuthorize("hasAuthority('postFuncRel:single:deleteByFuncId')")
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
