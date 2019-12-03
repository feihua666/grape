package grape.base.rest.dataconstraint.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.service.BaseLoginUser;
import grape.base.service.dataconstraint.api.IDataScopeCustomRelService;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseLoginUserController;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.dataconstraint.form.DataScopeCreateForm;
import grape.base.rest.dataconstraint.form.DataScopeUpdateForm;
import grape.base.rest.dataconstraint.form.DataScopeListPageForm;
import grape.base.rest.dataconstraint.vo.DataScopeVo;
import grape.base.rest.dataconstraint.mapper.DataScopeWebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.dataconstraint.api.IDataScopeService;
import java.util.List;
/**
 * <p>
 * 数据范围约束表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@RestController
@RequestMapping("/datascope")
@Api(tags = "数据范围相关接口")
public class DataScopeController extends BaseLoginUserController<DataScopeVo, DataScope, BaseLoginUser> {

    @Autowired
    private DataScopeWebMapper currentWebMapper;
    @Autowired
    private IDataScopeService currentService;
    @Autowired
    private IDataScopeCustomRelService iDataScopeCustomRelService;


     @ApiOperation("添加数据范围")
     @RequiresPermissions("datascope:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public DataScopeVo create(@RequestBody @Valid DataScopeCreateForm cf) {
         // code 唯一检查
         if (currentService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
         }
         DataScope po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("根据ID查询数据范围")
     @RequiresPermissions("datascope:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public DataScopeVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除数据范围")
     @RequiresPermissions("datascope:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("更新数据范围")
     @RequiresPermissions("datascope:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public DataScopeVo update(@PathVariable String id,@RequestBody @Valid DataScopeUpdateForm uf) {
         // 如果是从自定义更新为不是自定义，判断自定义数据是否是否存在
         DataScope dataScope = currentService.getById(id);
         
         if (!dataScope.getIsCustom().equals(uf.getIsCustom()) && iDataScopeCustomRelService.existByDataScopeId(id)) {
             throw new RBaseException("存在自定义数据，不能变更自定义状态");
         }
         DataScope po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("分页查询数据范围")
    @RequiresPermissions("datascope:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<DataScopeVo> listPage(DataScopeListPageForm listPageForm) {
         DataScope po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }

}
