package grape.base.service.log.impl;

import grape.base.service.log.po.Log;
import grape.base.service.log.mapper.LogMapper;
import grape.base.service.log.api.ILogService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-11-06
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<LogMapper, Log> implements ILogService {

}
