package grape.base.rest.func.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.func.form.FuncCreateForm;
import grape.base.rest.func.form.FuncUpdateForm;
import grape.base.rest.func.form.FuncListPageForm;
import grape.base.rest.func.vo.FuncVo;
import grape.base.rest.func.mapper.FuncWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.func.po.Func;
import grape.base.service.func.api.IFuncService;
import java.util.List;
/**
 * <p>
 * 菜单功能表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/func")
@Api(tags = "菜单功能表")
public class FuncController extends BaseController<IFuncService,FuncWebMapper, FuncVo, Func, FuncCreateForm,FuncUpdateForm,FuncListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 菜单功能表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[菜单功能表]单表创建/添加数据")
     @RequiresPermissions("func:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public FuncVo create(@RequestBody @Valid FuncCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[菜单功能表]单表根据ID查询")
     @RequiresPermissions("func:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public FuncVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[菜单功能表]单表根据ID删除")
     @RequiresPermissions("func:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[菜单功能表]单表根据ID更新")
     @RequiresPermissions("func:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public FuncVo update(@PathVariable Long id,@RequestBody @Valid FuncUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[菜单功能表]单表分页列表")
    @RequiresPermissions("func:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<FuncVo> listPage(FuncListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }

    @ApiOperation("[菜单功能表]根据父级查询")
    //@RequiresPermissions("func:single:tree")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<FuncVo> tree( Long parentId) {
        return super.tree(parentId);
    }
}
