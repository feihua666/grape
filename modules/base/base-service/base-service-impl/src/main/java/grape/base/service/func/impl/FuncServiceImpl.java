package grape.base.service.func.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.func.po.Func;
import grape.base.service.func.mapper.FuncMapper;
import grape.base.service.func.api.IFuncService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单功能表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Service
public class FuncServiceImpl extends BaseServiceImpl<FuncMapper, Func> implements IFuncService {

    @Autowired
    private IDictService iDictService;

    @Override
    public List<Func> getByRoleId(String roleId,Boolean isDisabled) {
        return getRoleId(roleId,null,null,isDisabled);
    }

    @Override
    public List<Func> getRoleId(String roleId,List<Func.TypeDictItem> typeCode,String applicationId,Boolean isDisabled) {
        assertParamNotEmpty(roleId,"roleId不能为空");
        List<Dict> itemType = iDictService.getItemByGroupCode(Func.TypeDictGroup.func_type.name(), null,typeCode.stream().map(item->item.name()).collect(Collectors.toList()));
        return getBaseMapper().getByRoleId(roleId,iDictService.convertIds(itemType),applicationId,isDisabled);
    }

    @Override
    public List<Func> getByTypes(List<Func.TypeDictItem> typeCode, Boolean isDisabled) {
        return getByTypes(typeCode,null,isDisabled);
    }

    @Override
    public List<Func> getByTypes(List<Func.TypeDictItem> typeCode, String applicationId, Boolean isDisabled) {
        assertListNotEmpty(typeCode,"typeCode 不能为空");
        List<Dict> itemType = iDictService.getItemByGroupCode(Func.TypeDictGroup.func_type.name(), null,typeCode.stream().map(item->item.name()).collect(Collectors.toList()));
        return list(Wrappers.<Func>lambdaQuery()
                .in(Func::getTypeDictId, iDictService.convertIds(itemType))
                .eq(isDisabled != null, Func::getIsDisabled, isDisabled)
                .eq(!isStrEmpty(applicationId),Func::getApplicationId,applicationId));
    }
}
