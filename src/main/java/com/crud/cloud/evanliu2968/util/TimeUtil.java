package com.crud.cloud.evanliu2968.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
public class TimeUtil {

    public static String formatDateFileName_yyyyMMddHHmmss(Date data) {
        SimpleDateFormat fileSecond = new SimpleDateFormat("yyyyMMddHHmmss");
        return fileSecond.format(data);
    }

    ;

    public static String formatDateFileName_yyyyMMddHHmmssSSS(Date data) {
        SimpleDateFormat fileSecond = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return fileSecond.format(data);
    }

    /**
     * 格式化excel中的时间
     *
     * @param date
     * @return
     */
    public static String formatDateForExcelDate(Date date) {
        SimpleDateFormat excelDate = new SimpleDateFormat("yyyy/MM/dd");
        return excelDate.format(date);
    }

    /**
     * 将日期格式化作为文件名
     *
     * @param date
     * @return
     */
    public static String formatDateForFileName_yyyy_MM_dd_HH_mm_ss(Date date) {
        SimpleDateFormat fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return fileName.format(date);
    }

    /**
     * 将日期格式化作为文件名(到分钟)
     *
     * @param date
     * @return
     */
    public static String formatDateForFileName_yyyyMMddhhmm(Date date) {
        SimpleDateFormat fileMinute = new SimpleDateFormat("yyyyMMddHHmm");
        return fileMinute.format(date);
    }

    /**
     * yyMMdd
     *
     * @param date
     * @return
     */
    public static String formatDate_yyMMdd(Date date) {
        SimpleDateFormat yyMMdd = new SimpleDateFormat("yyMMdd");
        return yyMMdd.format(date);
    }


    /**
     * 格式化日期(精确到秒)
     * yy-MM-dd hh:mm:s
     *
     * @param date
     * @return
     */
    public static String formatDateSecond(Date date) {
        SimpleDateFormat second = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        return second.format(date);
    }

    /**
     * 格式化日期(精确到秒)
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatDate_yyyy_MM_dd_HH_mm_ss(Date date) {
        SimpleDateFormat tempTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return tempTime.format(date);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式转为date
     *
     * @param str
     * @return
     */
    public static Date tempDate_yyyy_MM_dd_HH_mm_ss(String str) {
        try {
            SimpleDateFormat tempTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return tempTime.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式转为date
     *
     * @param str
     * @return
     */
    public static Date tempDateDay(String str) {
        try {
            SimpleDateFormat tempTime3 = new SimpleDateFormat("yyyy-MM-dd");
            return tempTime3.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * yyyy/MM/dd 格式转为日期
     *
     * @param string
     * @return
     */
    public static Date stringToDateByExcelFormat(String string) {
        try {
            SimpleDateFormat excelDate = new SimpleDateFormat("yyyy/MM/dd");
            return excelDate.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 将yyyyMMdd格式转为date
     *
     * @param str
     * @return
     */
    public static Date strToDateYYYYMMdd(String str) {
        try {
            SimpleDateFormat tempTime2 = new SimpleDateFormat("yyyyMMdd");
            return tempTime2.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 格式化日期(精确到天)
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String formatDateYYYY_MM_dd(Date date) {
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        return day.format(date);
    }

    /**
     * 格式化日期(精确到天)
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static Date parseDateYYYY_MM_dd(String date) throws ParseException {
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        return day.parse(date);
    }

    /**
     * 格式化日期(精确到天)
     * yyyy年MM月dd天
     *
     * @param date
     * @return
     */
    public static String formatDateDetailToYYYY年MM月dd日(Date date) {
        SimpleDateFormat detailDay = new SimpleDateFormat("yyyy年MM月dd日");
        return detailDay.format(date);
    }

    /**
     * 格式化日期(精确到天)
     * yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String formatDateDetailToYYYYmmDD(Date date) {
        SimpleDateFormat detailDay2 = new SimpleDateFormat("yyyyMMdd");
        return detailDay2.format(date);
    }


    /**
     * 将字符串转换成日期
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date formateDate(String date) throws Exception {
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        return day.parse(date);
    }

    /**
     * 将字符日期转换成Date
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date parseStringToDate(String date) throws Exception {
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        return day.parse(date);
    }

    public static String formatDoubleNumber(double number) {
        DecimalFormat df = new DecimalFormat("#");
        return df.format(number);
    }

    /**
     * UNIX时间戳
     *
     * @return
     */
    public static long getTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }

    /**
     * @param lo
     * @return
     */
    public static String longToDate(long lo) {
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtil.isEmpty(lo)) {
            return "";
        }
        Date date = new Date(lo);
        return day.format(date);
    }

    /**
     * 获取当前时间 精确到秒
     *
     * @return
     */
    public static String getNowToString() {
        return formatDate_yyyy_MM_dd_HH_mm_ss(new Date());
    }

    /**
     * 获取当前时间 到月份
     * @return
     */
    public static String getNowToMonth(){
        SimpleDateFormat tempTime = new SimpleDateFormat("yyyy-MM");
        return tempTime.format(new Date());
    }

    /**
     * 时间增加秒数
     *
     * @param data
     * @param second
     * @return
     */
    public static Date addSecond(Date data, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 时间增加天
     *
     * @param data
     * @param day
     * @return
     */
    public static Date addDay(Date data, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
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
        Date startDate = strToDateYYYYMMdd(start);
        Date endDate = strToDateYYYYMMdd(end);
        long t = (endDate.getTime() - startDate.getTime()) / (3600 * 24 * 1000);
        return t;
    }

    public static Integer countTwoDate(String startDate, String endDate) {
        try {
            Date start = parseDateYYYY_MM_dd(startDate);
            Date end = parseDateYYYY_MM_dd(endDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(start);
            long time1 = cal.getTimeInMillis();
            cal.setTime(end);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取年份
     *
     * @return
     */
    public static int getYear() {
        Calendar cal = Calendar.getInstance();
        //获取年份
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取秒数  目前仅支持天-时-分
     *
     * @param time
     * @param timeUnit
     * @return
     */
    public static int toSconds(int time, TimeUnit timeUnit) {

        if (time <= 0) {
            return 0;
        }

        if (timeUnit.equals(TimeUnit.MINUTES)) {
            return 60 * time;
        } else if (timeUnit.equals(TimeUnit.HOURS)) {
            return 60 * 60 * time;
        } else if (timeUnit.equals(TimeUnit.DAYS)) {
            return 24 * 60 * 60 * time;
        }

        return 0;
    }

}

