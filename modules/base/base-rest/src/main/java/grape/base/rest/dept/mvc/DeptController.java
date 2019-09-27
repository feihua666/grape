package grape.base.rest.dept.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.dept.form.DeptCreateForm;
import grape.base.rest.dept.form.DeptUpdateForm;
import grape.base.rest.dept.form.DeptListPageForm;
import grape.base.rest.dept.vo.DeptVo;
import grape.base.rest.dept.mapper.DeptWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.dept.po.Dept;
import grape.base.service.dept.api.IDeptService;
import java.util.List;
/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@RestController
@RequestMapping("/dept")
@Api(tags = "部门表")
public class DeptController extends BaseController<IDeptService,DeptWebMapper, DeptVo, Dept, DeptCreateForm,DeptUpdateForm,DeptListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 部门表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[部门表]单表创建/添加数据")
     @RequiresPermissions("dept:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public DeptVo create(@RequestBody @Valid DeptCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[部门表]单表根据ID查询")
     @RequiresPermissions("dept:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public DeptVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[部门表]单表根据ID删除")
     @RequiresPermissions("dept:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[部门表]单表根据ID更新")
     @RequiresPermissions("dept:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public DeptVo update(@PathVariable Long id,@RequestBody @Valid DeptUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[部门表]单表分页列表")
    @RequiresPermissions("dept:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<DeptVo> listPage(DeptListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }

    @ApiOperation("[菜单功能表]根据父级查询")
    @RequiresPermissions("dept:single:tree")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<DeptVo> tree( Long parentId) {
        return super.tree(parentId);
    }
}
