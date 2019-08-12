package grape.base.rest.mvc;


import com.baomidou.mybatisplus.core.metadata.IPage;
import common.code.fill.annocations.PojoConvert;
import grape.base.service.api.po.BaseUserPo;
import grape.base.service.api.service.IBaseUserService;
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
 * 用户表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-08-10
 */
@RestController
@RequestMapping("/base-user")
@Api(tags = "用户表")
public class BaseUserController extends BaseController<IBaseUserService, BaseUserPoVo, BaseUserPo, BaseUserPoCreateForm,BaseUserPoUpdateForm,BaseUserPoListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 用户表 单表专用，自动生成谨慎修改**************************************************/
    @Override
    @PojoConvert
    public BaseUserPo createFormToPo( BaseUserPoCreateForm cf) {
        return null;
    }

    @Override
    @PojoConvert
    public BaseUserPo updateFormToPo(BaseUserPoUpdateForm uf) {
        return null;
    }

    @Override
    @PojoConvert
    public BaseUserPo listPageFormToPo(BaseUserPoListPageForm lf) {
        return null;
    }

    @Override
    @PojoConvert
    public BaseUserPoVo poToVo(BaseUserPo po) {
        return null;
    }

     @Override
     @ApiOperation("[用户表]单表创建/添加数据")
     @RequiresPermissions("base-user-po:single:create")
     public BaseUserPoVo create(@RequestBody @Valid BaseUserPoCreateForm cf) {
         return super.create(cf);
     }

     @Override
     @ApiOperation("[用户表]单表根据ID查询")
     @RequiresPermissions("base-user-po:single:queryById")
     public BaseUserPoVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @Override
     @ApiOperation("[用户表]单表根据ID删除")
     @RequiresPermissions("base-user-po:single:deleteById")
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @Override
     @ApiOperation("[用户表]单表根据ID更新")
     @RequiresPermissions("base-user-po:single:updateById")
     public BaseUserPoVo update(@PathVariable Long id,@RequestBody @Valid BaseUserPoUpdateForm uf) {
         return super.update(id, uf);
     }

    @Override
    @ApiOperation("[用户表]单表分页列表")
    @RequiresPermissions("base-user-po:single:listPage")
    public IPage<BaseUserPoVo> listPage(BaseUserPoListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
