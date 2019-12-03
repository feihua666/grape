package grape.base.rest.area.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.area.form.AreaCreateForm;
import grape.base.rest.area.form.AreaListPageForm;
import grape.base.rest.area.form.AreaUpdateForm;
import grape.base.rest.area.mapper.AreaWebMapper;
import grape.base.rest.area.vo.AreaVo;
import grape.base.service.BaseLoginUser;
import grape.base.service.area.api.IAreaService;
import grape.base.service.area.po.Area;
import grape.base.service.dict.api.IDictService;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseTreeLoginUserController;
import grape.common.rest.vo.TreeNodeVo;
import grape.common.tools.PinyinDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 区域 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Slf4j
@RestController
@RequestMapping("/area")
@Api(tags = "区域相关接口")
public class AreaController extends BaseTreeLoginUserController<AreaVo, Area, BaseLoginUser> {

    @Autowired
    private AreaWebMapper areaWebMapper;
    @Autowired
    private IAreaService iAreaService;
    @Autowired
    private IDictService iDictService;

    /**
     * 添加区域
     * @param cf 创建区域的表单对象
     * @return
     */
     @ApiOperation("添加区域")
     @RequiresPermissions("area:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public AreaVo create(@RequestBody @Valid AreaCreateForm cf) {
         Area area = areaWebMapper.formToPo(cf);
         // 拼音
         setPinyin(area);
         return create(area);
     }

    /**
     * 区域名称汉语转拼音
     * @param area 区域实体
     */
     private void setPinyin(Area area){
         PinyinDto pinyinDto = null;
         try {
             // 汉字转拼音
             pinyinDto = getPinyin(area.getName(),true);
         } catch (BadHanyuPinyinOutputFormatCombination e) {
             throw new RBaseException("汉语转拼音异常", e);
         }
         area.setSpell(Optional.ofNullable(pinyinDto.getFull()).orElse(area.getName()));
         area.setSpellSimple(Optional.ofNullable(pinyinDto.getSimple()).orElse(area.getName()));
         area.setSpellFirst(Optional.ofNullable(pinyinDto.getFirst()).orElse(area.getName().substring(0,1)));

     }

    /**
     * 根据区域id查询区域
     * @param id 区域id
     * @return
     */
     @ApiOperation("根据区域id查询区域")
     @RequiresPermissions("area:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public AreaVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

    /**
     * 根据区域id删除区域
     * @param id 区域id
     * @return
     */
    @ApiOperation("根据区域id删除区域")
    @RequiresPermissions("area:single:deleteById")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteById(@PathVariable String id) {
        return super.deleteById(id);
    }

    /**
     * 根据id更新区域
     * @param id 区域id
     * @param uf 更新区域的表单对象
     * @return
     */
     @ApiOperation("更新区域")
     @RequiresPermissions("area:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public AreaVo update(@PathVariable String id,@RequestBody @Valid AreaUpdateForm uf) {
         Area poQuery = areaWebMapper.formToPo(uf);
         poQuery.setId(id);
         setPinyin(poQuery);
         return update(poQuery);
     }

    /**
     * 区域分页查询，主要用于分页管理
     * @param listForm
     * @return
     */
    @ApiOperation("区域分页查询")
    @RequiresPermissions("area:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<AreaVo> listPage(AreaListPageForm listForm) {
        Area poQuery = areaWebMapper.formToPo(listForm);
        return listPage(poQuery,listForm);
     }

    /**
     * 区域懒加载树
     * @param parentId
     * @return
     */
    @ApiOperation("区域懒加载树")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "父级id,不传获取根节点",name = "parentId")
    })
    @RequiresPermissions("area:single:getByParentId")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<TreeNodeVo<AreaVo>> tree(String parentId) {
        return super.listToTreeNodeVo(super.getByParentId(parentId));
    }
}
