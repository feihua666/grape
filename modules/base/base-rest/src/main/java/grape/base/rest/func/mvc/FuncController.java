package grape.base.rest.func.mvc;

import grape.base.rest.BaseRestSuperController;
import grape.base.service.BaseLoginUser;
import grape.base.service.dept.po.Dept;
import grape.base.service.dict.po.Dict;
import grape.common.exception.ExceptionTools;
import grape.common.rest.vo.TreeNodeVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.func.form.FuncCreateForm;
import grape.base.rest.func.form.FuncUpdateForm;
import grape.base.rest.func.form.FuncListPageForm;
import grape.base.rest.func.vo.FuncVo;
import grape.base.rest.func.mapper.FuncWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.func.po.Func;
import grape.base.service.func.api.IFuncService;

import java.util.ArrayList;
import java.util.Iterator;
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
public class FuncController extends BaseRestSuperController<IFuncService,FuncWebMapper, FuncVo, Func, FuncCreateForm,FuncUpdateForm,FuncListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 菜单功能表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("添加功能")
     @RequiresPermissions("func:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public FuncVo create(@RequestBody @Valid FuncCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("根据id查询功能")
     @RequiresPermissions("func:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public FuncVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("删除功能")
     @RequiresPermissions("func:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("更新功能")
     @RequiresPermissions("func:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public FuncVo update(@PathVariable Long id,@RequestBody @Valid FuncUpdateForm uf) {
         return super.update(id, uf);
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
         return super.listPage(listPageForm);
     }

    @ApiOperation(value = "功能树")
    @RequiresPermissions("func:single:tree")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<FuncVo> tree( Long parentId) {
        return super.tree(parentId);
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
            funcList = getService().getByTypes(menuAndPage,false);
        }else{
            funcList = getService().getMenuAndPageByRoleId(loginUser.getCurrentRole().getId(),false);
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
    @Override
    public FuncVo transVo(FuncVo vo) {
        Dict dict = getDictById(vo.getTypeDictId());
        if (dict != null) {
            vo.setTypeDictCode(dict.getCode());
            vo.setTypeDictName(dict.getName());
        }

        return vo;
    }
}
