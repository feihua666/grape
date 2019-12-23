package grape.base.rest.workcalendar.vo;

import grape.common.tools.calendar.CalendarInfo;
import grape.common.tools.calendar.CalendarUtils;
import grape.common.tools.calendar.LunarCalendarUtils;
import grape.common.tools.calendar.LunarDate;
import lombok.Data;

import java.util.Date;

/**
 * Created by yangwei
 * Created at 2018/9/20 17:58
 */
@Data
public class CalendarVo {

    private static final String shu = "属";
    private static final String nian = "年";
    private static final String yue = "月";
    private static final String ri = "日";
    private static final String shi = "时";

    public CalendarVo(){}
    public CalendarVo(CalendarInfo calendarDto){
        this.date = calendarDto.getDate();
        this.year = calendarDto.getYear();
        this.month = calendarDto.getMonth();
        this.day = calendarDto.getDay();
        this.hour = calendarDto.getHour();
        this.minute = calendarDto.getMinute();
        this.second = calendarDto.getSecond();
        this.week = calendarDto.getWeek();
        this.lunarDate = calendarDto.getLunarDate();
        this.lunarDateStr = LunarCalendarUtils.lunarDateToString(lunarDate, CalendarUtils.DateStyle.YYYY_MM_DD_HH_MM_SS_CN);
        this.lunarYear = calendarDto.getLunarYear();
        this.lunarMonth = calendarDto.getLunarMonth();
        this.lunarMonthStr = LunarCalendarUtils.ChineseNumber.getByNumber(lunarMonth).getMonthName();
        this.lunarDay = calendarDto.getLunarDay();
        this.lunarDayStr = LunarCalendarUtils.ChinaBaseTen.getByDay(lunarDay).getChineseName() + LunarCalendarUtils.ChineseNumber.getByDay(lunarDay).getChineseName();
        this.lunarHour = calendarDto.getLunarHour();
        this.lunarMinute = calendarDto.getLunarMinute();
        this.lunarSecond = calendarDto.getLunarSecond();
        this.leap = calendarDto.isLeap();
        this.leapMonth = calendarDto.getLeapMonth();
        this.currentLeap = calendarDto.isCurrentLeap();

        this.weekOfYear = CalendarUtils.getWeekOfYear(calendarDto.getDate());
        this.dayOfYear = CalendarUtils.getDayOfYear(calendarDto.getDate());

        this.animal = shu + calendarDto.getAnimal().getChineseName();
        this.ganzhiYear = calendarDto.getTianganYear().getChineseName() + calendarDto.getDizhiYear().getChineseName() + nian;
        this.ganzhiMonth = calendarDto.getTianganMonth().getChineseName() + calendarDto.getDizhiMonth().getChineseName() + yue;
        this.ganzhiDay = calendarDto.getTianganDay().getChineseName() + calendarDto.getDizhiDay().getChineseName() + ri;
        this.ganzhiHour = calendarDto.getTianganHour().getChineseName() + calendarDto.getDizhiHour().getChineseName() + shi;
        if (calendarDto.getSolarTerm24() != null) {
            this.solarTerm24 = calendarDto.getSolarTerm24().getChineseName() ;
        }
        if (calendarDto.getConstellation() != null) {
            this.constellation = calendarDto.getConstellation().getChineseName();
        }
        if (calendarDto.getWeekSolarHoliday() != null) {
            this.weekSolarHoliday = calendarDto.getWeekSolarHoliday().getChineseName();
        }
        if (calendarDto.getSolarHoliday() != null) {
            this.solarHoliday = calendarDto.getSolarHoliday().getChineseName();
        }
        if (calendarDto.getTraditionalFestival() != null) {
            this.traditionalFestival = calendarDto.getTraditionalFestival().getChineseName();
        }
        if (calendarDto.getChinaConstellation() != null) {
            this.chinaConstellation = calendarDto.getChinaConstellation().getChineseName();
        }
    }
    private Date date;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private int week;
    private LunarDate lunarDate;
    private String lunarDateStr;
    private int lunarYear;
    private int lunarMonth;
    private String lunarMonthStr;
    private int lunarDay;
    private String lunarDayStr;
    private int lunarHour;
    private int lunarMinute;
    private int lunarSecond;
    /**
     * 该年是否闰月
     */
    private boolean leap;
    /**
     * 如果闰月，闰的是几月
     */
    private int leapMonth;
    /**
     * 当前日期是否在闰月中
     */
    private boolean currentLeap;
    private int weekOfYear;
    private int dayOfYear;
    private String animal;
    private String ganzhiYear;
    private String ganzhiMonth;
    private String ganzhiDay;
    private String ganzhiHour;
    private String solarTerm24;
    private String constellation;
    private String weekSolarHoliday;
    private String solarHoliday;
    private String traditionalFestival;
    private String chinaConstellation;

}
