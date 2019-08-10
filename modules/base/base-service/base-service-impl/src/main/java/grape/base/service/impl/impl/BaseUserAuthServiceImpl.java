package grape.base.service.impl.impl;

import grape.base.service.api.po.BaseUserAuthPo;
import grape.base.service.impl.mapper.BaseUserAuthMapper;
import grape.base.service.api.service.IBaseUserAuthService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户认证表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-08-10
 */
@Service
public class BaseUserAuthServiceImpl extends BaseServiceImpl<BaseUserAuthMapper, BaseUserAuthPo> implements IBaseUserAuthService {

}
