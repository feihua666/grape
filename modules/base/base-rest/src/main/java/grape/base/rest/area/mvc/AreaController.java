package grape.base.rest.area.mvc;

import grape.base.rest.BaseRestSuperController;
import grape.base.rest.comp.vo.CompVo;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.area.form.AreaCreateForm;
import grape.base.rest.area.form.AreaUpdateForm;
import grape.base.rest.area.form.AreaListPageForm;
import grape.base.rest.area.vo.AreaVo;
import grape.base.rest.area.mapper.AreaWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.area.po.Area;
import grape.base.service.area.api.IAreaService;

import java.util.List;

/**
 * <p>
 * 区域 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/area")
@Api(tags = "区域相关接口")
public class AreaController extends BaseRestSuperController<IAreaService,AreaWebMapper, AreaVo, Area, AreaCreateForm,AreaUpdateForm,AreaListPageForm> {

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 区域表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("添加区域")
     @RequiresPermissions("area:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public AreaVo create(@RequestBody @Valid AreaCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("根据id查询区域")
     @RequiresPermissions("area:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public AreaVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("更新区域")
     @RequiresPermissions("area:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public AreaVo update(@PathVariable Long id,@RequestBody @Valid AreaUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("区域分页查询")
    @RequiresPermissions("area:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<AreaVo> listPage(AreaListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }

    @ApiOperation("区域树")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "父级id,不传获取根节点",name = "parentId")
    })
    @RequiresPermissions("area:single:tree")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<AreaVo> tree(Long parentId) {
        return super.tree(parentId);
    }

    @Override
    public AreaVo transVo(AreaVo vo) {
        Dict dict = getDictById(vo.getTypeDictId());
        if (dict != null) {
            vo.setTypeDictCode(dict.getCode());
            vo.setTypeDictName(dict.getName());
        }

        return vo;
    }
}
