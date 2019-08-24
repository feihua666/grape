package grape.base.rest.area.mvc;


import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import grape.base.rest.area.form.AreaPoCreateForm;
import grape.base.rest.area.form.AreaPoUpdateForm;
import grape.base.rest.area.form.AreaPoListPageForm;
import grape.base.rest.area.vo.AreaPoVo;
import grape.base.rest.area.mapperconverter.AreaPoControllerMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.area.po.AreaPo;
import grape.base.service.area.api.IAreaService;
/**
 * <p>
 * 区域表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-08-24
 */
@RestController
@RequestMapping("/area")
@Api(tags = "区域表")
public class AreaController extends BaseController<IAreaService,AreaPoControllerMapper, AreaPoVo, AreaPo, AreaPoCreateForm,AreaPoUpdateForm,AreaPoListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 区域表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[区域表]单表创建/添加数据")
     @RequiresPermissions("area-po:single:create")
     public AreaPoVo create(@RequestBody @Valid AreaPoCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[区域表]单表根据ID查询")
     @RequiresPermissions("area-po:single:queryById")
     public AreaPoVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[区域表]单表根据ID删除")
     @RequiresPermissions("area-po:single:deleteById")
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[区域表]单表根据ID更新")
     @RequiresPermissions("area-po:single:updateById")
     public AreaPoVo update(@PathVariable Long id,@RequestBody @Valid AreaPoUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[区域表]单表分页列表")
    @RequiresPermissions("area-po:single:listPage")
    public IPage<AreaPoVo> listPage(AreaPoListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
