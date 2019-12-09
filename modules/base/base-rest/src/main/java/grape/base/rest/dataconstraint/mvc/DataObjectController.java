package grape.base.rest.dataconstraint.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.dataconstraint.form.DataObjectCreateForm;
import grape.base.rest.dataconstraint.form.DataObjectListForm;
import grape.base.rest.dataconstraint.form.DataObjectListPageForm;
import grape.base.rest.dataconstraint.form.DataObjectUpdateForm;
import grape.base.rest.dataconstraint.mapper.DataObjectWebMapper;
import grape.base.rest.dataconstraint.vo.DataObjectVo;
import grape.base.service.BaseLoginUser;
import grape.base.service.dataconstraint.api.IDataObjectService;
import grape.base.service.dataconstraint.api.IDataScopeService;
import grape.base.service.dataconstraint.po.DataObject;
import grape.base.service.dataconstraint.po.DataScope;
import grape.common.exception.ExceptionTools;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseLoginUserController;
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
 * 数据对象表 前端控制器
 * 说明：数据对象编码与代码息息相关，不能随便变动数据库中的编码，必须对代码保持一致
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@RestController
@RequestMapping("/dataobject")
@Api(tags = "数据对象表相关接口")
public class DataObjectController extends BaseLoginUserController<DataObjectVo, DataObject, BaseLoginUser> {

    @Autowired
    private DataObjectWebMapper currentWebMapper;
    @Autowired
    private IDataObjectService currentService;

    @Autowired
    private IDataScopeService iDataScopeService;


     @ApiOperation("添加数据对象")
     @RequiresPermissions("dataobject:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public DataObjectVo create(@RequestBody @Valid DataObjectCreateForm cf) {
         // code 唯一检查
         if (currentService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
         }
         DataObject po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("根据ID查询数据对象")
     @RequiresPermissions("dataobject:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public DataObjectVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除数据对象")
     @RequiresPermissions("dataobject:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         // 已存在数据范围不能删除
         if (!isListEmpty(iDataScopeService.getByDataObjectId(id))) {
             throw ExceptionTools.failRE("删除失败，存在关联的数据范围");
         }
         return super.deleteById(id);
     }

     @ApiOperation("更新数据对象")
     @RequiresPermissions("dataobject:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public DataObjectVo update(@PathVariable String id,@RequestBody @Valid DataObjectUpdateForm uf) {
         DataObject po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("分页查询数据对象")
    @RequiresPermissions("dataobject:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<DataObjectVo> listPage(DataObjectListPageForm listPageForm) {
         DataObject po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }
    /**
     * 列出数据对象
     * @param listForm
     * @return
     */
    @ApiOperation(value = "不分页查询数据对象",notes = "可用于添加职级做下拉或下拉搜索")
    @RequiresPermissions("dataobject:single:list")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<DataObjectVo> list(DataObjectListForm listForm) {
        DataObject po = currentWebMapper.formToPo(listForm);
        return super.list(po);
    }

    @ApiOperation(value = "数据范围id查询数据对象",notes = "主要用于自定义数据范围参数判断")
    @RequiresPermissions("dataobject:single:queryByDataScopeId")
    @GetMapping("/dataobject/{dataScopeId}")
    @ResponseStatus(HttpStatus.OK)
    public DataObjectVo queryByDataScopeId(@PathVariable String dataScopeId) {
        DataScope dataScope = iDataScopeService.getById(dataScopeId);
        DataObject dataObject = currentService.getById(dataScope.getDataObjectId());
        if (dataObject == null) {
            throw ExceptionTools.dataNotExistRE("数据对象不存在");
        }
        return transVo(currentWebMapper.poToVo(dataObject));
    }
}
