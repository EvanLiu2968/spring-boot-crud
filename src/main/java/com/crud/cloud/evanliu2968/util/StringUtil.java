package com.crud.cloud.evanliu2968.util;

//import com.deepoove.poi.XWPFTemplate;

import java.text.Collator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by evanliu2968 on 2019-10-14.
 *
 * @author kanghong.zhao
 */
public class StringUtil {

    public static final String COMMA = ",|\\，";

    public static final String COMMA_OUT = ",";

    public static final String BLANK = " ";

    public static final String REGEX = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，？]|\n|\r|\t";

    public static final String NOBLANK = "\\s*|\t|\r|\n";

    private static final String MOBILE_REGEX = "^1[3|4|5|7|8][0-9]\\d{8}$";

    private static final String PHONE_REGEX = "^(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{7,14}$";

    public static final String ADMIN_COUNT = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,20}$";

    public static final String CHECK_BLACK = "^[^ ]+$";

    public static final String COMMA_DAWN = "、";

    /**
     * 校验是否为有效的手机号。
     * <p>
     * 运营商号段如下：
     * 中国联通号码：130、131、132、145（无线上网卡）、155、156、185（iPhone5上市后开放）、186、176（4G号段）、
     * 175（2015年9月10日正式启用，暂只对北京、上海和广东投放办理）
     * 中国移动号码：134、135、136、137、138、139、147（无线上网卡）、150、151、152、157、158、159、182、183、187、188、178
     * 中国电信号码：133、153、180、181、189、177、173、149 虚拟运营商：170、1718、1719
     * 手机号前3位的数字包括：
     * 1 :1
     * 2 :3,4,5,7,8
     * 3 :0,1,2,3,4,5,6,7,8,9
     * 总结： 目前java手机号码正则表达式有：
     * a :"^1[3|4|5|7|8][0-9]\\d{4,8}$"    一般验证情况下这个就可以了
     * b :"^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$"
     *
     * @param phone phone
     * @return boolean
     */
    public static boolean isValidPhone(String phone) {
        if (isEmpty(phone)) {
            return false;
        }

//            Pattern p = Pattern.compile(PHONE_REGEX);
//            Matcher m = p.matcher(phone);

        return phone.matches(MOBILE_REGEX) || phone.matches(PHONE_REGEX);
    }

    public static List<Integer> toIntList(String idsStr, String spliter) {
        List<Integer> ids = new ArrayList<>();

        if (isEmpty(idsStr)) {
            return ids;
        }
        for (String idStr : idsStr.split(spliter)) {
            ids.add(Integer.parseInt(idStr));
        }
        return ids;
    }

    public static List<Integer> toIntListByComma(String idsStr) {
        return toIntList(idsStr, COMMA);
    }

    public static List<String> toStrListBySpliter(String strsStr, String spliter) {
        List<String> strs = new ArrayList<>();

        if (isEmpty(strsStr)) {
            return strs;
        }
        for (String str : strsStr.split(spliter)) {
            strs.add(str);
        }
        return strs;
    }


    /**
     * 校验是否是邮箱。
     *
     * @param email email
     * @return boolean
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) {
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static List<String> toStrListByComma(String strsStr) {
        return toStrListBySpliter(strsStr, COMMA);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty() || str.trim().isEmpty();
    }

    public static boolean isEmptyWithinNullStr(String str) {
        return isEmpty(str) || "null".equals(str);
    }

    /**
     * 判断其中一个字符串是否为空
     *
     * @param strs
     * @return
     */
    public static boolean isOneEmpty(String... strs) {

        if (strs == null) {
            return true;
        }

        for (String str : strs) {
            if (str == null || str.isEmpty() || str.trim().isEmpty()) {
                return true;
            }
        }

        return false;
    }

    public static boolean isEmpty(Object obj) {
        return obj == null || "".equals(obj);
    }

    public static String toStrByComma(List<String> objs) {
        StringBuffer sb = new StringBuffer();
        if (objs == null || objs.isEmpty()) {
            return sb.toString();
        }

        for (Object obj : objs) {
            sb.append(obj).append(COMMA_DAWN);
        }

        sb = sb.deleteCharAt(sb.lastIndexOf(COMMA_DAWN));
        return sb.toString();
    }

    public static String toStrByCommaDawn(List<Integer> objs) {
        StringBuffer sb = new StringBuffer();
        if (objs == null || objs.isEmpty()) {
            return sb.toString();
        }

        for (Object obj : objs) {
            sb.append(obj).append(COMMA_DAWN);
        }

        sb = sb.deleteCharAt(sb.lastIndexOf(COMMA_DAWN));
        return sb.toString();
    }

    public static boolean isBlankList(List<String> cells) {
        if (cells != null && cells.size()  > 0){
            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i) != null && !cells.get(i).trim().equals("")){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 替换html标签
     *
     * @param content
     * @return
     */
    public static String replaceHtml(String content) {
        if (isEmpty(content)) {
            return "";
        }
        return content.replaceAll("</?[^>]+>", "").replaceAll("&nbsp;", " ");
    }

    public static String changeSpecialCharacter(String content) {
        if (isEmpty(content)) {
            return "";
        }
        content = content.replace("$", "\\$");
        return content;
    }

    public static boolean isContainscontent(String content) {
        String regx = String.valueOf((char) 160) + "｜ ｜　|&|&nbsp|<[^>]+>";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        return matcher.find();
    }

    public static String importStemRpeplace(String content) {
        if (content == null) {
            return "";
        }
        if (isContainscontent(content)) {
            content = content.replaceAll("&", "&amp;");
            content = content.replaceAll("\"", "&quot;");
            content = content.replaceAll("\t", "&nbsp;&nbsp;");
            content = content.replaceAll(" ", "&nbsp;");
            content = content.replaceAll("　", "&nbsp;");
            content = content.replaceAll(String.valueOf((char) 160), "&nbsp;");
            content = content.replaceAll("<", "&lt;");
            content = content.replaceAll(">", "&gt;");
        }
        return content;
    }

    public static String exportStemRpeplace(String content) {
        if (content == null) {
            return "";
        }
        content = content.replaceAll("&amp;", "&");
        content = content.replaceAll("&quot;", "\"");
        content = content.replaceAll("&nbsp;&nbsp;", "\t");
        content = content.replaceAll("&nbsp;", " ");
        content = content.replaceAll("&lt;", "<");
        content = content.replaceAll("&gt;", ">");
        return content;
    }

    public static int countStr(String param, String target) {
        String str = target;
        int i = 0;
        while (str.contains(param)) {
            i++;
            str = str.substring(str.indexOf(param) + param.length());
        }

        return i;
    }

    public static String getStringNoBlank(String str) {
        if (str != null && !"".equals(str)) {
            Pattern p = Pattern.compile(NOBLANK);
            Matcher m = p.matcher(str);
            String strNoBlank = m.replaceAll("");
            return strNoBlank;
        } else {
            return str;
        }
    }

    public static String getStringNoBlankBytrim(String str) {
        if (str != null) {
            str = str.replace((char) 12288, ' ');
            str = str.trim();
        }
        return str;
    }

    /**
     * 全角转半角
     *
     * @param QJstr
     * @return
     */
    public static final String QBchange(String QJstr) {
        String outStr = "";
        String Tstr = "";
        byte[] b = null;

        for (int i = 0; i < QJstr.length(); i++) {
            try {
                Tstr = QJstr.substring(i, i + 1);
                b = Tstr.getBytes("unicode");
            } catch (java.io.UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (b[3] == -1) {
                b[2] = (byte) (b[2] + 32);
                b[3] = 0;
                try {
                    outStr = outStr + new String(b, "unicode");
                } catch (java.io.UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                outStr = outStr + Tstr;
            }
        }

        return outStr;
    }

    public static String listToString(List<String> list, String splitCh) {
        StringBuffer sb = new StringBuffer();
        if (list == null || list.isEmpty()) {
            return sb.toString();
        }
        list.stream().forEach(str -> sb.append(str).append(splitCh));
        if (splitCh.length() != 0) {
            sb.deleteCharAt(sb.length() - splitCh.length());
        }
        return sb.toString();
    }

    public static String listToString(List<String> list, String splitCh, String pre, String suff) {
        StringBuffer sb = new StringBuffer();
        if (list == null || list.isEmpty()) {
            return sb.toString();
        }
        list.stream().forEach(str -> sb.append(pre + str + suff).append(splitCh));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String intListToString(List<Integer> list, String splitCh) {

        StringBuffer sb = new StringBuffer();
        if (list == null || list.isEmpty()) {
            return sb.toString();
        }

        List<String> strs = list.stream().map(i -> i + "").collect(Collectors.toList());
        return listToString(strs, splitCh);
    }

    public static void sort(List<String> list) {
        Collections.sort(list, Collator.getInstance(Locale.CHINA));
    }

    public static String listToStringForSort(List<String> list, String splitCh) {
        sort(list);
        return listToString(list, splitCh);
    }

    public static String toScoreKey(String answer) {
        return answer.replace(",", "#");
    }

    /**
     * 判断是否含有特殊字符
     *
     * @param str
     * @return true为包含，false为不包含
     */
    public static boolean isSpecialChar(String str) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 根据逗号或者空格分隔字符串；优先逗号
     *
     * @param content
     * @return
     */
    public static List<String> strToListByCommaOrBlank(String content) {

        if (isEmpty(content)) {
            return new ArrayList<>();
        }

        if (content.contains(COMMA_OUT)) {
            return Arrays.asList(content.split(COMMA_OUT));
        } else {
            return Arrays.asList(content.split(BLANK));
        }
    }

    /**
     * 对字符串列表进行排序
     **/
    public static void sortStrings(List<String> strList) {
        Collections.sort(strList, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });
    }

    public static String getCharAndNumr(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                //取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
    public static String toUtf8String(String s) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println("将文件名中的汉字转为UTF8编码的串时错误，输入的字符串为：" + s);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
    //
    public static void main(String[] args) {
//        String ss = " AI平台邀请您参加在线测评。请使用专属链接进入答题 http://devtas6.cepin.net/api/7RZRjy ,或使用个人通行码  47b80577 登录  http://devtas6.cepin.net/exam/login 进入答题。请勿转发他人。";
//
//        System.out.println(QBchange(ss));
//        for (int i = 0; i < 10; i++) {
//            System.out.println(getCharAndNumr(8));
//        }

        String str = "!#$%^&*()55A@55";
        boolean matches = Pattern.compile(StringUtil.CHECK_BLACK).matcher(str).matches();
        if (matches) {
            System.out.println(Pattern.compile(StringUtil.ADMIN_COUNT).matcher(str).matches());
        }else {
            System.out.println(str + "字符串包含空格");
        }
    }

}
