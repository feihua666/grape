package grape.base.rest.dataconstraint.mvc;

import grape.base.rest.dataconstraint.form.DataScopeAssignDataForm;
import grape.base.rest.dataconstraint.mapper.DataScopeCustomRelWebMapper;
import grape.base.rest.dataconstraint.vo.DataScopeCustomRelVo;
import grape.base.service.BaseLoginUser;
import grape.base.service.dataconstraint.api.IDataScopeCustomRelService;
import grape.base.service.dataconstraint.po.DataScopeCustomRel;
import grape.common.exception.ExceptionTools;
import grape.common.rest.mvc.BaseLoginUserController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据范围约束自定义表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@RestController
@RequestMapping("/datascopecustomrel")
@Api(tags = "数据范围约束自定义表相关接口")
public class DataScopeCustomRelController extends BaseLoginUserController<DataScopeCustomRelVo, DataScopeCustomRel, BaseLoginUser> {

    @Autowired
    private DataScopeCustomRelWebMapper currentWebMapper;
    @Autowired
    private IDataScopeCustomRelService currentService;


    @ApiOperation("数据范围分配数据")
    @RequiresPermissions("datascopecustomrel:single:dataScopeAssignData")
    @PostMapping("/datascope/assign/data")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean dataScopeAssignData(@RequestBody @Valid DataScopeAssignDataForm cf) {
        currentService.dataScopeAssignData(cf.getDataScopeId(),cf.getCheckedDataIds(),cf.getUncheckedDataIds(),cf.getIsLazyLoad());
        return true;
    }

    @ApiOperation("根据数据范围ID查询已分配的数据id")
    @RequiresPermissions("datascopecustomrel:single:queryByDataScopeId")
    @GetMapping("/datascope/{dataScopeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> queryByDataScopeId(@PathVariable String dataScopeId) {
        List<DataScopeCustomRel> rels = currentService.getByDataScopeId(dataScopeId);
        if (rels == null) {
            ExceptionTools.dataNotExistRE("数据不存在");
        }

        return rels.stream().map(item -> item.getDataId()).collect(Collectors.toList());
    }

    @ApiOperation("清空数据范围下的所有数据")
    @RequiresPermissions("datascopecustomrel:single:deleteByDataScopeId")
    @DeleteMapping("/datascope/{dataScopeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteByDataScopeId(@PathVariable String dataScopeId) {
        boolean b = currentService.removeByDataScopeId(dataScopeId);
        if (!b) {
            throw ExceptionTools.failRE("删除失败，可能要删除的数据不存在，请刷新数据再试");
        }
        return b;
    }

}
