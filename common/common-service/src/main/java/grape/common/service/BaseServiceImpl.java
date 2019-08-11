package grape.common.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import grape.common.service.po.NormalBasePo;
import grape.common.tools.ToolService;

/**
 * 全局service实现类的基类
 * Created by yangwei
 * Created at 2019/7/23 14:32
 */
public class BaseServiceImpl<Mapper extends IBaseMapper<Po>,Po extends NormalBasePo> extends ServiceImpl<Mapper,Po> implements IBaseService<Po> , ToolService {
    @Override
    public Po create(Po entity) {
        boolean r = super.save(entity);
        if (r) {
            return entity;
        }
        return null;
    }
}
