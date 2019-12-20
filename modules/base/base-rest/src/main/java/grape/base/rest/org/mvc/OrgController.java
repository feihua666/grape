package grape.base.rest.org.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.service.BaseLoginUser;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.rest.mvc.BaseTreeLoginUserController;
import grape.common.service.common.DefaultDataObject;
import grape.common.service.common.IDataObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.org.form.OrgCreateForm;
import grape.base.rest.org.form.OrgUpdateForm;
import grape.base.rest.org.form.OrgListPageForm;
import grape.base.rest.org.vo.OrgVo;
import grape.base.rest.org.mapper.OrgWebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.org.po.Org;
import grape.base.service.org.api.IOrgService;
import java.util.List;
import grape.common.rest.vo.TreeNodeVo;
/**
 * <p>
 * 组织树 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-12-20
 */
@RestController
@RequestMapping("/org")
@Api(tags = "组织树相关接口")
public class OrgController extends BaseTreeLoginUserController<OrgVo, Org, BaseLoginUser> {

    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject( "dataObjectCodeOrg");

    @Autowired
    private OrgWebMapper currentWebMapper;
    @Autowired
    private IOrgService currentService;

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


     @ApiOperation("添加组织树")
     @RequiresPermissions("org:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public OrgVo create(@RequestBody @Valid OrgCreateForm cf) {
         Org po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("根据ID查询组织树")
     @RequiresPermissions("org:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public OrgVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除组织树")
     @RequiresPermissions("org:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("更新组织树")
     @RequiresPermissions("org:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public OrgVo update(@PathVariable String id,@RequestBody @Valid OrgUpdateForm uf) {
         // 如果有子节点，不允许修改应用id
         if (currentService.getChildrenCount(id) > 0) {
             throw new InvalidParamsException("当前功能存在子节点，不允许变更应用");
         }
         Org po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("分页查询组织树")
    @RequiresPermissions("org:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<OrgVo> listPage(OrgListPageForm listPageForm) {
         Org po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }

    @ApiOperation("组织树树")
    @RequiresPermissions("org:single:tree")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<TreeNodeVo<OrgVo>> tree( String parentId) {
        return super.listToTreeNodeVo(super.getByParentId(parentId));
    }
}
