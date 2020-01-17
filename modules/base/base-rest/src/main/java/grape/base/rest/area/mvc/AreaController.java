package grape.base.rest.area.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.area.form.AreaCreateForm;
import grape.base.rest.area.form.AreaListPageForm;
import grape.base.rest.area.form.AreaUpdateForm;
import grape.base.rest.area.mapper.AreaWebMapper;
import grape.base.rest.area.vo.AreaVo;
import grape.common.service.loginuser.LoginUser;
import grape.base.service.area.api.IAreaService;
import grape.base.service.area.po.Area;
import grape.base.service.dict.api.IDictService;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseTreeLoginUserController;
import grape.common.rest.vo.TreeNodeVo;
import grape.common.service.common.dataconstraint.DefaultDataObject;
import grape.common.service.common.dataconstraint.IDataObject;
import grape.common.tools.PinyinDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class AreaController extends BaseTreeLoginUserController<AreaVo, Area, LoginUser> {

    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject("dataObjectCodeArea");


    @Autowired
    private AreaWebMapper areaWebMapper;
    @Autowired
    private IAreaService iAreaService;
    @Autowired
    private IDictService iDictService;

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
     * 添加区域
     * @param cf 创建区域的表单对象
     * @return
     */
     @ApiOperation("添加区域")
     @PreAuthorize("hasAuthority('area:single:create')")
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
     @PreAuthorize("hasAuthority('area:single:queryById')")
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
    @PreAuthorize("hasAuthority('area:single:deleteById')")
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
     @PreAuthorize("hasAuthority('area:single:updateById')")
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
    @PreAuthorize("hasAuthority('area:single:listPage')")
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
    @PreAuthorize("hasAuthority('area:single:getByParentId')")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<TreeNodeVo<AreaVo>> tree(String parentId) {
        return super.listToTreeNodeVo(super.getByParentId(parentId));
    }
}
