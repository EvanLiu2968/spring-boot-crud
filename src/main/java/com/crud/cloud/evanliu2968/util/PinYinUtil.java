package com.crud.cloud.evanliu2968.util;

import lombok.extern.log4j.Log4j2;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 获取汉语拼音工具类
 * @author jinpeng.bu
 */
@Log4j2
public class PinYinUtil {

    /**
     * 根据中文转汉语小写拼音
     *
     * @param chinese
     * @return
     */
    public static String getPinYinLowercase(String chinese) {
        char[] charArray = chinese.toCharArray();
        StringBuilder pinyin = new StringBuilder();

        try {
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
            // 设置大小写格式
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            // 设置声调格式：
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            for (int i = 0; i < charArray.length; i++) {
                //匹配中文,非中文转换会转换成null
                if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat);
                    if (hanyuPinyinStringArray != null) {
                        pinyin.append(hanyuPinyinStringArray[0]);
                    }
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error(("根据中文转汉语小写拼音异常："), e);
        }
        return pinyin.toString();
    }

    /**
     * 根据中文转汉语首字母大写拼音，含有非汉语的保留原样
     * @author jinpeng.bu
     * @param chinese
     * @return
     */
    public static String getPinYinFirstUppercase(String chinese) {
        char[] charArray = chinese.toCharArray();
        StringBuilder pinyin = new StringBuilder();

        try {
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
            // 设置大小写格式
            defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
            // 设置声调格式：
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            for (int i = 0; i < charArray.length; i++) {
                //匹配中文,非中文转换会转换成null
                if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat);
                    if (hanyuPinyinStringArray != null) {
                        pinyin.append((hanyuPinyinStringArray[0]).charAt(0));
                    }
                } else {
                    pinyin.append(charArray[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error(("根据中文转汉语首字母大写拼音，含有非汉语的保留原样异常："), e);
        }
        return pinyin.toString();
    }
}
