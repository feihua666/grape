package grape.common.service.common;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import grape.common.service.po.IDBasePo;
import grape.common.tools.ToolService;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 全局service实现类的基类
 * Created by yangwei
 * Created at 2019/7/23 14:32
 */
@Slf4j
public class BaseServiceImpl<Mapper extends IBaseMapper<Po>,Po extends IDBasePo<String,Po>> extends ServiceImpl<Mapper,Po> implements IBaseService<Po> , ToolService {
    @Override
    public Po create(Po entity) {
        boolean r = super.save(entity);
        if (r) {
            return entity;
        }

        return null;
    }

    @Override
    public Po getById(Serializable id) {
        if (id == null) {
            return null;
        }
        return super.getById(id);
    }
}
