package grape.base.rest.log.mapper;

import grape.base.service.log.po.Log;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.log.form.LogCreateForm;
import grape.base.rest.log.form.LogUpdateForm;
import grape.base.rest.log.form.LogListPageForm;
import grape.base.rest.log.vo.LogVo;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 系统日志 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-11-06
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogWebMapper extends WebMapper<LogVo, Log> {

    Log formToPo(LogCreateForm f);
    Log formToPo(LogUpdateForm f);
    Log formToPo(LogListPageForm f);
}
