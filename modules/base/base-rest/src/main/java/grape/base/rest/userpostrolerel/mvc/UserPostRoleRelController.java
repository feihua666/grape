package grape.base.rest.userpostrolerel.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.userpostrolerel.form.UserPostRoleRelCreateForm;
import grape.base.rest.userpostrolerel.form.UserPostRoleRelUpdateForm;
import grape.base.rest.userpostrolerel.form.UserPostRoleRelListPageForm;
import grape.base.rest.userpostrolerel.vo.UserPostRoleRelVo;
import grape.base.rest.userpostrolerel.mapper.UserPostRoleRelWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.userpostrolerel.po.UserPostRoleRel;
import grape.base.service.userpostrolerel.api.IUserPostRoleRelService;
import java.util.List;
/**
 * <p>
 * 用户岗位与角色关系表,决定了用户的功能 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@RestController
@RequestMapping("/userst-role-rel")
@Api(tags = "用户岗位与角色关系表,决定了用户的功能")
public class UserPostRoleRelController extends BaseController<IUserPostRoleRelService,UserPostRoleRelWebMapper, UserPostRoleRelVo, UserPostRoleRel, UserPostRoleRelCreateForm,UserPostRoleRelUpdateForm,UserPostRoleRelListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 用户岗位与角色关系表,决定了用户的功能 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[用户岗位与角色关系表,决定了用户的功能]单表创建/添加数据")
     @RequiresPermissions("user-post-role-rel:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserPostRoleRelVo create(@RequestBody @Valid UserPostRoleRelCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[用户岗位与角色关系表,决定了用户的功能]单表根据ID查询")
     @RequiresPermissions("user-post-role-rel:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserPostRoleRelVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[用户岗位与角色关系表,决定了用户的功能]单表根据ID删除")
     @RequiresPermissions("user-post-role-rel:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[用户岗位与角色关系表,决定了用户的功能]单表根据ID更新")
     @RequiresPermissions("user-post-role-rel:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserPostRoleRelVo update(@PathVariable Long id,@RequestBody @Valid UserPostRoleRelUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[用户岗位与角色关系表,决定了用户的功能]单表分页列表")
    @RequiresPermissions("user-post-role-rel:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserPostRoleRelVo> listPage(UserPostRoleRelListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }

}
