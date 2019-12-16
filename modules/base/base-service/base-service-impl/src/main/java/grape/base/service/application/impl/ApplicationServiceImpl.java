package grape.base.service.application.impl;

import grape.base.service.application.po.Application;
import grape.base.service.application.mapper.ApplicationMapper;
import grape.base.service.application.api.IApplicationService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-13
 */
@Service
public class ApplicationServiceImpl extends BaseServiceImpl<ApplicationMapper, Application> implements IApplicationService {

}
