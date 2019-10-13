package com.crud.cloud.evanliu2968.util;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
import cn.hutool.core.date.Week;
import com.crud.cloud.evanliu2968.constant.DateStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtil {

    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    public static final String TIME_START_SUFFIX = " 00:00:00.000";
    public static final String TIME_End_SUFFIX = " 23:59:59.999";
    public static final String MILLI_TIME_START_SUFFIX = ".000";
    public static final String MILLI_TIME_End_SUFFIX = ".999";

    public static final String DATE_START_TIME = "yyyy-MM-dd 00:00:00.000";
    public static final String DATE_END_TIME = "yyyy-MM-dd 23:59:59.999";
    public static final String DATE_TIME_START_TIME = "yyyy-MM-dd HH:mm:ss.000";
    public static final String DATE_TIME_END_TIME = "yyyy-MM-dd HH:mm:ss.999";

    public static DateTimeFormatter YYYY_MM_DDFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter YYYY_MM_DD_HH_MMFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static DateTimeFormatter YYYY_MM_DD_HH_MM_SSFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter YYYYMM_DD_HH_MM_BACKSLASH = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /**
     * 获取SimpleDateFormat
     *
     * @param parttern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
        return new SimpleDateFormat(parttern);
    }

    /**
     * 获取日期中的某数值。如获取月份
     *
     * @param date     日期
     * @param dateType 日期格式
     * @return 数值
     */
    private static int getInteger(Date date, int dateType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(dateType);
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期字符串
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期字符串
     */
    private static String addInteger(String date, int dateType, int amount) {
        String dateString = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = StringToDate(date, dateStyle);
            myDate = addInteger(myDate, dateType, amount);
            dateString = DateToString(myDate, dateStyle);
        }
        return dateString;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期
     */
    public static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * 获取精确的日期
     *
     * @param timestamps 时间long集合
     * @return 日期
     */
    private static Date getAccurateDate(List<Long> timestamps) {
        Date date = null;
        long timestamp = 0;
        Map<Long, long[]> map = new HashMap<Long, long[]>();
        List<Long> absoluteValues = new ArrayList<Long>();

        if (timestamps != null && timestamps.size() > 0) {
            if (timestamps.size() > 1) {
                for (int i = 0; i < timestamps.size(); i++) {
                    for (int j = i + 1; j < timestamps.size(); j++) {
                        long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
                        absoluteValues.add(absoluteValue);
                        long[] timestampTmp = {timestamps.get(i), timestamps.get(j)};
                        map.put(absoluteValue, timestampTmp);
                    }
                }

                // 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的
                long minAbsoluteValue = -1;
                if (!absoluteValues.isEmpty()) {
                    // 如果timestamps的size为2，这是差值只有一个，因此要给默认值
                    minAbsoluteValue = absoluteValues.get(0);
                }
                for (int i = 0; i < absoluteValues.size(); i++) {
                    for (int j = i + 1; j < absoluteValues.size(); j++) {
                        if (absoluteValues.get(i) > absoluteValues.get(j)) {
                            minAbsoluteValue = absoluteValues.get(j);
                        } else {
                            minAbsoluteValue = absoluteValues.get(i);
                        }
                    }
                }

                if (minAbsoluteValue != -1) {
                    long[] timestampsLastTmp = map.get(minAbsoluteValue);
                    if (absoluteValues.size() > 1) {
                        timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
                    } else if (absoluteValues.size() == 1) {
                        // 当timestamps的size为2，需要与当前时间作为参照
                        long dateOne = timestampsLastTmp[0];
                        long dateTwo = timestampsLastTmp[1];
                        if ((Math.abs(dateOne - dateTwo)) < 100000000000L) {
                            timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
                        } else {
                            long now = System.currentTimeMillis();
                            if (Math.abs(dateOne - now) <= Math.abs(dateTwo - now)) {
                                timestamp = dateOne;
                            } else {
                                timestamp = dateTwo;
                            }
                        }
                    }
                }
            } else {
                timestamp = timestamps.get(0);
            }
        }

        if (timestamp != 0) {
            date = new Date(timestamp);
        }
        return date;
    }

    /**
     * 判断字符串是否为日期字符串
     *
     * @param date 日期字符串
     * @return true or false
     */
    public static boolean isDate(String date) {
        boolean isDate = false;
        if (date != null) {
            if (StringToDate(date) != null) {
                isDate = true;
            }
        }
        return isDate;
    }

    /**
     * 获取日期字符串的日期风格。失敗返回null。
     *
     * @param date 日期字符串
     * @return 日期风格
     */
    public static DateStyle getDateStyle(String date) {
        DateStyle dateStyle = null;
        Map<Long, DateStyle> map = new HashMap<Long, DateStyle>();
        List<Long> timestamps = new ArrayList<Long>();
        for (DateStyle style : DateStyle.values()) {
            Date dateTmp = StringToDate(date, style.getValue());
            if (dateTmp != null) {
                timestamps.add(dateTmp.getTime());
                map.put(dateTmp.getTime(), style);
            }
        }
        dateStyle = map.get(getAccurateDate(timestamps).getTime());
        return dateStyle;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static Date StringToDate(String date) {
        return StringToDate(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date     日期字符串
     * @param parttern 日期格式
     * @return 日期
     */
    public static Date StringToDate(String date, String parttern) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(parttern).parse(date);
            } catch (Exception e) {
            }
        }
        return myDate;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date      日期字符串
     * @param dateStyle 日期风格
     * @return 日期
     */
    public static Date StringToDate(String date, DateStyle dateStyle) {
        Date myDate = null;
        if (dateStyle == null) {
            List<Long> timestamps = new ArrayList<Long>();
            for (DateStyle style : DateStyle.values()) {
                Date dateTmp = StringToDate(date, style.getValue());
                if (dateTmp != null) {
                    timestamps.add(dateTmp.getTime());
                }
            }
            myDate = getAccurateDate(timestamps);
        } else {
            myDate = StringToDate(date, dateStyle.getValue());
        }
        return myDate;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date     日期
     * @param parttern 日期格式
     * @return 日期字符串
     */
    public static String DateToString(Date date, String parttern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(parttern).format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date      日期
     * @param dateStyle 日期风格
     * @return 日期字符串
     */
    public static String DateToString(Date date, DateStyle dateStyle) {
        String dateString = null;
        if (dateStyle != null) {
            dateString = DateToString(date, dateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date     旧日期字符串
     * @param parttern 新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String parttern) {
        return StringToString(date, null, parttern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date      旧日期字符串
     * @param dateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle dateStyle) {
        return StringToString(date, null, dateStyle);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddParttern 旧日期格式
     * @param newParttern  新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String olddParttern, String newParttern) {
        String dateString = null;
        if (olddParttern == null) {
            DateStyle style = getDateStyle(date);
            if (style != null) {
                Date myDate = StringToDate(date, style.getValue());
                dateString = DateToString(myDate, newParttern);
            }
        } else {
            Date myDate = StringToDate(date, olddParttern);
            dateString = DateToString(myDate, newParttern);
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddDteStyle 旧日期风格
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle olddDteStyle, DateStyle newDateStyle) {
        String dateString = null;
        if (olddDteStyle == null) {
            DateStyle style = getDateStyle(date);
            dateString = StringToString(date, style.getValue(), newDateStyle.getValue());
        } else {
            dateString = StringToString(date, olddDteStyle.getValue(), newDateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期字符串
     */
    public static String addYear(String date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期
     */
    public static Date addYear(Date date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加月份后的日期字符串
     */
    public static String addMonth(String date, int yearAmount) {
        return addInteger(date, Calendar.MONTH, yearAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加月份后的日期
     */
    public static Date addMonth(Date date, int yearAmount) {
        return addInteger(date, Calendar.MONTH, yearAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期字符串
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期字符串
     */
    public static String addDay(String date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date 日期字符串
     * @return 增加小时后的日期字符串
     */
    public static String addHour(String date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date 日期
     * @return 增加小时后的日期
     */
    public static Date addHour(Date date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date 日期字符串
     * @return 增加分钟后的日期字符串
     */
    public static String addMinute(String date, int hourAmount) {
        return addInteger(date, Calendar.MINUTE, hourAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date 日期
     * @return 增加分钟后的日期
     */
    public static Date addMinute(Date date, int hourAmount) {
        return addInteger(date, Calendar.MINUTE, hourAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date 日期字符串
     * @return 增加秒钟后的日期字符串
     */
    public static String addSecond(String date, int hourAmount) {
        return addInteger(date, Calendar.SECOND, hourAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date 日期
     * @return 增加秒钟后的日期
     */
    public static Date addSecond(Date date, int hourAmount) {
        return addInteger(date, Calendar.SECOND, hourAmount);
    }

    /**
     * 增加日期的毫秒钟。失败返回null。
     *
     * @param date 日期
     * @return 增加毫秒钟后的日期
     */
    public static Date addMilliSecond(Date date, int milliSecond) {
        return addInteger(date, Calendar.MILLISECOND, milliSecond);
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期字符串
     * @return 年份
     */
    public static int getYear(String date) {
        return getYear(StringToDate(date));
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        return getInteger(date, Calendar.YEAR);
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期字符串
     * @return 月份
     */
    public static int getMonth(String date) {
        return getMonth(StringToDate(date));
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        return getInteger(date, Calendar.MONTH);
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期字符串
     * @return 天
     */
    public static int getDay(String date) {
        return getDay(StringToDate(date));
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期
     * @return 天
     */
    public static int getDay(Date date) {
        return getInteger(date, Calendar.DATE);
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期字符串
     * @return 小时
     */
    public static int getHour(String date) {
        return getHour(StringToDate(date));
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期
     * @return 小时
     */
    public static int getHour(Date date) {
        return getInteger(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 分钟
     */
    public static int getMinute(String date) {
        return getMinute(StringToDate(date));
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期
     * @return 分钟
     */
    public static int getMinute(Date date) {
        return getInteger(date, Calendar.MINUTE);
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 秒钟
     */
    public static int getSecond(String date) {
        return getSecond(StringToDate(date));
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期
     * @return 秒钟
     */
    public static int getSecond(Date date) {
        return getInteger(date, Calendar.SECOND);
    }

    /**
     * 获取日期 。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static String getDate(String date) {
        return StringToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取日期。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期
     * @return 日期
     */
    public static String getDate(Date date) {
        return DateToString(date, DateStyle.YYYY_MM_DD);
    }

    public static String getDateTime(Date date) {
        return DateToString(date, DateStyle.YYYY_MM_DD_HH_MM_SS);
    }

    public static Date getDateTime(String date) {
        try {
            return new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()).parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 时间
     */
    public static String getTime(String date) {
        return StringToString(date, DateStyle.HH_MM_SS);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期
     * @return 时间
     */
    public static String getTime(Date date) {
        return DateToString(date, DateStyle.HH_MM_SS);
    }

    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期字符串
     * @return 星期
     */
    public static Week getWeek(String date) {
        Week week = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = StringToDate(date, dateStyle);
            week = getWeek(myDate);
        }
        return week;
    }

    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期
     * @return 星期
     */
    public static Week getWeek(Date date) {
        Week week = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (weekNumber) {
            case 0:
                week = Week.SUNDAY;
                break;
            case 1:
                week = Week.MONDAY;
                break;
            case 2:
                week = Week.TUESDAY;
                break;
            case 3:
                week = Week.WEDNESDAY;
                break;
            case 4:
                week = Week.THURSDAY;
                break;
            case 5:
                week = Week.FRIDAY;
                break;
            case 6:
                week = Week.SATURDAY;
                break;
        }
        return week;
    }

    /**
     * 获取两个日期相差的天数
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 相差天数
     */
    public static int getIntervalDays(String date, String otherDate) {
        return getIntervalDays(StringToDate(date), StringToDate(otherDate));
    }

    /**
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 相差天数
     */
    public static int getIntervalDays(Date date, Date otherDate) {
        long time = Math.abs(date.getTime() - otherDate.getTime());
        return (int) (time / (24 * 60 * 60 * 1000));
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     */
    public static BigDecimal calLastedTime(Date startDate, Date endDate) {
        return new BigDecimal(endDate.getTime() - startDate.getTime()).divide(new BigDecimal(1000), 3, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @param date      后面的日期
     * @param otherDate 前面的日期
     * @return 相差天数（此方法与上面那个方法有点区别，这个方法不是严格意义的一天之差）
     */
    public static int getIntervalDays2(Date date, Date otherDate) {
        Calendar aCalendar = Calendar.getInstance();

        aCalendar.setTime(otherDate);

        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

        aCalendar.setTime(date);

        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        return day2 - day1;
    }

    /**
     * 日期格式为：yyyyMMdd
     * 两字符串日期相减得天数
     *
     * @param end
     * @param start
     * @return
     */
    public static long calculationTime(String end, String start) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateStyle.YYYYMMDD.getValue());
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(start);
            endDate = sdf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long t = (endDate.getTime() - startDate.getTime()) / (3600 * 24 * 1000);
        return t;
    }

    public static Timestamp dateStrChangeTimestamp(String dateStr) {
        Date date1 = new Date();//获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
        String str = sdf.format(date1);//时间存储为字符串
        return Timestamp.valueOf(str);//转换时间字符串为Timestamp
    }

    /**
     * 判断一个时间点是否在一个时间区间内
     *
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param targetDate 判断的时间点
     * @return
     */
    public static boolean timeWhetherInTimeZone(Long startDate, Long endDate, Long targetDate) {
        if (startDate != null && endDate != null && targetDate > startDate && targetDate < endDate) {
            return true;
        } else if (startDate != null && endDate == null && targetDate > startDate) {
            return true;
        } else if (startDate == null && endDate != null && targetDate < endDate) {
            return true;
        } else if (startDate == null && endDate == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断一个时间点是否在一个时间区间内
     *
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param targetDate 判断的时间点
     * @return
     */
    public static boolean timeWhetherInTimeZone(Date startDate, Date endDate, Date targetDate) {
        if (startDate != null && endDate != null && targetDate.after(startDate) && targetDate.before(endDate)) {
            return true;
        } else if (startDate != null && endDate == null && targetDate.after(startDate)) {
            return true;
        } else if (startDate == null && endDate != null && targetDate.before(endDate)) {
            return true;
        } else if (startDate == null && endDate == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将日期时间转换为截至日期时间
     *
     * @param date
     * @return
     */
    public static Date dateToEndDate(Date date) {
        String s = DateUtil.DateToString(date, "yyyy-MM-dd");
        s = s + " 23:59:59";
        return DateUtil.StringToDate(s, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将时间转换成当天的00:00:00
     *
     * @param date
     * @return
     */
    public static Date getDateStartTime(Date date) {
        return getDateTime(date, DATE_START_TIME);
    }

    /**
     * 将yyyy-MM-dd格式时间转换成当天的00:00:00
     *
     * @param dateStr
     * @return
     */
    public static Date getDateStartTime(String dateStr) {
        return getDateTimeForMillisecond(dateStr + TIME_START_SUFFIX);
    }

    /**
     * 将yyyy-MM-dd格式时间转换成当天的00:00:00
     *
     * @param dateStr
     * @return
     */
    public static String getDateStartTimeForStr(String dateStr) {
        return dateStr + TIME_START_SUFFIX;
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式时间转换成yyyy-MM-dd HH:mm:ss.000
     *
     * @param dateStr
     * @return
     */
    public static Date getDateTimeStartTime(String dateStr) {
        return getDateTimeForMillisecond(dateStr + TIME_START_SUFFIX);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式时间转换成yyyy-MM-dd HH:mm:ss.999
     *
     * @param dateStr
     * @return
     */
    public static Date getDateTimeEndTime(String dateStr) {
        return getDateTimeForMillisecond(dateStr + TIME_End_SUFFIX);
    }

    /**
     * 时间转换成当天的23:59:59
     *
     * @param date
     * @return
     */
    public static Date getDateEndTime(Date date) {
        return getDateTime(date, DATE_END_TIME);
    }

    /**
     * 将将yyyy-MM-dd转换成当天的23:59:59
     *
     * @param dateStr
     * @return
     */
    public static Date getDateEndTime(String dateStr) {
        return getDateTimeForMillisecond(dateStr + TIME_End_SUFFIX);
    }

    /**
     * 将将yyyy-MM-dd转换成当天的23:59:59
     *
     * @param dateStr
     * @return
     */
    public static String getDateEndTimeForStr(String dateStr) {
        return dateStr + TIME_End_SUFFIX;
    }

    private static Date getDateTime(Date date, String format) {
        SimpleDateFormat timeSdf = new SimpleDateFormat(format);
        SimpleDateFormat dateSdf = new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
        try {
            return dateSdf.parse(timeSdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Date getDateTimeForMillisecond(String dateStr) {
        SimpleDateFormat dateSdf = new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS_XXX.getValue());
        try {
            return dateSdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 毫秒时间戳转换成日期 YYY_MM_DD_HH_MM 格式，不包含秒
     *
     * @param time
     * @return
     */
    public static String timeMillisMmToDate(long time) {
        return formatDate(YYYY_MM_DD_HH_MMFormatter, time);
    }

    /**
     * 毫秒时间戳转换成日期 yyyy-MM-dd HH:mm:ss格式
     *
     * @param time
     * @return
     */
    public static String timeMillisToDateTime(long time) {
        return formatDate(YYYY_MM_DD_HH_MM_SSFormatter, time);
    }

    /**
     * 毫秒时间戳转换成日期 yyyy/MM/dd HH:mm:ss格式
     *
     * @param time
     * @return
     */
    public static String timeMillisToDateTimeBackslash(long time) {
        return formatDate(YYYYMM_DD_HH_MM_BACKSLASH, time);
    }

    /**
     * 毫秒时间戳转换成日期 yyyy-MM-dd格式
     *
     * @param time
     * @return
     */
    public static String timeMillisToDate(long time) {
        return formatDate(YYYY_MM_DDFormatter, time);
    }

    public static String formatDate(DateTimeFormatter dateTimeFormatter, long time) {
        return dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))));
    }

    /**
     * @param time
     * @return
     */

    public static Date longToDate(Long time) {
        Date date = new Date(time);
        return date;
    }

    /**
     * 秒数转分秒文字描述，如78 ——> 1分钟18秒
     *
     * @param seconds
     * @return
     */
    public static String secondsToStr(Integer seconds) {
        Integer oneMinute = 60;
        String timeStr;
        if (seconds < oneMinute) {
            timeStr = seconds + "秒";
        } else {
            timeStr = seconds / oneMinute + "分钟";
            Integer remainder = seconds % oneMinute;
            if (remainder != 0) {
                timeStr = timeStr + remainder + "秒";
            }
        }
        return timeStr;
    }

    public static int getAgeByBirth(Date birthday){
        Calendar cal = Calendar.getInstance();
        Calendar bir = Calendar.getInstance();
        bir.setTime(birthday);
        if(cal.before(birthday)){
            throw new IllegalArgumentException("The birthday is before Now,It's unbelievable");
        }
        /*取出当前年月日*/
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayNow = cal.get(Calendar.DAY_OF_MONTH);
        /*取出出生年月日*/
        int yearBirth = bir.get(Calendar.YEAR);
        int monthBirth = bir.get(Calendar.MONTH);
        int dayBirth = bir.get(Calendar.DAY_OF_MONTH);
        /*大概年龄是当前年减去出生年*/
        int age = yearNow - yearBirth;
        /*如果出当前月小与出生月，或者当前月等于出生月但是当前日小于出生日，那么年龄age就减一岁*/
        if(monthNow < monthBirth || (monthNow == monthBirth && dayNow < dayBirth)){
            age--;
        }
        return age;
    }

    public static String[] getLast12Months(String time){
        //处理月份输入条件
        if(time.length()==7){
            time=time+"-01 00:00:00";
        }else if(time.length()==110){
            time=time.substring(0,7)+"-01 00:00:00";
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            date= sdf.parse(time);
        } catch (Exception e) {
            return null;
        }

        String[] last12Months = new String[12];
        Calendar cal = Calendar.getInstance();
        //设置输入条件时间
        cal.setTime(date);

        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1); //要先+1,才能把本月的算进去
        for(int i=0; i<12; i++){
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1); //逐次往前推1个月
            last12Months[11-i] = cal.get(Calendar.YEAR)+ "-" + addZeroForNum(String.valueOf(cal.get(Calendar.MONTH)+1), 2);
        }

        return last12Months;
    }

    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                // sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    /**
     * 获取本年开始时间
     *
     * @return
     */
    public static Date getCurrentYearStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, 1);
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = null;
        try {
            now = longSdf.parse(shortSdf.format(cal.getTime()) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取本年开始时间
     *
     * @return
     */
    public static Date getCurrentYearStartDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, 1);
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = null;
        try {
            now = longSdf.parse(shortSdf.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取N年之后的第一天
     * @param number
     * @return
     */
    public static Date getCurrentYearByNumber(Integer number) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentYearStartTime());
        cal.add(Calendar.YEAR, number);
        return cal.getTime();
    }

    /**
     * 得到当前时间前1年的月份
     *
     * @return
     */
    public static String[] getYearMonth() {
        Date now = new Date();
        String[] yearMonth = new String[12];
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
        Calendar c = Calendar.getInstance();

        for (int i = 0; i < 12; i++) {
            //过去半年
            c.setTime(now);
            c.add(Calendar.MONTH, -i);
            yearMonth[11 - i] = format.format(c.getTime());
        }
        return yearMonth;
    }


    /**
     * 得到当前时间前1年的月份
     *
     * @return
     */
    public static String format(Date date, String format) {
        return cn.hutool.core.date.DateUtil.format(date,format);
    }

    public static void main(String[] args) {
    }
}
