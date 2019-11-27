package grape.base.rest.post.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.post.form.*;
import grape.base.rest.post.mapper.PostWebMapper;
import grape.base.rest.post.vo.PostVo;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.post.api.IPostService;
import grape.base.service.post.dto.PostListQuery;
import grape.base.service.post.po.Post;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 岗位表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/post")
@Api(tags = "岗位相关接口")
public class PostController extends BaseController<PostVo, Post> {

    @Autowired
    private PostWebMapper currentWebMapper;
    @Autowired
    private IPostService currentService;
    @Autowired
    private IDictService iDictService;
    @Autowired
    private IDeptService iDeptService;

     @ApiOperation("添加岗位")
     @RequiresPermissions("post:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public PostVo create(@RequestBody @Valid PostCreateForm cf) {
         // code 唯一检查
         if (currentService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
         }
         Post po = currentWebMapper.formToPo(cf);
         po.setIsDisabled(false);
         return super.create(po);
     }

     @ApiOperation("根据id查询岗位")
     @RequiresPermissions("post:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public PostVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除岗位")
     @RequiresPermissions("post:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("更新岗位")
     @RequiresPermissions("post:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public PostVo update(@PathVariable String id,@RequestBody @Valid PostUpdateForm uf) {
         Post po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("分页查询岗位")
    @RequiresPermissions("post:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<PostVo> listPage(PostListPageForm listPageForm) {
         Post po = currentWebMapper.formToPo(listPageForm);
         // 包括公共的
        po.setIsPublic(true);
         return super.listPage(po,listPageForm);
     }
    /**
     * 列出岗位
     * @param listForm
     * @return
     */
    @ApiOperation(value = "不分页查询岗位",notes = "可用于添加岗位做下拉或下拉搜索")
    @RequiresPermissions("post:single:list")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<PostVo> list(PostListForm listForm) {
        PostListQuery po = currentWebMapper.formToQuery(listForm);
        return posToVos(currentService.list(po));
    }
    /**
     * 启用或禁用
     * @param id
     * @param form
     * @return
     */
    @ApiOperation("启用或禁用")
    @RequiresPermissions("post:single:enable")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public PostVo enable(@PathVariable String id, @RequestBody @Valid PostEnableForm form) {
        Post post = new Post();
        post.setId(id);
        post.setIsDisabled(form.getDisabled());
        post.setVersion(form.getVersion());
        post.setDisabledReason(form.getDisabledReason());
        return super.update(post);
    }
}
