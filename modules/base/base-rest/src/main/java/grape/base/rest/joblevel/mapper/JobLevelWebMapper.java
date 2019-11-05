package grape.base.rest.joblevel.mapper;

import grape.base.service.joblevel.po.JobLevel;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.joblevel.form.JobLevelCreateForm;
import grape.base.rest.joblevel.form.JobLevelUpdateForm;
import grape.base.rest.joblevel.form.JobLevelListPageForm;
import grape.base.rest.joblevel.vo.JobLevelVo;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 职务级别表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-10-31
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobLevelWebMapper extends WebMapper<JobLevelVo, JobLevel> {

    JobLevel formToPo(JobLevelCreateForm f);
    JobLevel formToPo(JobLevelUpdateForm f);
    JobLevel formToPo(JobLevelListPageForm f);
}
