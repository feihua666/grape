package grape.base.rest.workcalendar.mapper;

import grape.base.service.workcalendar.po.WorkCalendar;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.workcalendar.form.WorkCalendarCreateForm;
import grape.base.rest.workcalendar.form.WorkCalendarUpdateForm;
import grape.base.rest.workcalendar.form.WorkCalendarListPageForm;
import grape.base.rest.workcalendar.vo.WorkCalendarVo;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 工作日历表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-12-23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkCalendarWebMapper extends WebMapper<WorkCalendarVo, WorkCalendar> {

    WorkCalendar formToPo(WorkCalendarCreateForm f);
    WorkCalendar formToPo(WorkCalendarUpdateForm f);
    WorkCalendar formToPo(WorkCalendarListPageForm f);
}
