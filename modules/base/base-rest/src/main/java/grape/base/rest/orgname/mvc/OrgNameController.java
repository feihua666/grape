package grape.base.rest.orgname.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.orgname.form.OrgNameListForm;
import grape.base.service.BaseLoginUser;
import grape.base.service.org.api.IOrgService;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseLoginUserController;
import grape.common.service.common.DefaultDataObject;
import grape.common.service.common.IDataObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.orgname.form.OrgNameCreateForm;
import grape.base.rest.orgname.form.OrgNameUpdateForm;
import grape.base.rest.orgname.form.OrgNameListPageForm;
import grape.base.rest.orgname.vo.OrgNameVo;
import grape.base.rest.orgname.mapper.OrgNameWebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.orgname.po.OrgName;
import grape.base.service.orgname.api.IOrgNameService;
import java.util.List;
/**
 * <p>
 * 组织树名称 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-12-20
 */
@RestController
@RequestMapping("/orgname")
@Api(tags = "组织树名称相关接口")
public class OrgNameController extends BaseLoginUserController<OrgNameVo, OrgName, BaseLoginUser> {
    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject( "dataObjectCodeOrgName");

    @Autowired
    private OrgNameWebMapper currentWebMapper;
    @Autowired
    private IOrgNameService currentService;

    @Autowired
    private IOrgService iOrgService;

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


     @ApiOperation("添加组织树名称")
     @RequiresPermissions("orgname:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public OrgNameVo create(@RequestBody @Valid OrgNameCreateForm cf) {
         // code 唯一检查
         if (currentService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
         }
         OrgName po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("根据ID查询组织树名称")
     @RequiresPermissions("orgname:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public OrgNameVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除组织树名称")
     @RequiresPermissions("orgname:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
        // 如果存在组织树，不能删除
         if (iOrgService.countByOrgNameId(id,null) > 0) {
             throw new RBaseException("存在组织树，不能删除");

         }
         return super.deleteById(id);
     }

     @ApiOperation("更新组织树名称")
     @RequiresPermissions("orgname:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public OrgNameVo update(@PathVariable String id,@RequestBody @Valid OrgNameUpdateForm uf) {
         OrgName po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("分页查询组织树名称")
    @RequiresPermissions("orgname:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<OrgNameVo> listPage(OrgNameListPageForm listPageForm) {
         OrgName po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }
    /**
     * 列出组织树名称
     * @param listForm
     * @return
     */
    @ApiOperation(value = "不分页查询组织树名称",notes = "可用于添加组织树做下拉或下拉搜索")
    @RequiresPermissions("orgname:single:list")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<OrgNameVo> list(OrgNameListForm listForm) {
        OrgName po = currentWebMapper.formToPo(listForm);
        return super.list(po);
    }
}
