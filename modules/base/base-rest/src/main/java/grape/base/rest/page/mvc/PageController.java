package grape.base.rest.page.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.page.form.PagePoCreateForm;
import grape.base.rest.page.form.PagePoUpdateForm;
import grape.base.rest.page.form.PagePoListPageForm;
import grape.base.rest.page.vo.PagePoVo;
import grape.base.rest.page.mapperconverter.PagePoControllerMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.page.po.PagePo;
import grape.base.service.page.api.IPageService;
/**
 * <p>
 * 页面表，用于自动生成页面元素和关联功能菜单 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-01
 */
@RestController
@RequestMapping("/page")
@Api(tags = "页面表，用于自动生成页面元素和关联功能菜单")
public class PageController extends BaseController<IPageService,PagePoControllerMapper, PagePoVo, PagePo, PagePoCreateForm,PagePoUpdateForm,PagePoListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 页面表，用于自动生成页面元素和关联功能菜单 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[页面表，用于自动生成页面元素和关联功能菜单]单表创建/添加数据")
     @RequiresPermissions("page-po:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public PagePoVo create(@RequestBody @Valid PagePoCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[页面表，用于自动生成页面元素和关联功能菜单]单表根据ID查询")
     @RequiresPermissions("page-po:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public PagePoVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[页面表，用于自动生成页面元素和关联功能菜单]单表根据ID删除")
     @RequiresPermissions("page-po:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[页面表，用于自动生成页面元素和关联功能菜单]单表根据ID更新")
     @RequiresPermissions("page-po:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public PagePoVo update(@PathVariable Long id,@RequestBody @Valid PagePoUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[页面表，用于自动生成页面元素和关联功能菜单]单表分页列表")
    @RequiresPermissions("page-po:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<PagePoVo> listPage(PagePoListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
