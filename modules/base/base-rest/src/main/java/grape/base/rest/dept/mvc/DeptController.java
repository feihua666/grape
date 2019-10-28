package grape.base.rest.dept.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.BaseRestSuperController;
import grape.base.rest.dept.form.DeptCreateForm;
import grape.base.rest.dept.form.DeptListPageForm;
import grape.base.rest.dept.form.DeptUpdateForm;
import grape.base.rest.dept.mapper.DeptWebMapper;
import grape.base.rest.dept.vo.DeptVo;
import grape.base.service.comp.po.Comp;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.dict.po.Dict;
import grape.base.service.user.po.User;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.vo.TreeNodeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
public class DeptController extends BaseRestSuperController<DeptVo, Dept> {


    @Autowired
    private DeptWebMapper deptWebMapper;
    @Autowired
    private IDeptService iDeptService;


     @ApiOperation("添加部门")
     @RequiresPermissions("dept:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public DeptVo create(@RequestBody @Valid DeptCreateForm cf) {
         // code 唯一检查
         if (iDeptService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
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
    public List<TreeNodeVo<DeptVo>> tree(String parentId) {
        return super.listToTreeNodeVo(super.getByParentId(parentId));
    }

    @Override
    public DeptVo transVo(DeptVo vo) {
        Dict dict = getDictById(vo.getTypeDictId());
        if (dict != null) {
            vo.setTypeDictCode(dict.getCode());
            vo.setTypeDictName(dict.getName());
        }
        Comp comp = getCompById(vo.getCompId());
        if (comp != null) {
            vo.setCompName(comp.getName());
        }
        User user = getUserById(vo.getMasterUserId());
        if (user != null) {
            vo.setMasterUserName(user.getNickname());
        }

        return vo;
    }
}
