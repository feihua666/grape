package grape.base.rest.mvc;


import common.code.fill.annocations.PojoConvert;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.api.po.BaseUserAuthPo;
import grape.base.service.api.service.IBaseUserAuthService;
/**
 * <p>
 * 用户认证表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-08-10
 */
@RestController
@RequestMapping("/base-user-auth")
@Api(tags = "用户认证表")
public class BaseUserAuthController extends BaseController<IBaseUserAuthService, BaseUserAuthPoVo, BaseUserAuthPo, BaseUserAuthPoCreateForm,BaseUserAuthPoUpdateForm,BaseUserAuthPoListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 用户认证表 单表专用，自动生成谨慎修改**************************************************/
    @Override
    @PojoConvert
    public BaseUserAuthPo createFormToPo( BaseUserAuthPoCreateForm cf) {
        return null;
    }

    @Override
    @PojoConvert
    public BaseUserAuthPo updateFormToPo(BaseUserAuthPoUpdateForm uf) {
        return null;
    }

    @Override
    @PojoConvert
    public BaseUserAuthPo listPageFormToPo(BaseUserAuthPoListPageForm lf) {
        return null;
    }

    @Override
    @PojoConvert
    public BaseUserAuthPoVo poToVo(BaseUserAuthPo po) {
        return null;
    }

     @Override
     @ApiOperation("[用户认证表]单表创建/添加数据")
     @RequiresPermissions("base-user-auth-po:single:create")
     public BaseUserAuthPoVo create(@RequestBody @Valid BaseUserAuthPoCreateForm cf) {
         return super.create(cf);
     }

     @Override
     @ApiOperation("[用户认证表]单表根据ID查询")
     @RequiresPermissions("base-user-auth-po:single:queryById")
     public BaseUserAuthPoVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @Override
     @ApiOperation("[用户认证表]单表根据ID删除")
     @RequiresPermissions("base-user-auth-po:single:deleteById")
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @Override
     @ApiOperation("[用户认证表]单表根据ID更新")
     @RequiresPermissions("base-user-auth-po:single:updateById")
     public BaseUserAuthPoVo update(@PathVariable Long id,@RequestBody @Valid BaseUserAuthPoUpdateForm uf) {
         return super.update(id, uf);
     }

    @Override
    @ApiOperation("[用户认证表]单表分页列表")
    @RequiresPermissions("base-user-auth-po:single:listPage")
    public IPage<BaseUserAuthPoVo> listPage(BaseUserAuthPoListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
