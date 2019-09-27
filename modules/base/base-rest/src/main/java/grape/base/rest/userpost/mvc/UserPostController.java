package grape.base.rest.userpost.mvc;

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
 * @since 2019-09-26
 */
@RestController
@RequestMapping("/userst")
@Api(tags = "用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职")
public class UserPostController extends BaseController<IUserPostService,UserPostWebMapper, UserPostVo, UserPost, UserPostCreateForm,UserPostUpdateForm,UserPostListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职]单表创建/添加数据")
     @RequiresPermissions("user-post:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserPostVo create(@RequestBody @Valid UserPostCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职]单表根据ID查询")
     @RequiresPermissions("user-post:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserPostVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职]单表根据ID删除")
     @RequiresPermissions("user-post:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职]单表根据ID更新")
     @RequiresPermissions("user-post:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserPostVo update(@PathVariable Long id,@RequestBody @Valid UserPostUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职]单表分页列表")
    @RequiresPermissions("user-post:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserPostVo> listPage(UserPostListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }

}
