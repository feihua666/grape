package grape.base.service.workcalendar.impl;

import grape.base.service.workcalendar.po.WorkCalendar;
import grape.base.service.workcalendar.mapper.WorkCalendarMapper;
import grape.base.service.workcalendar.api.IWorkCalendarService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 工作日历表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-23
 */
@Service
public class WorkCalendarServiceImpl extends BaseServiceImpl<WorkCalendarMapper, WorkCalendar> implements IWorkCalendarService {

}
