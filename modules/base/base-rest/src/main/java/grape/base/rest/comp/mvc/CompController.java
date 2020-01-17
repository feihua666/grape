package grape.base.rest.comp.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.comp.form.CompCreateForm;
import grape.base.rest.comp.form.CompListPageForm;
import grape.base.rest.comp.form.CompUpdateForm;
import grape.base.rest.comp.mapper.CompWebMapper;
import grape.base.rest.comp.vo.CompVo;
import grape.common.service.loginuser.LoginUser;
import grape.base.service.comp.api.ICompService;
import grape.base.service.comp.po.Comp;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseTreeLoginUserController;
import grape.common.rest.vo.TreeNodeVo;
import grape.common.service.common.dataconstraint.DefaultDataObject;
import grape.common.service.common.dataconstraint.IDataObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class CompController extends BaseTreeLoginUserController<CompVo, Comp,LoginUser> {

    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject( "dataObjectCodeComp");


    @Autowired
    private CompWebMapper compWebMapper;
    @Autowired
    private ICompService iCompService;

    /**
     * 开启全局
     * @return
     */
    @Override
    public boolean isEnableDefaultDataObject() {
        // 判断是否存在关闭的情况
        if (getEnableDefaultDataObjectKeyValue() != null) {
            return (boolean) getEnableDefaultDataObjectKeyValue();
        }
        enableDefaultDataObject();
        return super.isEnableDefaultDataObject();
    }

    @Override
    protected String defaultDataObjectCode() {
        return defaultDataObjectCode.dataObjectCode();
    }

     @ApiOperation("添加公司")
     @PreAuthorize("hasAuthority('comp:single:create')")
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
     @PreAuthorize("hasAuthority('comp:single:queryById')")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public CompVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

    @ApiOperation("删除公司")
    @PreAuthorize("hasAuthority('comp:single:deleteById')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteById(@PathVariable String id) {
        return super.deleteById(id);
    }

     @ApiOperation("更新公司")
     @PreAuthorize("hasAuthority('comp:single:updateById')")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public CompVo update(@PathVariable String id,@RequestBody @Valid CompUpdateForm uf) {
         Comp poQuery = compWebMapper.formToPo(uf);
         poQuery.setId(id);
         return update(poQuery);
     }

    @ApiOperation("公司分页查询")
    @PreAuthorize("hasAuthority('comp:single:listPage')")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<CompVo> listPage(CompListPageForm listForm) {
         Comp poQuery = compWebMapper.formToPo(listForm);
        return listPage(poQuery, listForm);
    }
    /**
     * 检查树结构是否完整
     * @return
     */
    @ApiOperation(value = "检查树结构是否完整",notes = "主要用于检查树结构的完整性")
    @PreAuthorize("hasAuthority('comp:single:checkTreeStruct')")
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
    @PreAuthorize("hasAuthority('comp:single:getByParentId')")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public  List<TreeNodeVo<CompVo>> tree(String parentId) {
        List<CompVo> r = super.getByParentId(parentId);
        return super.listToTreeNodeVo(r);
    }

}
