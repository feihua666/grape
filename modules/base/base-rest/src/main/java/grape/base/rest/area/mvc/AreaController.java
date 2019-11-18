package grape.base.rest.area.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.area.form.AreaCreateForm;
import grape.base.rest.area.form.AreaListPageForm;
import grape.base.rest.area.form.AreaUpdateForm;
import grape.base.rest.area.mapper.AreaWebMapper;
import grape.base.rest.area.vo.AreaVo;
import grape.base.service.area.api.IAreaService;
import grape.base.service.area.po.Area;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.common.rest.mvc.BaseTreeController;
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
import org.springframework.beans.factory.annotation.Value;
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
public class AreaController extends BaseTreeController<AreaVo, Area> {

    @Autowired
    private AreaWebMapper areaWebMapper;
    @Autowired
    private IAreaService iAreaService;
    @Autowired
    private IDictService iDictService;
    @Value("${baidu.map.ak}")
    private String baiduMapAk;


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

     private void setPinyin(Area area){

         try {
             // 汉字转拼音
             PinyinDto pinyinDto = getPinyin(area.getName(),true);
             area.setSpell(Optional.ofNullable(pinyinDto.getFull()).orElse(area.getName()));
             area.setSpellSimple(Optional.ofNullable(pinyinDto.getSimple()).orElse(area.getName()));
             area.setSpellFirst(Optional.ofNullable(pinyinDto.getFirst()).orElse(area.getName().substring(0,1)));

         } catch (BadHanyuPinyinOutputFormatCombination e) {
             // 产生异常这里可以基本不用管
             log.error(e.getMessage(),e);
         }
     }

     @ApiOperation("根据id查询区域")
     @RequiresPermissions("area:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public AreaVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

    @ApiOperation("删除区域")
    @RequiresPermissions("area:single:deleteById")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteById(@PathVariable String id) {
        return super.deleteById(id);
    }

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

    @ApiOperation("区域分页查询")
    @RequiresPermissions("area:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<AreaVo> listPage(AreaListPageForm listForm) {
        Area poQuery = areaWebMapper.formToPo(listForm);
        return listPage(poQuery,listForm);
     }

    @ApiOperation("区域树")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "父级id,不传获取根节点",name = "parentId")
    })
    @RequiresPermissions("area:single:getByParentId")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<TreeNodeVo<AreaVo>> tree(String parentId) {
        return super.listToTreeNodeVo(super.getByParentId(parentId));
    }

    @Override
    public AreaVo transVo(AreaVo vo) {
        Dict dict = iDictService.getById(vo.getTypeDictId());
        vo.setTypeDictCode(Optional.ofNullable(dict).map(d -> d.getCode()).orElse(null));
        vo.setTypeDictName(Optional.ofNullable(dict).map(d -> d.getName()).orElse(null));

        Area area = getService().getById(vo.getParentId());
        vo.setParentName(Optional.ofNullable(area).map(d -> d.getName()).orElse(null));

        return vo;
    }
}
