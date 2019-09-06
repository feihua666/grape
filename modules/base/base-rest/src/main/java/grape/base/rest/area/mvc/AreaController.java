package grape.base.rest.area.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.area.form.AreaCreateForm;
import grape.base.rest.area.form.AreaUpdateForm;
import grape.base.rest.area.form.AreaListPageForm;
import grape.base.rest.area.vo.AreaVo;
import grape.base.rest.area.mapper.AreaWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.area.po.Area;
import grape.base.service.area.api.IAreaService;
/**
 * <p>
 * 区域表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-06
 */
@RestController
@RequestMapping("/area")
@Api(tags = "区域表")
public class AreaController extends BaseController<IAreaService,AreaWebMapper, AreaVo, Area, AreaCreateForm,AreaUpdateForm,AreaListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 区域表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[区域表]单表创建/添加数据")
     @RequiresPermissions("area:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public AreaVo create(@RequestBody @Valid AreaCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[区域表]单表根据ID查询")
     @RequiresPermissions("area:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public AreaVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[区域表]单表根据ID删除")
     @RequiresPermissions("area:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[区域表]单表根据ID更新")
     @RequiresPermissions("area:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public AreaVo update(@PathVariable Long id,@RequestBody @Valid AreaUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[区域表]单表分页列表")
    @RequiresPermissions("area:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<AreaVo> listPage(AreaListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
