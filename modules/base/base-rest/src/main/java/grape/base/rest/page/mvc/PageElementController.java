package grape.base.rest.page.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.page.form.PageElementCreateForm;
import grape.base.rest.page.form.PageElementUpdateForm;
import grape.base.rest.page.form.PageElementListPageForm;
import grape.base.rest.page.vo.PageElementVo;
import grape.base.rest.page.mapper.PageElementWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.page.po.PageElement;
import grape.base.service.page.api.IPageElementService;
/**
 * <p>
 * 页面元素表，用于用于描述页面上显示的元素抽象 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-06
 */
@RestController
@RequestMapping("/page-element")
@Api(tags = "页面元素表，用于用于描述页面上显示的元素抽象")
public class PageElementController extends BaseController<IPageElementService,PageElementWebMapper, PageElementVo, PageElement, PageElementCreateForm,PageElementUpdateForm,PageElementListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 页面元素表，用于用于描述页面上显示的元素抽象 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[页面元素表，用于用于描述页面上显示的元素抽象]单表创建/添加数据")
     @RequiresPermissions("page-element:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public PageElementVo create(@RequestBody @Valid PageElementCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[页面元素表，用于用于描述页面上显示的元素抽象]单表根据ID查询")
     @RequiresPermissions("page-element:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public PageElementVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[页面元素表，用于用于描述页面上显示的元素抽象]单表根据ID删除")
     @RequiresPermissions("page-element:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[页面元素表，用于用于描述页面上显示的元素抽象]单表根据ID更新")
     @RequiresPermissions("page-element:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public PageElementVo update(@PathVariable Long id,@RequestBody @Valid PageElementUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[页面元素表，用于用于描述页面上显示的元素抽象]单表分页列表")
    @RequiresPermissions("page-element:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<PageElementVo> listPage(PageElementListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
