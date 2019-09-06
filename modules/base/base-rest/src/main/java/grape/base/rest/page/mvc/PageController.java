package grape.base.rest.page.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.page.form.PageCreateForm;
import grape.base.rest.page.form.PageUpdateForm;
import grape.base.rest.page.form.PageListPageForm;
import grape.base.rest.page.vo.PageVo;
import grape.base.rest.page.mapper.PageWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.page.po.Page;
import grape.base.service.page.api.IPageService;
/**
 * <p>
 * 页面表，用于自动生成页面元素和关联功能菜单 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-06
 */
@RestController
@RequestMapping("/page")
@Api(tags = "页面表，用于自动生成页面元素和关联功能菜单")
public class PageController extends BaseController<IPageService,PageWebMapper, PageVo, Page, PageCreateForm,PageUpdateForm,PageListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 页面表，用于自动生成页面元素和关联功能菜单 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[页面表，用于自动生成页面元素和关联功能菜单]单表创建/添加数据")
     @RequiresPermissions("page:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public PageVo create(@RequestBody @Valid PageCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[页面表，用于自动生成页面元素和关联功能菜单]单表根据ID查询")
     @RequiresPermissions("page:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public PageVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[页面表，用于自动生成页面元素和关联功能菜单]单表根据ID删除")
     @RequiresPermissions("page:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[页面表，用于自动生成页面元素和关联功能菜单]单表根据ID更新")
     @RequiresPermissions("page:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public PageVo update(@PathVariable Long id,@RequestBody @Valid PageUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[页面表，用于自动生成页面元素和关联功能菜单]单表分页列表")
    @RequiresPermissions("page:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<PageVo> listPage(PageListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
