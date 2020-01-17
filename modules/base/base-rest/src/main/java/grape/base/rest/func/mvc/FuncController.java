package grape.base.rest.func.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.func.form.FuncCreateForm;
import grape.base.rest.func.form.FuncEnableForm;
import grape.base.rest.func.form.FuncListPageForm;
import grape.base.rest.func.form.FuncUpdateForm;
import grape.base.rest.func.mapper.FuncWebMapper;
import grape.base.rest.func.vo.FuncVo;
import grape.common.service.loginuser.LoginUser;
import grape.base.service.func.api.IFuncService;
import grape.base.service.func.po.Func;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseTreeLoginUserController;
import grape.common.rest.vo.TreeNodeVo;
import grape.common.service.common.dataconstraint.DefaultDataObject;
import grape.common.service.common.dataconstraint.IDataObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 菜单功能 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/func")
@Api(tags = "功能相关接口")
public class FuncController extends BaseTreeLoginUserController<FuncVo, Func,LoginUser> {

    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject( "dataObjectCodeFunc");

    @Autowired
    private FuncWebMapper funcWebMapper;
    @Autowired
    private IFuncService iFuncService;

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

    /**
     * 功能添加
     * @param cf
     * @return
     */
     @ApiOperation("添加功能")
     @PreAuthorize("hasAuthority('func:single:create')")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public FuncVo create(@RequestBody @Valid FuncCreateForm cf) {
         // code 唯一检查
         if (iFuncService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
         }
         // 应用id必须和父级的应用id一致
         if (!isStrEmpty(cf.getParentId())) {
             Func parent = iFuncService.getById(cf.getParentId());
             if (!isEqual(cf.getApplicationId(), parent.getApplicationId())) {
                 throw new InvalidParamsException("选择的应用和父级所在应用不一致");
             }
         }
         Func func = funcWebMapper.formToPo(cf);
         func.setIsDisabled(false);
         return super.create(func);
     }

    /**
     * 根据id查询功能，用于编辑回显或查看详情
     * @param id
     * @return
     */
     @ApiOperation(value = "根据id查询功能",notes = "用于更新初始化值或查看详情")
     @PreAuthorize("hasAuthority('func:single:queryById')")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public FuncVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

    /**
     * 删除功能
     * @param id
     * @return
     */
     @ApiOperation("删除功能")
     @PreAuthorize("hasAuthority('func:single:deleteById')")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

    /**
     * 更新功能
     * @param id
     * @param uf
     * @return
     */
     @ApiOperation("更新功能")
     @PreAuthorize("hasAuthority('func:single:updateById')")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public FuncVo update(@PathVariable String id,@RequestBody @Valid FuncUpdateForm uf) {
         // 应用id必须和父级的应用id一致
         if (!isStrEmpty(uf.getParentId())) {
             Func parent = iFuncService.getById(uf.getParentId());
             if (!isEqual(uf.getApplicationId(), parent.getApplicationId())) {
                 throw new InvalidParamsException("选择的应用和父级所在应用不一致");
             }
         }
         // 如果有子节点，不允许修改应用id
         if (iFuncService.getChildrenCount(id) > 0) {
             Func funcDb = iFuncService.getById(uf.getParentId());
             if (!isEqual(uf.getApplicationId(), funcDb.getApplicationId())) {
                 throw new InvalidParamsException("当前功能存在子节点，不允许变更应用");
             }

         }


         Func func = funcWebMapper.formToPo(uf);
         func.setId(id);
         return super.update(func);
     }

    /**
     * 启用或禁用功能，禁用后导航项目不再展示，赐予的权限也不会被分配
     * @param id
     * @param form
     * @return
     */
    @ApiOperation("启用或禁用功能")
    @PreAuthorize("hasAuthority('func:single:enable')")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public FuncVo enable(@PathVariable String id, @RequestBody @Valid FuncEnableForm form) {
        Func func = new Func();
        func.setId(id);
        func.setIsDisabled(form.getDisabled());
        func.setVersion(form.getVersion());
        func.setDisabledReason(form.getDisabledReason());
        return super.update(func);
    }
    /**
     * 用于后台管理列表页面
     * @param listPageForm
     * @return
     */
    @ApiOperation("分页查询功能")
    @PreAuthorize("hasAuthority('func:single:listPage')")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<FuncVo> listPage(FuncListPageForm listPageForm) {
        Func func = funcWebMapper.formToPo(listPageForm);
         return super.listPage(func,listPageForm);
     }

    /**
     * 检查树结构是否完整
     * @return
     */
    @ApiOperation(value = "检查树结构是否完整",notes = "主要用于检查树结构的完整性")
    @PreAuthorize("hasAuthority('func:single:checkTreeStruct')")
    @GetMapping("/tree/check/struct")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkTreeStruct() {
        return super.checkTreeStruct();
    }
    /**
     * 懒加载树，只能一级一级钻取加载，目前用于功能的树组件
     * @param parentId
     * @return
     */
    @ApiOperation(value = "功能树",notes = "主要用于选择上级")
    @PreAuthorize("hasAuthority('func:single:getByParentId')")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<TreeNodeVo<FuncVo>> tree(String parentId) {
        List<FuncVo> r = super.getByParentId(parentId);
        return super.listToTreeNodeVo(r);
    }

    /**
     * 用于后台管理导航，即左侧功能展示
     * @return
     */
    @ApiOperation(value = "导航功能树",notes = "查询当前登录用户可用的功能")
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/tree/nav")
    @ResponseStatus(HttpStatus.OK)
    public List<TreeNodeVo<FuncVo>> treeNav(String applicationId) {
        List<FuncVo> funcList = super.list(new Func().setApplicationId(applicationId).setIsDisabled(false));
        return super.listToTree(funcList);
    }

}
