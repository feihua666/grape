package grape.common.service.common;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import grape.common.service.po.IDBasePo;
import grape.common.tools.ToolService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 所有service接口的父接口，以实现通用功能
 * Created by yangwei
 * Created at 2019/7/23 14:30
 */
public interface IBaseService<Po extends IDBasePo<?,?>> extends IService<Po>, ToolService {

    /**
     * 保存实体，操作同com.baomidou.mybatisplus.extension.service.IService#save(java.lang.Object)
     * 之所以加这个是兼容rpc的远程调用，controller调用service时建议使用该方法插入数据，以获取插入后的实体数据id
     *
     * @param entity
     * @return 返回插入后的实体
     */
    Po create(Po entity);

    default List<String> convertIds(List<Po> pos){
        if (!isListEmpty(pos)) {
            return pos.stream().map((p) -> p.getId().toString()).collect(Collectors.toList());
        }
        return null;
    }

    default IPage<Po> page(IPage<Po> page, Po query){
        return page(page, Wrappers.query(query));
    }

    default List<Po> list(Po query){
        return list(Wrappers.query(query));
    }
}
