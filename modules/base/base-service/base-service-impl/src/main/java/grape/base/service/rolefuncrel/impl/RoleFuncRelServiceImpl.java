package grape.base.service.rolefuncrel.impl;

import grape.base.service.rolefuncrel.po.RoleFuncRel;
import grape.base.service.rolefuncrel.mapper.RoleFuncRelMapper;
import grape.base.service.rolefuncrel.api.IRoleFuncRelService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单功能关系表，多对多 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Service
public class RoleFuncRelServiceImpl extends BaseServiceImpl<RoleFuncRelMapper, RoleFuncRel> implements IRoleFuncRelService {

}
