package grape.base.rest.page.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.page.form.PageElementPoCreateForm;
import grape.base.rest.page.form.PageElementPoUpdateForm;
import grape.base.rest.page.form.PageElementPoListPageForm;
import grape.base.rest.page.vo.PageElementPoVo;
import grape.base.rest.page.mapperconverter.PageElementPoControllerMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.page.po.PageElementPo;
import grape.base.service.page.api.IPageElementService;
/**
 * <p>
 * 页面元素表，用于用于描述页面上显示的元素抽象 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-01
 */
@RestController
@RequestMapping("/page-element")
@Api(tags = "页面元素表，用于用于描述页面上显示的元素抽象")
public class PageElementController extends BaseController<IPageElementService,PageElementPoControllerMapper, PageElementPoVo, PageElementPo, PageElementPoCreateForm,PageElementPoUpdateForm,PageElementPoListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 页面元素表，用于用于描述页面上显示的元素抽象 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[页面元素表，用于用于描述页面上显示的元素抽象]单表创建/添加数据")
     @RequiresPermissions("page-element-po:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public PageElementPoVo create(@RequestBody @Valid PageElementPoCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[页面元素表，用于用于描述页面上显示的元素抽象]单表根据ID查询")
     @RequiresPermissions("page-element-po:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public PageElementPoVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[页面元素表，用于用于描述页面上显示的元素抽象]单表根据ID删除")
     @RequiresPermissions("page-element-po:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[页面元素表，用于用于描述页面上显示的元素抽象]单表根据ID更新")
     @RequiresPermissions("page-element-po:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public PageElementPoVo update(@PathVariable Long id,@RequestBody @Valid PageElementPoUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[页面元素表，用于用于描述页面上显示的元素抽象]单表分页列表")
    @RequiresPermissions("page-element-po:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<PageElementPoVo> listPage(PageElementPoListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
