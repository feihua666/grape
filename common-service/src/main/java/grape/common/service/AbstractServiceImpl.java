package grape.common.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import grape.common.service.po.NormalBasePo;

/**
 * Created by yangwei
 * Created at 2019/7/23 14:32
 */
public class AbstractServiceImpl<Mapper extends BaseMapper,Po extends NormalBasePo> implements IService<Po> {
}
