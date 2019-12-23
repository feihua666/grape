package grape.common.tools.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangwei
 * Created at 2019/12/23 13:38
 */
public class CalendarInfoUtils {


    /**
     * 根据公历日期获取详细内容
     * @param date 公历日期
     * @return
     */
    public static CalendarInfo getCalendarByDate(Date date) {
        CalendarInfo calendarDto = new CalendarInfo();

        calendarDto.setDate(date);
        int year = CalendarUtils.getYear(date);
        int month = CalendarUtils.getMonth(date);
        int day = CalendarUtils.getDay(date);
        int hour = CalendarUtils.getHour(date);
        int minute = CalendarUtils.getMinute(date);
        int second = CalendarUtils.getMinute(date);
        calendarDto.setYear(year);
        calendarDto.setMonth(month);
        calendarDto.setDay(day);
        calendarDto.setHour(hour);
        calendarDto.setMinute(minute);
        calendarDto.setSecond(second);
        calendarDto.setWeek(CalendarUtils.getDayOfWeek(date) - 1);
        //转农历
        LunarDate lunarDate = LunarCalendarUtils.CalendarToLunar(date);
        int lunarYear = lunarDate.getYear();
        int lunarMonth = lunarDate.getMonth();
        int lunarDay = lunarDate.getDay();

        int lunarHour = CalendarUtils.getHour(date);
        int lunarMinute = CalendarUtils.getMinute(date);
        int lunarSecond = CalendarUtils.getMinute(date);

        calendarDto.setLunarYear(lunarYear);
        calendarDto.setLunarMonth(lunarMonth);
        calendarDto.setLunarDay(lunarDay);
        calendarDto.setLunarHour(lunarHour);
        calendarDto.setLunarMinute(lunarMinute);
        calendarDto.setLunarSecond(lunarSecond);

        calendarDto.setLunarDate(lunarDate);
        //闰月
        int leapMonth = LunarCalendarUtils.leapMonth(year);

        if (leapMonth == 0) {
            calendarDto.setLeap(false);
        }else{
            calendarDto.setLeap(true);
            calendarDto.setLeapMonth(leapMonth);

            if(lunarMonth == leapMonth){
                // 农历转公历
                Date todate = LunarCalendarUtils.LunarToCalendar(lunarDate);
                // 如果转公历日期与原日期相等，则日期是闰月的第一个月
                if(todate.equals(date)){
                    calendarDto.setCurrentLeap(false);
                }else {
                    calendarDto.setCurrentLeap(true);
                }
            }

        }
        // 动物年
        LunarCalendarUtils.SymbolicAnimals animals = LunarCalendarUtils.symbolicAnimalsYear(lunarYear);
        calendarDto.setAnimal(animals);

        // 天干地支年
        LunarCalendarUtils.Tiangan tianganYear = LunarCalendarUtils.tianganYear(lunarYear);
        LunarCalendarUtils.Dizhi dizhiYear = LunarCalendarUtils.dizhiYear(lunarYear);
        calendarDto.setTianganYear(tianganYear);
        calendarDto.setDizhiYear(dizhiYear);

        // 天干地支月
        LunarCalendarUtils.Tiangan tianganMonth = LunarCalendarUtils.tianganMonth(lunarYear,lunarMonth);
        LunarCalendarUtils.Dizhi dizhiMonth = LunarCalendarUtils.dizhiMonth(lunarMonth);
        calendarDto.setTianganMonth(tianganMonth);
        calendarDto.setDizhiMonth(dizhiMonth);

        // 天干地支日
        LunarCalendarUtils.Tiangan tianganDay = LunarCalendarUtils.tianganDay(lunarYear,lunarMonth,lunarDay);
        LunarCalendarUtils.Dizhi dizhiDay = LunarCalendarUtils.dizhiDay(lunarYear,lunarMonth,lunarDay);
        calendarDto.setTianganDay(tianganDay);
        calendarDto.setDizhiDay(dizhiDay);

        // 天干地支时辰
        LunarCalendarUtils.Tiangan tianganHour = LunarCalendarUtils.tianganHour(lunarYear,lunarMonth,lunarDay,lunarHour,lunarMinute);
        LunarCalendarUtils.Dizhi dizhiHour = LunarCalendarUtils.dizhiHour(lunarHour,lunarMinute);
        calendarDto.setTianganHour(tianganHour);
        calendarDto.setDizhiHour(dizhiHour);

        // 24节气
        LunarCalendarUtils.SolarTerm24 solarTerm24 = LunarCalendarUtils.getSolarTerm24(date);
        calendarDto.setSolarTerm24(solarTerm24);

        // 星座
        LunarCalendarUtils.Constellation constellation = LunarCalendarUtils.getConstellation(date);
        calendarDto.setConstellation(constellation);

        //某月第几周星期几节日
        LunarCalendarUtils.WeekSolarHoliday weekSolarHoliday = (LunarCalendarUtils.getWeekHoliday(date));
        calendarDto.setWeekSolarHoliday(weekSolarHoliday);

        //公历节日
        LunarCalendarUtils.SolarHoliday solarHoliday = LunarCalendarUtils.getSolarHoliday(date);
        calendarDto.setSolarHoliday(solarHoliday);

        // 农历节日
        LunarCalendarUtils.TraditionalFestival traditionalFestival = LunarCalendarUtils.getTraditionalFestival(lunarYear,lunarMonth,lunarDay);
        calendarDto.setTraditionalFestival(traditionalFestival);

        // 二十八星宿
        LunarCalendarUtils.ChinaConstellation chinaConstellation = LunarCalendarUtils.getChinaConstellation(lunarMonth,lunarDay);
        calendarDto.setChinaConstellation(chinaConstellation);
        return calendarDto;
    }

    /**
     * 根据农历日期获取详细内容
     * 如果给定的农历日期是闰月的话，则得到的公历日期是闰月第一个月对应的日期
     * @param lunarDate 农历日期
     * @return
     */
    public static CalendarInfo getCalendarByLunarDate(LunarDate lunarDate) {
        return getCalendarByDate(LunarCalendarUtils.LunarToCalendar(lunarDate));
    }

    /**
     * 根据公历日期获取一段时间的连续详细内容
     * @param start 公历开始日期
     * @param end 公历结束日期
     * @return 包括开始日期和结束日期,如果开始日期大于结束日期，返回null
     */
    public static List<CalendarInfo> getCalendarsByDate(Date start, Date end) {
        List<CalendarInfo> list = null;
        if(start.compareTo(end) <= 0){
            list = new ArrayList<>();
            list.add(getCalendarByDate(start));
            int intevalDays = CalendarUtils.getIntervalDays(end,start);

            for (int i = 0; i < intevalDays; i++) {
                Date _temp = CalendarUtils.addDay(start,i + 1);
                list.add(getCalendarByDate(_temp));
            }
        }
        return list;
    }

    /**
     * 根据农历日期获取一段时间的连接详细内容
     * 如果给定的农历日期是闰月的话，则得到的公历日期是闰月第一个月对应的日期
     * @param lunarStart 农历开始日期
     * @param lunarEnd 农历结束日期
     * @return 包括开始日期和结束日期,如果开始日期大于结束日期，返回null
     */
    public static List<CalendarInfo> getCalendarsByLunarDate(LunarDate lunarStart, LunarDate lunarEnd) {
        return getCalendarsByDate(LunarCalendarUtils.LunarToCalendar(lunarStart),LunarCalendarUtils.LunarToCalendar(lunarEnd));
    }
}
