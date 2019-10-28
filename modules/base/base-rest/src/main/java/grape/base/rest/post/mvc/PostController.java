package grape.base.rest.post.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.post.form.PostCreateForm;
import grape.base.rest.post.form.PostUpdateForm;
import grape.base.rest.post.form.PostListPageForm;
import grape.base.rest.post.vo.PostVo;
import grape.base.rest.post.mapper.PostWebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.post.po.Post;
import grape.base.service.post.api.IPostService;
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
@Api(tags = "岗位表")
public class PostController extends BaseController<PostVo, Post> {

    @Autowired
    private PostWebMapper currentWebMapper;
    @Autowired
    private IPostService currentService;



     @ApiOperation("[岗位表]单表创建/添加数据")
     @RequiresPermissions("post:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public PostVo create(@RequestBody @Valid PostCreateForm cf) {
         Post po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("[岗位表]单表根据ID查询")
     @RequiresPermissions("post:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public PostVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("[岗位表]单表根据ID删除")
     @RequiresPermissions("post:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("[岗位表]单表根据ID更新")
     @RequiresPermissions("post:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public PostVo update(@PathVariable String id,@RequestBody @Valid PostUpdateForm uf) {
         Post po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("[岗位表]单表分页列表")
    @RequiresPermissions("post:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<PostVo> listPage(PostListPageForm listPageForm) {
         Post po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }

}
