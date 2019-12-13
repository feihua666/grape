package grape.base.rest.func.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.func.form.FuncCreateForm;
import grape.base.rest.func.form.FuncEnableForm;
import grape.base.rest.func.form.FuncListPageForm;
import grape.base.rest.func.form.FuncUpdateForm;
import grape.base.rest.func.mapper.FuncWebMapper;
import grape.base.rest.func.vo.FuncVo;
import grape.base.service.BaseLoginUser;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.func.api.IFuncService;
import grape.base.service.func.po.Func;
import grape.common.exception.ExceptionTools;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseTreeController;
import grape.common.rest.vo.TreeNodeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
public class FuncController extends BaseTreeController<FuncVo, Func> {


    @Autowired
    private FuncWebMapper funcWebMapper;
    @Autowired
    private IFuncService iFuncService;
    @Autowired
    private IDictService iDictService;

    /**
     * 功能添加
     * @param cf
     * @return
     */
     @ApiOperation("添加功能")
     @RequiresPermissions("func:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public FuncVo create(@RequestBody @Valid FuncCreateForm cf) {
         // code 唯一检查
         if (iFuncService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
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
     @RequiresPermissions("func:single:queryById")
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
     @RequiresPermissions("func:single:deleteById")
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
     @RequiresPermissions("func:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public FuncVo update(@PathVariable String id,@RequestBody @Valid FuncUpdateForm uf) {
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
    @RequiresPermissions("func:single:enable")
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
    @RequiresPermissions("func:single:listPage")
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
    @RequiresPermissions("func:single:checkTreeStruct")
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
    @RequiresPermissions("func:single:getByParentId")
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
    @RequiresPermissions("user")
    @GetMapping("/tree/nav")
    @ResponseStatus(HttpStatus.OK)
    public List<TreeNodeVo<FuncVo>> treeNav() {
        BaseLoginUser loginUser = BaseLoginUser.getLoginUser();
        List<Func> funcList;
        // 超级管理员查全部的
        if (loginUser.getIsSuperAdmin()) {
            List<String> menuAndPage = new ArrayList<>(2);
            menuAndPage.add(Func.TypeDictItem.menu.name());
            menuAndPage.add(Func.TypeDictItem.page.name());
            funcList = iFuncService.getByTypes(menuAndPage,false);
        }else{
            if (loginUser.getCurrentRole() == null) {
                throw new RBaseException("当前用户没有可用角色");
            }
            funcList = iFuncService.getMenuAndPageByRoleId(loginUser.getCurrentRole().getId(),false);
        }
        if (isListEmpty(funcList)) {
            throw ExceptionTools.dataNotExistRE("暂无数据");
        }
        List<FuncVo> result = new ArrayList<>();
        for (Func func : funcList) {
            FuncVo funcVo = getMapperConverter().poToVo(func);
            funcVo = transVo(funcVo);
            result.add(funcVo);
        }

        return super.listToTree(result);
    }

}
