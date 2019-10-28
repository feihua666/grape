package grape.base.rest.userpost.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.userpost.form.UserPostCreateForm;
import grape.base.rest.userpost.form.UserPostUpdateForm;
import grape.base.rest.userpost.form.UserPostListPageForm;
import grape.base.rest.userpost.vo.UserPostVo;
import grape.base.rest.userpost.mapper.UserPostWebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.userpost.po.UserPost;
import grape.base.service.userpost.api.IUserPostService;
import java.util.List;
/**
 * <p>
 * 用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/userst")
@Api(tags = "用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职")
public class UserPostController extends BaseController<UserPostVo, UserPost> {

    @Autowired
    private UserPostWebMapper currentWebMapper;
    @Autowired
    private IUserPostService currentService;



     @ApiOperation("[用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职]单表创建/添加数据")
     @RequiresPermissions("user-post:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserPostVo create(@RequestBody @Valid UserPostCreateForm cf) {
         UserPost po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("[用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职]单表根据ID查询")
     @RequiresPermissions("user-post:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserPostVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("[用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职]单表根据ID删除")
     @RequiresPermissions("user-post:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("[用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职]单表根据ID更新")
     @RequiresPermissions("user-post:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserPostVo update(@PathVariable String id,@RequestBody @Valid UserPostUpdateForm uf) {
         UserPost po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("[用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职]单表分页列表")
    @RequiresPermissions("user-post:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserPostVo> listPage(UserPostListPageForm listPageForm) {
         UserPost po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }

}
