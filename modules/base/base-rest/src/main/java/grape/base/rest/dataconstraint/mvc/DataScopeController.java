package grape.base.rest.dataconstraint.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.dataconstraint.vo.DataScopeVirtualTreeNodeVo;
import grape.base.rest.setting.dataconstraint.BaseDataConstraintServiceImpl;
import grape.base.service.BaseLoginUser;
import grape.base.service.dataconstraint.api.IDataObjectService;
import grape.base.service.dataconstraint.api.IDataScopeCustomRelService;
import grape.base.service.dataconstraint.dto.DataConstraintDto;
import grape.base.service.dataconstraint.dto.DataObjectAndScopeDto;
import grape.base.service.dataconstraint.po.DataObject;
import grape.common.exception.ExceptionTools;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseLoginUserController;
import grape.common.rest.vo.TreeNodeVo;
import grape.common.service.common.ConstraintContent;
import grape.common.service.common.DefaultDataObject;
import grape.common.service.common.IDataObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.dataconstraint.api.IDataScopeService;

import java.util.*;
import java.util.stream.Collectors;

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
    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject("dataObjectCodeDataScope");

    @Autowired
    private DataScopeWebMapper currentWebMapper;
    @Autowired
    private IDataScopeService currentService;
    @Autowired
    private IDataScopeCustomRelService iDataScopeCustomRelService;
    @Autowired
    private IDataObjectService iDataObjectService;

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

    /**
     * 数据范围树
     * @return
     */
    @ApiOperation(value = "数据范围树",notes = "数据对象和数据范围组成一个树,一次性加载所有数据")
    @RequiresPermissions("datascope:tree")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<TreeNodeVo<DataScopeVirtualTreeNodeVo>> treeAll() {

        List<TreeNodeVo<DataScopeVirtualTreeNodeVo>> r = new ArrayList<>();

        List<DataScope> list = currentService.list(null, parseConstraint(defaultDataObjectCode()));
        if (isEmpty(list)) {
            ExceptionTools.dataNotExistRE("当前用户未分配数据范围");
        }
        Map<String, List<DataScope>> map = new HashMap<>();
        for (DataScope dataScope : list) {
            List<DataScope> dataScopes = map.get(dataScope.getDataObjectId());
            if (dataScope == null) {
                dataScopes = new ArrayList<>();
                map.put(dataScope.getDataObjectId(), dataScopes);
            }
            dataScopes.add(dataScope);
        }
        if (!map.isEmpty()) {
            for (Map.Entry<String, List<DataScope>> stringListEntry : map.entrySet()) {
                DataObject dataObject = iDataObjectService.getById(stringListEntry.getKey());
                combine(r,dataObject,stringListEntry.getValue());

            }
        }

        return r;
    }


    /**
     * 组装为一整个树
     * @param r
     * @param dataObject
     * @param dataScopes
     */
    private void combine(List<TreeNodeVo<DataScopeVirtualTreeNodeVo>> r,DataObject dataObject,List<DataScope> dataScopes){
        TreeNodeVo<DataScopeVirtualTreeNodeVo> treeNode;
        TreeNodeVo<DataScopeVirtualTreeNodeVo> treeNode1;
        DataScopeVirtualTreeNodeVo dataScopeVirtualTreeNodeVo;
        dataScopeVirtualTreeNodeVo = new DataScopeVirtualTreeNodeVo();

        dataScopeVirtualTreeNodeVo.setIsDataObject(true);
        dataScopeVirtualTreeNodeVo.setId(dataObject.getId());
        dataScopeVirtualTreeNodeVo.setIsDisabled(true);
        dataScopeVirtualTreeNodeVo.setName(dataObject.getName());
        dataScopeVirtualTreeNodeVo.setParentId(null);
        dataScopeVirtualTreeNodeVo.setVersion(dataObject.getVersion());


        treeNode = new TreeNodeVo<>();
        treeNode.setNode(dataScopeVirtualTreeNodeVo);
        treeNode.setId(dataScopeVirtualTreeNodeVo.getId());
        treeNode.setVersion(dataScopeVirtualTreeNodeVo.getVersion());
        if (!isEmpty(dataScopes)) {
            treeNode.setHasChildren(true);
            treeNode.setChildren(new ArrayList<>());
            for (DataScope dataScope : dataScopes) {
                dataScopeVirtualTreeNodeVo = new DataScopeVirtualTreeNodeVo();
                dataScopeVirtualTreeNodeVo.setIsDataObject(false);
                dataScopeVirtualTreeNodeVo.setId(dataScope.getId());
                dataScopeVirtualTreeNodeVo.setIsDisabled(false);
                dataScopeVirtualTreeNodeVo.setName(dataScope.getName());
                dataScopeVirtualTreeNodeVo.setParentId(dataObject.getId());
                dataScopeVirtualTreeNodeVo.setVersion(dataScope.getVersion());
                treeNode1 = new TreeNodeVo<>(dataScopeVirtualTreeNodeVo, null, false);
                treeNode1.setId(dataScopeVirtualTreeNodeVo.getId());
                treeNode1.setVersion(dataScopeVirtualTreeNodeVo.getVersion());
                treeNode.getChildren().add(treeNode1);
            }
        }
        r.add(treeNode);
    }
}
