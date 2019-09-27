package grape.base.rest.dict.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.dict.form.DictCreateForm;
import grape.base.rest.dict.form.DictUpdateForm;
import grape.base.rest.dict.form.DictListPageForm;
import grape.base.rest.dict.vo.DictVo;
import grape.base.rest.dict.mapper.DictWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.dict.po.Dict;
import grape.base.service.dict.api.IDictService;
/**
 * <p>
 * 字典表,提供值与编码映射，用于下拉框或组合选择使用 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/dict")
@Api(tags = "字典表,提供值与编码映射，用于下拉框或组合选择使用")
public class DictController extends BaseController<IDictService,DictWebMapper, DictVo, Dict, DictCreateForm,DictUpdateForm,DictListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 字典表,提供值与编码映射，用于下拉框或组合选择使用 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[字典表,提供值与编码映射，用于下拉框或组合选择使用]单表创建/添加数据")
     @RequiresPermissions("dict:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public DictVo create(@RequestBody @Valid DictCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[字典表,提供值与编码映射，用于下拉框或组合选择使用]单表根据ID查询")
     @RequiresPermissions("dict:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public DictVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[字典表,提供值与编码映射，用于下拉框或组合选择使用]单表根据ID删除")
     @RequiresPermissions("dict:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[字典表,提供值与编码映射，用于下拉框或组合选择使用]单表根据ID更新")
     @RequiresPermissions("dict:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public DictVo update(@PathVariable Long id,@RequestBody @Valid DictUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[字典表,提供值与编码映射，用于下拉框或组合选择使用]单表分页列表")
    @RequiresPermissions("dict:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<DictVo> listPage(DictListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
