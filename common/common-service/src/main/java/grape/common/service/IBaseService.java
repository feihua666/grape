package grape.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import grape.common.service.po.IDBasePo;
import grape.common.service.po.NormalBasePo;
import grape.common.tools.ToolService;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有service接口的父接口，以实现通用功能
 * Created by yangwei
 * Created at 2019/7/23 14:30
 */
public interface IBaseService<Po extends IDBasePo<Long,Po>> extends IService<Po>, ToolService {

    /**
     * 保存实体，操作同com.baomidou.mybatisplus.extension.service.IService#save(java.lang.Object)
     * 之所以加这个是兼容rpc的远程调用，controller调用service时建议使用该方法插入数据，以获取插入后的实体数据id
     *
     * @param entity
     * @return 返回插入后的实体
     */
    Po create(Po entity);

    default List<Long> convertIds(List<Po> pos){
        if (!isListEmpty(pos)) {
            List<Long> ids = new ArrayList<>(pos.size());
            for (Po po : pos) {
                ids.add(po.getId());
            }
        }
        return null;
    }
}
