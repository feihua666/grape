package grape.base.rest.job.mapper;

import grape.base.rest.job.form.JobListForm;
import grape.base.service.job.po.Job;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.job.form.JobCreateForm;
import grape.base.rest.job.form.JobUpdateForm;
import grape.base.rest.job.form.JobListPageForm;
import grape.base.rest.job.vo.JobVo;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 职务表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-10-29
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobWebMapper extends WebMapper<JobVo, Job> {

    Job formToPo(JobCreateForm f);
    Job formToPo(JobUpdateForm f);
    Job formToPo(JobListPageForm f);
    Job formToPo(JobListForm f);
}
