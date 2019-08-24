package grape.base.service.area.impl;

import grape.base.service.area.po.AreaPo;
import grape.base.service.area.mapper.AreaMapper;
import grape.base.service.area.api.IAreaService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 区域表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-08-24
 */
@Service
public class AreaServiceImpl extends BaseServiceImpl<AreaMapper, AreaPo> implements IAreaService {

}
