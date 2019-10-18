package grape.base.rest.comp.mvc;

import grape.base.rest.BaseRestSuperController;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.comp.form.CompCreateForm;
import grape.base.rest.comp.form.CompUpdateForm;
import grape.base.rest.comp.form.CompListPageForm;
import grape.base.rest.comp.vo.CompVo;
import grape.base.rest.comp.mapper.CompWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.comp.po.Comp;
import grape.base.service.comp.api.ICompService;
import java.util.List;
/**
 * <p>
 * 公司 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@RestController
@RequestMapping("/comp")
@Api(tags = "公司相关接口")
public class CompController extends BaseRestSuperController<ICompService,CompWebMapper, CompVo, Comp, CompCreateForm,CompUpdateForm,CompListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 部门表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("添加公司")
     @RequiresPermissions("comp:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public CompVo create(@RequestBody @Valid CompCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("根据id查询公司")
     @RequiresPermissions("comp:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public CompVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("更新公司")
     @RequiresPermissions("comp:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public CompVo update(@PathVariable Long id,@RequestBody @Valid CompUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("公司分页查询")
    @RequiresPermissions("comp:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<CompVo> listPage(CompListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }

    @ApiOperation("公司树")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "父级id,不传获取根节点",name ="parentId",paramType = "query")
    })
    @RequiresPermissions("comp:single:tree")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<CompVo> tree( Long parentId) {
        return super.tree(parentId);
    }

    @Override
    public CompVo transVo(CompVo vo) {
        Dict dict = getDictById(vo.getTypeDictId());
        if (dict != null) {
            vo.setTypeDictCode(dict.getCode());
            vo.setTypeDictName(dict.getName());
        }
        return vo;
    }
}
