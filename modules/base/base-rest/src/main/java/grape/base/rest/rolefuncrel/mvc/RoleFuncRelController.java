package grape.base.rest.rolefuncrel.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.rolefuncrel.form.RoleFuncRelCreateForm;
import grape.base.rest.rolefuncrel.form.RoleFuncRelUpdateForm;
import grape.base.rest.rolefuncrel.form.RoleFuncRelListPageForm;
import grape.base.rest.rolefuncrel.vo.RoleFuncRelVo;
import grape.base.rest.rolefuncrel.mapper.RoleFuncRelWebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.rolefuncrel.po.RoleFuncRel;
import grape.base.service.rolefuncrel.api.IRoleFuncRelService;
import java.util.List;
/**
 * <p>
 * 角色菜单功能关系表，多对多 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/role-func-rel")
@Api(tags = "角色菜单功能关系表，多对多")
public class RoleFuncRelController extends BaseController<RoleFuncRelVo, RoleFuncRel> {

    @Autowired
    private RoleFuncRelWebMapper currentWebMapper;
    @Autowired
    private IRoleFuncRelService currentService;



     @ApiOperation("[角色菜单功能关系表，多对多]单表创建/添加数据")
     @RequiresPermissions("role-func-rel:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public RoleFuncRelVo create(@RequestBody @Valid RoleFuncRelCreateForm cf) {
         RoleFuncRel po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("[角色菜单功能关系表，多对多]单表根据ID查询")
     @RequiresPermissions("role-func-rel:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public RoleFuncRelVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("[角色菜单功能关系表，多对多]单表根据ID删除")
     @RequiresPermissions("role-func-rel:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("[角色菜单功能关系表，多对多]单表根据ID更新")
     @RequiresPermissions("role-func-rel:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public RoleFuncRelVo update(@PathVariable String id,@RequestBody @Valid RoleFuncRelUpdateForm uf) {
         RoleFuncRel po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("[角色菜单功能关系表，多对多]单表分页列表")
    @RequiresPermissions("role-func-rel:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<RoleFuncRelVo> listPage(RoleFuncRelListPageForm listPageForm) {
         RoleFuncRel po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }

}
