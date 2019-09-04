package grape.base.rest.dict.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.dict.form.DictPoCreateForm;
import grape.base.rest.dict.form.DictPoUpdateForm;
import grape.base.rest.dict.form.DictPoListPageForm;
import grape.base.rest.dict.vo.DictPoVo;
import grape.base.rest.dict.mapperconverter.DictPoControllerMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.dict.po.DictPo;
import grape.base.service.dict.api.IDictService;
/**
 * <p>
 * 字典表,提供值与编码映射，用于下拉框或组合选择使用 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-01
 */
@RestController
@RequestMapping("/dict")
@Api(tags = "字典表,提供值与编码映射，用于下拉框或组合选择使用")
public class DictController extends BaseController<IDictService,DictPoControllerMapper, DictPoVo, DictPo, DictPoCreateForm,DictPoUpdateForm,DictPoListPageForm> {

    // 请在这里添加额外的方法
    //todo
    @ApiOperation("[字典表]根据字典组编码查询字典项")
    @RequiresPermissions("dict-po:single:queryItemByGroupCode")
    @GetMapping("/group/{code}/item-list")
    @ResponseStatus(HttpStatus.OK)
    public DictPoVo queryItemByGroupCode(@PathVariable String code) {
        //getService().query().getEntity().setCode(code)
        //return super.queryById(id);
        return null;
    }




    /************************分割线，以下代码为 字典表,提供值与编码映射，用于下拉框或组合选择使用 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[字典表]单表创建/添加数据")
     @RequiresPermissions("dict-po:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public DictPoVo create(@RequestBody @Valid DictPoCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[字典表]单表根据ID查询")
     @RequiresPermissions("dict-po:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public DictPoVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[字典表]单表根据ID删除")
     @RequiresPermissions("dict-po:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[字典表]单表根据ID更新")
     @RequiresPermissions("dict-po:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public DictPoVo update(@PathVariable Long id,@RequestBody @Valid DictPoUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[字典表]单表分页列表")
    @RequiresPermissions("dict-po:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<DictPoVo> listPage(DictPoListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
