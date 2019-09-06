package grape.base.service.area.impl;

import grape.base.service.area.po.Area;
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
 * @since 2019-09-06
 */
@Service
public class AreaServiceImpl extends BaseServiceImpl<AreaMapper, Area> implements IAreaService {

}
