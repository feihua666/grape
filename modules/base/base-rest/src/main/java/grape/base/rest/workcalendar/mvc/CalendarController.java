package grape.base.rest.workcalendar.mvc;

import grape.base.rest.workcalendar.vo.CalendarVo;
import grape.common.rest.mvc.SuperController;
import grape.common.tools.calendar.CalendarInfo;
import grape.common.tools.calendar.CalendarInfoUtils;
import grape.common.tools.calendar.CalendarUtils;
import grape.common.tools.calendar.LunarCalendarUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yangwei
 * Created at 2019/12/23 16:24
 */
@RestController
@RequestMapping("/calendar")
@Api(tags = "日历相关接口")
public class CalendarController extends SuperController {

    /**
     * 单资源，获取日期详情
     * @param date 公历日期 严格来讲，yyyy-MM-dd
     * @return
     */
    @GetMapping("/{date}")
    public CalendarVo getCalendarByDate(@PathVariable String date){
        CalendarInfo calendarDto = CalendarInfoUtils.getCalendarByDate(CalendarUtils.stringToDate(date));
        return new CalendarVo(calendarDto);
    }
    /**
     * 单资源，获取日期详情
     * @param lunarDate 农历日期 严格来讲，yyyy-MM-dd
     * @return
     */
    @RequestMapping(value = "/lunar/{lunarDate}",method = RequestMethod.GET)
    public CalendarVo getCalendarByLunarDate(@PathVariable String lunarDate){
        CalendarInfo calendarDto = CalendarInfoUtils.getCalendarByLunarDate(LunarCalendarUtils.stringTolunarDate(lunarDate));
        return new CalendarVo(calendarDto);
    }

    /**
     * 获取一整月的数据
     * @param monthDate 公历日期 严格来讲，yyyy-MM-dd
     * @return
     */
    @RequestMapping(value = "/dates/{monthDate}",method = RequestMethod.GET)
    public List<CalendarVo> getCalendarsByMonthDate(@PathVariable String monthDate){
        Date _monthDate = CalendarUtils.stringToDate(monthDate);
        Date start = CalendarUtils.getFirstDayOfMonth(_monthDate);
        Date end = CalendarUtils.getEndDayOfMonth(_monthDate);
        List<CalendarInfo> calendarsByDate = CalendarInfoUtils.getCalendarsByDate(start, end);

        return calendarsByDate.stream().map(i -> new CalendarVo(i)).collect(Collectors.toList());

    }

    /**
     * 获取一整月的数据
     * @param lunarMonthDate 农历日期 严格来讲，yyyy-MM-dd
     * @return
     */
    @RequestMapping(value = "/dates/lunar/{lunarMonthDate}",method = RequestMethod.GET)
    public List<CalendarVo>  getCalendarsByLunarMonthDate(@PathVariable String lunarMonthDate){
        Date monthDate = LunarCalendarUtils.LunarToCalendar(LunarCalendarUtils.stringTolunarDate(lunarMonthDate));

        return getCalendarsByMonthDate(CalendarUtils.dateToString(monthDate));
    }
}
