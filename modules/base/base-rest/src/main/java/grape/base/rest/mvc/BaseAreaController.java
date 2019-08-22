package grape.base.rest.mvc;


import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.service.api.po.BaseAreaPo;
import grape.base.service.api.service.IBaseAreaService;
import grape.common.rest.mvc.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
/**
 * <p>
 * 区域表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-08-10
 */
@RestController
@RequestMapping("/base-area")
@Api(tags = "区域表")
public class BaseAreaController extends BaseController<IBaseAreaService,BaseAreaConMapper, BaseAreaPoVo, BaseAreaPo, BaseAreaPoCreateForm,BaseAreaPoUpdateForm,BaseAreaPoListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 区域表 单表专用，自动生成谨慎修改**************************************************/


     @ApiOperation("[区域表]单表创建/添加数据")
     @RequiresPermissions("base-area-po:single:create")
     public BaseAreaPoVo create(@RequestBody @Valid BaseAreaPoCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[区域表]单表根据ID查询")
     @RequiresPermissions("base-area-po:single:queryById")
     public BaseAreaPoVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[区域表]单表根据ID删除")
     @RequiresPermissions("base-area-po:single:deleteById")
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[区域表]单表根据ID更新")
     @RequiresPermissions("base-area-po:single:updateById")
     public BaseAreaPoVo update(@PathVariable Long id,@RequestBody @Valid BaseAreaPoUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[区域表]单表分页列表")
    @RequiresPermissions("base-area-po:single:listPage")
    public IPage<BaseAreaPoVo> listPage(BaseAreaPoListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
