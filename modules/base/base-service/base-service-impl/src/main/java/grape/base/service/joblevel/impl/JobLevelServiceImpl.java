package grape.base.service.joblevel.impl;

import grape.base.service.joblevel.po.JobLevel;
import grape.base.service.joblevel.mapper.JobLevelMapper;
import grape.base.service.joblevel.api.IJobLevelService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 职务级别表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-10-31
 */
@Service
public class JobLevelServiceImpl extends BaseServiceImpl<JobLevelMapper, JobLevel> implements IJobLevelService {

}
