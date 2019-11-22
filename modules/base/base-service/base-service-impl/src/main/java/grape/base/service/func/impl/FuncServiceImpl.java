package grape.base.service.func.impl;

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
        return getBaseMapper().getByRoleId(roleId,null,isDisabled);
    }

    @Override
    public List<Func> getMenuAndPageByRoleId(String roleId,Boolean isDisabled) {
        List<String> menuAndPage = new ArrayList<>(2);
        menuAndPage.add(Func.TypeDictItem.menu.name());
        menuAndPage.add(Func.TypeDictItem.page.name());
        List<Dict> itemType = iDictService.getItemByGroupCode(Func.TypeDictGroup.func_type.name(), null,menuAndPage);
        return getBaseMapper().getByRoleId(roleId,iDictService.convertIds(itemType),isDisabled);
    }

    @Override
    public List<Func> getByTypes(List<String> typeCode, Boolean isDisabled) {
        List<Dict> itemType = iDictService.getItemByGroupCode(Func.TypeDictGroup.func_type.name(), null,typeCode);
        return getBaseMapper().getByType(iDictService.convertIds(itemType),isDisabled);
    }
}
