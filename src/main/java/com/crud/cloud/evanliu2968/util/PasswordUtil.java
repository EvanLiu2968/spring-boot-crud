package com.crud.cloud.evanliu2968.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
public class PasswordUtil {

    public static Integer SIX_BITS = 6;
    public static Integer PASSWORD_LENGTH = 50;//密码长度

    public final static Pattern PASSWORD_VERIFIERS = Pattern.compile("(?!^\\d+$)(?!^[A-Za-z]+$)(?!^[^A-Za-z0-9]+$)(?!^.*[\\u4E00-\\u9FA5].*$)^\\S{6,20}$");

    /**
     * 生成随机密码
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz!@#-");
        StringBuffer letter = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
        StringBuffer number = new StringBuffer("0123456789");
        StringBuffer specialCharacter = new StringBuffer("!@#-");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int range = buffer.length();
        sb.append(letter.charAt(r.nextInt(letter.length())));
        sb.append(number.charAt(r.nextInt(number.length())));
        sb.append(specialCharacter.charAt(r.nextInt(specialCharacter.length())));
        for (int i = 0; i < length - 3; i ++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }

    //检查是否字符串中包含数字和字母和字符串
    public static Boolean containLetterAndNumber(String str){
        /*boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母

        for(int i=0 ; i<str.length() ; i++){ //循环遍历字符串
            if(Character.isDigit(str.charAt(i))){     //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            }else if(Character.isLetter(str.charAt(i))){   //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }*/
        //String reg = "^(?![^a-zA-Z]+$)(?!\\d+$)(?![^;:'\",_`~@#%&\\{\\}\\[\\]\\|\\\\\\<\\>\\?\\/\\.\\-\\+\\=\\!\\$\\^\\*\\(\\)]+$).{6,}$";
        //非汉字非字母非数字的字符
//        Pattern pattern = Pattern.compile("[^\\dA-Za-z\\u4e00-\\u9fa5\\s+]");
//        Matcher matcher = pattern.matcher(str);
        //匹配是否包含字母、数字、特殊字符
        boolean flag = str.matches(".*[a-zA-Z]+.*") && str.matches(".*[\\d]+.*") /*&& matcher.find()*/;//暂时取消
        return  flag;
    }

    //检查是否字符串中包含汉字
    public static Boolean containChinese(String str){
        boolean temp = false;
        Pattern p= Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m=p.matcher(str);
        if(m.find()){
            temp =  true;
        }
        return temp;
    }

    public static void main(String stras[]){
        String str ="1234567890abcdefghijklmnopqrstuvswyzABCDEFGHIJKLMNOPQRSTUVSWYZ中水电费水电费水电费水电费第三方是否是的发生东方闪电发水电费水电费水电费水电费 ";
        str = str.trim();
        //Pattern pattern = Pattern.compile("[^\\dA-Za-z\\u3007\\u4E00-\\u9FCB\\uE815-\\uE864]");
        //非中文非字母非数字的字符
        Pattern pattern = Pattern.compile("[^\\dA-Za-z\\u4e00-\\u9fa5\\s+]");
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.find());
        System.out.println(str.matches(".*[a-zA-Z]+.*"));
        System.out.println(str.matches(".*[\\d]+.*"));
    }
}
