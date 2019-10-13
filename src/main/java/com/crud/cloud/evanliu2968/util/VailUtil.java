package com.crud.cloud.evanliu2968.util;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bin.yang
 */
public class VailUtil {

    public static final String MOBILE_REX = "^[1][3,4,5,6,7,8,9][0-9]{9}$";
    public static final String AREA_TELEPHONE_REX = "^[0][1-9]{2,3}-[0-9]{5,10}$";
    public static final String TELEPHONE_REX = "^[1-9]{1}[0-9]{5,8}$";
    public static final String EMAIL_REX = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
    public static final int ELEPHONE_LENGTH = 9;

    public static final String LETTER_OR_NUM_REGEX = "^[a-z0-9A-Z]+$";

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        // 验证手机号
        p = Pattern.compile(MOBILE_REX);
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 电话号码验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        // 验证带区号的
        p1 = Pattern.compile(AREA_TELEPHONE_REX);
        // 验证没有区号的
        p2 = Pattern.compile(TELEPHONE_REX);
        if (str.length() > ELEPHONE_LENGTH) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    public static boolean isEmail(String email) {
        Pattern p = Pattern.compile(EMAIL_REX);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 出生日期范围：起期1940，止期为当前时间。
     *
     * @param birthdayStr
     * @return
     * @throws Exception
     */
    public static boolean birthdayIllegal(String birthdayStr) throws Exception {
        Calendar calendar = Calendar.getInstance();
        //获取年
        int year = calendar.get(Calendar.YEAR);
        //获取月份，0表示1月份
        int month = calendar.get(Calendar.MONTH) + 1;
        //获取当前天数
        int day = calendar.get(Calendar.DAY_OF_MONTH) + 1;
        Date minDate = TimeUtil.formateDate("1939-12-31");
        Date maxDate = TimeUtil.formateDate(year + "-" + month + "-" + day);
        Date birthday = TimeUtil.formateDate(birthdayStr);
        if (birthday.after(minDate) && birthday.before(maxDate)) {
        } else {
            //throw new WrappedException(BizEnums.BirthdayNotVail);
        }

        return true;
    }

    /**
     * 系统有效期、项目起止期、任务起止期、商品有效时间:今年-2050需判断止期大于起期
     *
     * @param startDateStr
     * @param endDateStr
     * @return
     * @throws Exception
     */
    public static void otherDayIllegal(String startDateStr, String endDateStr) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR) - 1;
        calendar.set(year, 11, 31, 23, 59, 59);
        Date minDate = calendar.getTime();
        calendar.set(2051, 00, 01, 00, 00, 00);
        Date maxDate = calendar.getTime();
        Date startDate = startDateStr != null ? TimeUtil.tempDate_yyyy_MM_dd_HH_mm_ss(startDateStr) : null;
        Date endDate = endDateStr != null ? TimeUtil.tempDate_yyyy_MM_dd_HH_mm_ss(endDateStr) : null;
        if (startDate != null && endDate != null && startDate.after(endDate)) {
            //throw new WrappedException(BizEnums.StartDateLaterThanEndDate);
        }
        boolean inStartScope = !(startDate.after(minDate) && startDate.before(maxDate));
        if (startDate != null && inStartScope) {
            //throw new WrappedException(BizEnums.StartDateNotInScope);
        }
        boolean inEndScope = !(endDate.after(minDate) && endDate.before(maxDate));
        if (startDate != null && endDate != null && inEndScope) {
            //throw new WrappedException(BizEnums.EndDateNotInScope);
        }
    }

    public static boolean isLetterOrNumber(String s){
        if (s != null){
            return s.matches(LETTER_OR_NUM_REGEX);
        }

        return false;
    }

}
