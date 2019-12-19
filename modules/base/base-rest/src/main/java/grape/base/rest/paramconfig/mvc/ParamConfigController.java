package grape.base.rest.paramconfig.mvc;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.ConvertException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.paramconfig.form.ParamConfigCreateForm;
import grape.base.rest.paramconfig.form.ParamConfigEnableForm;
import grape.base.rest.paramconfig.form.ParamConfigListPageForm;
import grape.base.rest.paramconfig.form.ParamConfigUpdateForm;
import grape.base.rest.paramconfig.mapper.ParamConfigWebMapper;
import grape.base.rest.paramconfig.vo.ParamConfigVo;
import grape.base.service.BaseLoginUser;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.paramconfig.api.IParamConfigService;
import grape.base.service.paramconfig.po.ParamConfig;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseController;
import grape.common.rest.mvc.BaseLoginUserController;
import grape.common.service.common.DefaultDataObject;
import grape.common.service.common.IDataObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * <p>
 * 参数配置表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-11-05
 */
@RestController
@RequestMapping("/paramconfig")
@Api(tags = "参数配置相关接口")
public class ParamConfigController extends BaseLoginUserController<ParamConfigVo, ParamConfig, BaseLoginUser> {
    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject("dataObjectCodeParamConfig");

    @Autowired
    private ParamConfigWebMapper currentWebMapper;
    @Autowired
    private IParamConfigService currentService;
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

     @ApiOperation("添加参数配置")
     @RequiresPermissions("paramConfig:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public ParamConfigVo create(@RequestBody @Valid ParamConfigCreateForm cf) {
         // code 唯一检查
         if (currentService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
         }
         // 检查类型和值是否正确

         checkTypeAndValueMatch(cf.getValue(), cf.getValueTypeDictId());

         ParamConfig po = currentWebMapper.formToPo(cf);
         po.setIsDisabled(false);
         return super.create(po);
     }

    /**
     * 检查值和类型是否匹配
     * @param value
     * @param valueTypeDictId
     * @return
     */
    private void checkTypeAndValueMatch(String value,String valueTypeDictId){
        Dict dict = iDictService.getById(valueTypeDictId);
        try {
            Convert.convertByClassName(dict.getCode(), value);
        } catch (ConvertException e) {
            throw new RBaseException("类型与值不匹配，请检查填写是否正确");
        }
    }
     @ApiOperation("根据ID查询参数配置")
     @RequiresPermissions("paramConfig:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public ParamConfigVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除参数配置")
     @RequiresPermissions("paramConfig:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("更新参数配置")
     @RequiresPermissions("paramConfig:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public ParamConfigVo update(@PathVariable String id,@RequestBody @Valid ParamConfigUpdateForm uf) {

         checkTypeAndValueMatch(uf.getValue(), uf.getValueTypeDictId());

         ParamConfig po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("分页查询参数配置")
    @RequiresPermissions("paramConfig:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<ParamConfigVo> listPage(ParamConfigListPageForm listPageForm) {
         ParamConfig po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }
    /**
     * 启用或禁用
     * @param id
     * @param form
     * @return
     */
    @ApiOperation("启用或禁用")
    @RequiresPermissions("paramConfig:single:enable")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ParamConfigVo enable(@PathVariable String id, @RequestBody @Valid ParamConfigEnableForm form) {
        ParamConfig paramConfig = new ParamConfig();
        paramConfig.setId(id);
        paramConfig.setIsDisabled(form.getDisabled());
        paramConfig.setVersion(form.getVersion());
        paramConfig.setDisabledReason(form.getDisabledReason());
        return super.update(paramConfig);
    }
}
