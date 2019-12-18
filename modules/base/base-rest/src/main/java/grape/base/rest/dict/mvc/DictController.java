package grape.base.rest.dict.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.dict.form.DictCreateForm;
import grape.base.rest.dict.form.DictEnableForm;
import grape.base.rest.dict.form.DictListPageForm;
import grape.base.rest.dict.form.DictUpdateForm;
import grape.base.rest.dict.mapper.DictWebMapper;
import grape.base.rest.dict.vo.DictVo;
import grape.base.service.comp.api.ICompService;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
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
import java.util.List;

/**
 * <p>
 * 字典,提供值与编码映射，用于下拉框或组合选择使用 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/dict")
@Api(tags = "字典相关接口")
public class DictController extends BaseTreeController<DictVo, Dict> {

    @Autowired
    private DictWebMapper dictWebMapper;
    @Autowired
    private IDictService iDictService;
    @Autowired
    private ICompService iCompService;
    /**
     * 获取字典组下的字典项
     * @param groupCode
     * @return
     */
    @ApiOperation("根据字典组编码查询字典项")
    // 注释掉，一般字段的权限都有，如果要细粒度控制建议打开并在功能权限字符串中配置
    //@RequiresPermissions("dict:items:queryItemsByGroupCode")
    @RequiresPermissions("user")
    @GetMapping("/items/{groupCode}")
    @ResponseStatus(HttpStatus.OK)
    public List<DictVo> queryItemsByGroupCode(@PathVariable String groupCode) {
        List<Dict> items = iDictService.getItemByGroupCode(groupCode, null);
        if (isEmpty(items)) {
            throw ExceptionTools.dataNotExistRE("字典组编码" + groupCode + "对应的字典项不存在");
        }
        return posToVos(items);
    }


    /**
     * 添加字典
     * @param cf
     * @return
     */
     @ApiOperation("添加字典")
     @RequiresPermissions("dict:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public DictVo create(@RequestBody @Valid DictCreateForm cf) {
         // code 唯一检查
         if (iDictService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
         }
         if (cf.getIsGroup()) {
             // 字典项下面不允许添加字典组
             if(!isStrEmpty(cf.getParentId())){
                 Dict dictParent = iDictService.getById(cf.getParentId());
                 if(!dictParent.getIsGroup()){
                     throw new RBaseException("字典组不能作为字典项的子节点");
                 }
             }
         }else {
             // 字典项只能在字典组下面
             if(isStrEmpty(cf.getParentId())){
                 throw new RBaseException("字典项只能为字典组的子节点");
             }else {
                 Dict dictParent = iDictService.getById(cf.getParentId());
                 if(!dictParent.getIsGroup()){
                     throw new RBaseException("字典项只能为字典组的子节点");
                 }
             }
         }

         Dict dict = dictWebMapper.formToPo(cf);
         dict.setIsDisabled(false);
         return super.create(dict);
     }

     @ApiOperation(value = "根据id查询字典",notes = "主要用于字典更新")
     @RequiresPermissions("dict:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public DictVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation(value = "字典删除",notes = "字典下有子节点不能删除")
     @RequiresPermissions("dict:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("字典更新")
     @RequiresPermissions("dict:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public DictVo update(@PathVariable String id,@RequestBody @Valid DictUpdateForm uf) {
         Dict dict = dictWebMapper.formToPo(uf);
         dict.setId(id);
         return super.update(dict);
     }

    @ApiOperation("分页查询字典")
    @RequiresPermissions("dict:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<DictVo> listPage(DictListPageForm listPageForm) {
        Dict dict = dictWebMapper.formToPo(listPageForm);
         return super.listPage(dict,listPageForm);
     }

    /**
     * 检查树结构是否完整
     * @return
     */
    @ApiOperation(value = "检查树结构是否完整",notes = "主要用于检查树结构的完整性")
    @RequiresPermissions("dict:single:checkTreeStruct")
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
    @RequiresPermissions("dict:single:getByParentId")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<TreeNodeVo<DictVo>> tree(String parentId) {
        List<DictVo> r = super.getByParentId(parentId);
        return super.listToTreeNodeVo(r);
    }
    /**
     * 启用或禁用
     * @param id
     * @param form
     * @return
     */
    @ApiOperation("启用或禁用")
    @RequiresPermissions("dict:single:enable")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public DictVo enable(@PathVariable String id, @RequestBody @Valid DictEnableForm form) {
        Dict dict = new Dict();
        dict.setId(id);
        dict.setIsDisabled(form.getDisabled());
        dict.setVersion(form.getVersion());
        dict.setDisabledReason(form.getDisabledReason());
        return super.update(dict);
    }
}
