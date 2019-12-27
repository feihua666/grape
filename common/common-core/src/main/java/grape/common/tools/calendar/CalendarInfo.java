package grape.common.tools.calendar;

import grape.common.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by yangwei
 * Created at 2018/9/20 15:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CalendarInfo extends BasePojo {
    private Date date;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    /**
     * 星期几，星期日为0
     */
    private int week;
    private LunarDate lunarDate;
    private int lunarYear;
    private int lunarMonth;
    private int lunarDay;
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
    private LunarCalendarUtils.SymbolicAnimals animal;
    private LunarCalendarUtils.Tiangan tianganYear;
    private LunarCalendarUtils.Dizhi dizhiYear;

    private LunarCalendarUtils.Tiangan tianganMonth;
    private LunarCalendarUtils.Dizhi dizhiMonth;

    private LunarCalendarUtils.Tiangan tianganDay;
    private LunarCalendarUtils.Dizhi dizhiDay;

    private LunarCalendarUtils.Tiangan tianganHour;
    private LunarCalendarUtils.Dizhi dizhiHour;

    private LunarCalendarUtils.SolarTerm24 solarTerm24;

    private LunarCalendarUtils.Constellation constellation;

    private LunarCalendarUtils.WeekSolarHoliday weekSolarHoliday;

    private LunarCalendarUtils.SolarHoliday solarHoliday;

    private  LunarCalendarUtils.TraditionalFestival traditionalFestival;

    private LunarCalendarUtils.ChinaConstellation chinaConstellation;

}
