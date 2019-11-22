package grape.base.service.job.impl;

import grape.base.service.job.po.Job;
import grape.base.service.job.mapper.JobMapper;
import grape.base.service.job.api.IJobService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 职务表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-10-29
 */
@Service
public class JobServiceImpl extends BaseServiceImpl<JobMapper, Job> implements IJobService {

}
