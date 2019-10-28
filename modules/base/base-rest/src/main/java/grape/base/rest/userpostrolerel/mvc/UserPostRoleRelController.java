package grape.base.rest.userpostrolerel.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/userst-role-rel")
@Api(tags = "用户岗位与角色关系表,决定了用户的功能")
public class UserPostRoleRelController extends BaseController<UserPostRoleRelVo, UserPostRoleRel> {

    @Autowired
    private UserPostRoleRelWebMapper currentWebMapper;
    @Autowired
    private IUserPostRoleRelService currentService;



     @ApiOperation("[用户岗位与角色关系表,决定了用户的功能]单表创建/添加数据")
     @RequiresPermissions("user-post-role-rel:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserPostRoleRelVo create(@RequestBody @Valid UserPostRoleRelCreateForm cf) {
         UserPostRoleRel po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("[用户岗位与角色关系表,决定了用户的功能]单表根据ID查询")
     @RequiresPermissions("user-post-role-rel:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserPostRoleRelVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("[用户岗位与角色关系表,决定了用户的功能]单表根据ID删除")
     @RequiresPermissions("user-post-role-rel:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("[用户岗位与角色关系表,决定了用户的功能]单表根据ID更新")
     @RequiresPermissions("user-post-role-rel:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserPostRoleRelVo update(@PathVariable String id,@RequestBody @Valid UserPostRoleRelUpdateForm uf) {
         UserPostRoleRel po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("[用户岗位与角色关系表,决定了用户的功能]单表分页列表")
    @RequiresPermissions("user-post-role-rel:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserPostRoleRelVo> listPage(UserPostRoleRelListPageForm listPageForm) {
         UserPostRoleRel po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }

}
