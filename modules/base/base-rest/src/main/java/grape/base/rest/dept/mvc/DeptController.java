package grape.base.rest.dept.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.BaseRestSuperController;
import grape.base.rest.dept.form.DeptCreateForm;
import grape.base.rest.dept.form.DeptListPageForm;
import grape.base.rest.dept.form.DeptUpdateForm;
import grape.base.rest.dept.mapper.DeptWebMapper;
import grape.base.rest.dept.vo.DeptVo;
import grape.base.service.comp.api.ICompService;
import grape.base.service.comp.po.Comp;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
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
 * 部门 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@RestController
@RequestMapping("/dept")
@Api(tags = "部门相关接口")
public class DeptController extends BaseRestSuperController<IDeptService,DeptWebMapper, DeptVo, Dept, DeptCreateForm,DeptUpdateForm,DeptListPageForm> {

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

    @Override
    public DeptVo transVo(DeptVo vo) {
        Dict dict = getDictById(vo.getTypeDictId());
        if (dict != null) {
            vo.setTypeDictCode(dict.getCode());
            vo.setTypeDictName(dict.getName());
        }
        Comp comp = getCompById(vo.getCompId());
        if (comp != null) {
            vo.setCompName(comp.getName());
        }
        return vo;
    }
}
