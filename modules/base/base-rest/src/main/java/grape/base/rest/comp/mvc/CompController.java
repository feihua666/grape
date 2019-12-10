package grape.base.rest.comp.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import grape.base.rest.comp.form.CompCreateForm;
import grape.base.rest.comp.form.CompListPageForm;
import grape.base.rest.comp.form.CompUpdateForm;
import grape.base.rest.comp.mapper.CompWebMapper;
import grape.base.rest.comp.vo.CompVo;
import grape.base.service.BaseLoginUser;
import grape.base.service.comp.api.ICompService;
import grape.base.service.comp.po.Comp;
import grape.common.exception.ExceptionTools;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseTreeLoginUserController;
import grape.common.rest.vo.TreeNodeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
public class CompController extends BaseTreeLoginUserController<CompVo, Comp,BaseLoginUser> {

    @Autowired
    private CompWebMapper compWebMapper;
    @Autowired
    private ICompService iCompService;

     @ApiOperation("添加公司")
     @RequiresPermissions("comp:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public CompVo create(@RequestBody @Valid CompCreateForm cf) {

         // code 唯一检查
         if (iCompService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
         }
         Comp comp = compWebMapper.formToPo(cf);
         return create(comp);
     }

     @ApiOperation("根据id查询公司")
     @RequiresPermissions("comp:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public CompVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

    @ApiOperation("删除公司")
    @RequiresPermissions("comp:single:deleteById")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteById(@PathVariable String id) {
        return super.deleteById(id);
    }

     @ApiOperation("更新公司")
     @RequiresPermissions("comp:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public CompVo update(@PathVariable String id,@RequestBody @Valid CompUpdateForm uf) {
         Comp poQuery = compWebMapper.formToPo(uf);
         poQuery.setId(id);
         return update(poQuery);
     }

    @ApiOperation("公司分页查询")
    @RequiresPermissions("comp:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<CompVo> listPage(CompListPageForm listForm) {

        BaseLoginUser loginUser = getLoginUser();
        Comp poQuery = compWebMapper.formToPo(listForm);
        // 超级管理员处理
        if (loginUser.getIsSuperAdmin()) {
            return listPage(poQuery, listForm);
        }

        // 添加权限过滤，只查看本公司及以下公司
        IPage<Comp> page = iCompService.page(new Page(listForm.getCurrent(),listForm.getSize()),poQuery,loginUser.getCurrentUserPost().getCompId());
        if (page.getTotal() == 0) {
            throw ExceptionTools.dataNotExistRE("暂无数据");
        }
        return pagePoToVo(page);

    }
    /**
     * 检查树结构是否完整
     * @return
     */
    @ApiOperation(value = "检查树结构是否完整",notes = "主要用于检查树结构的完整性")
    @RequiresPermissions("comp:single:checkTreeStruct")
    @GetMapping("/tree/check/struct")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkTreeStruct() {
        return super.checkTreeStruct();
    }

    /**
     * 公司树
     * @param parentId
     * @return
     */
    @ApiOperation("公司树")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "父级id,不传获取根节点",name ="parentId",paramType = "query")
    })
    @RequiresPermissions("comp:single:getByParentId")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public  List<TreeNodeVo<CompVo>> tree(String parentId) {
        List<CompVo> r = super.getByParentId(parentId);
        return super.listToTreeNodeVo(r);
    }

}
