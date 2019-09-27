package grape.base.service.func.impl;

import grape.base.service.func.po.Func;
import grape.base.service.func.mapper.FuncMapper;
import grape.base.service.func.api.IFuncService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Func> getByRoleId(Long roleId) {
        return getBaseMapper().getByRoleId(roleId);
    }
}
