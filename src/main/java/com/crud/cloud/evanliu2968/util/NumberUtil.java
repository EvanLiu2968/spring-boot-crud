package com.crud.cloud.evanliu2968.util;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
public class NumberUtil {

    private static final Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$");

    public static Boolean isEmpty(Integer num) {
        return num == null || num <= 0;
    }

    //double 类型保留一位小数
    public static double changeDouble(Double dou) {
        NumberFormat nf = new DecimalFormat("000.0 ");
        dou = Double.parseDouble(nf.format(dou));
        return dou;
    }

    /**
     * 判断是否是整数
     *
     * @param s
     * @return
     */
    public final static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim())) {
            return s.matches("^[0-9]*$");
        } else {
            return false;
        }
    }

    /**
     * 将double类型的数字保留两位小数（四舍五入）
     *
     * @param number
     * @return
     */
    public static String formatNumber(double number) {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("#0.00");
        return df.format(number);
    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (StringUtil.isEmpty(str)) {
            return false;
        }
//        Pattern pattern = Pattern.compile("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+");//BigDecimal

        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，向下取数 如：2.35 取 2.3。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div_down(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static String toPercent(BigDecimal originNum, int scale) {
        return originNum.setScale(scale, BigDecimal.ROUND_HALF_UP).toString() + "%";
    }

    /**
     * sub/total 向下取整百分比
     *
     * @param sub
     * @param total
     * @return
     */
    public static String toDownIntegerPercent(Integer sub, Integer total) {

        if (total == null || total == 0) {
            return "0%";
        }

        return new BigDecimal(sub).multiply(new BigDecimal(100)).divide(new BigDecimal(total), 0, RoundingMode.DOWN).intValue() + "%";
    }

    /**
     * 格式化数字在前面补0
     *
     * @param len
     * @param number
     * @return
     */
    public static String formatZeroPrefix(Integer len, Long number) {
        return String.format("%0" + len + "d", number);
    }

    public static void main(String[] args) {System.out.println(toDownIntegerPercent(0, 1000));}


}
