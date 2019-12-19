package grape.base.rest.dept.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.dept.form.DeptCreateForm;
import grape.base.rest.dept.form.DeptListPageForm;
import grape.base.rest.dept.form.DeptUpdateForm;
import grape.base.rest.dept.mapper.DeptWebMapper;
import grape.base.rest.dept.vo.DeptVo;
import grape.base.service.BaseLoginUser;
import grape.base.service.comp.api.ICompService;
import grape.base.service.comp.po.Comp;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.po.User;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseTreeController;
import grape.common.rest.mvc.BaseTreeLoginUserController;
import grape.common.rest.vo.TreeNodeVo;
import grape.common.service.common.DefaultDataObject;
import grape.common.service.common.IDataObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@RestController
@RequestMapping("/dept")
@Api(tags = "部门相关接口")
public class DeptController extends BaseTreeLoginUserController<DeptVo, Dept, BaseLoginUser> {

    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject( "dataObjectCodeDept");

    @Autowired
    private DeptWebMapper deptWebMapper;
    @Autowired
    private IDeptService iDeptService;

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

     @ApiOperation("添加部门")
     @RequiresPermissions("dept:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public DeptVo create(@RequestBody @Valid DeptCreateForm cf) {
         // code 唯一检查
         if (iDeptService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
         }
         // 添加子部门的公司必须和父级公司一致
         if (cf.getParentId() != null) {
             Dept dept = iDeptService.getById(cf.getParentId());
             if(!isEqual(dept.getCompId(),cf.getCompId())){
                 throw new InvalidParamsException("子部门的公司必须同上级部门公司一致");
             }
         }
         Dept dept = deptWebMapper.formToPo(cf);
         return super.create(dept);
     }

     @ApiOperation("根据id查询部门")
     @RequiresPermissions("dept:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public DeptVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除部门")
     @RequiresPermissions("dept:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("更新部门")
     @RequiresPermissions("dept:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public DeptVo update(@PathVariable String id,@RequestBody @Valid DeptUpdateForm uf) {
         // 如果部门有子节点，不能变更公司
         Dept deptDb = iDeptService.getById(id);
         if (iDeptService.getChildrenCount(id) > 0 || deptDb.getParentId() != null) {
             if(!isEqual(deptDb.getCompId(),uf.getCompId())){
                 throw new InvalidParamsException("当前部门不允许变更公司");
             }
         }
         Dept dept = deptWebMapper.formToPo(uf);
         dept.setId(id);
         return super.update(dept);
     }

    @ApiOperation("分页查询部门")
    @RequiresPermissions("dept:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<DeptVo> listPage(DeptListPageForm listPageForm) {
        Dept dept = deptWebMapper.formToPo(listPageForm);
         return super.listPage(dept,listPageForm);
     }
    /**
     * 检查树结构是否完整
     * @return
     */
    @ApiOperation(value = "检查树结构是否完整",notes = "主要用于检查树结构的完整性")
    @RequiresPermissions("dept:single:checkTreeStruct")
    @GetMapping("/tree/check/struct")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkTreeStruct() {
        return super.checkTreeStruct();
    }

    @ApiOperation("部门树")
    @RequiresPermissions("dept:single:getByParentId")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<TreeNodeVo<DeptVo>> tree(String parentId,String compId) {
        Dept query = new Dept();
        query.setCompId(compId);
        return super.listToTreeNodeVo(super.getByParentId(parentId,query));
    }

}
