package com.crud.cloud.evanliu2968.util;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * * 判断对象是否为空或null
 *
 */
@Log4j2
public class ObjectUtil {

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) return true;
        else if (obj instanceof CharSequence) return ((CharSequence) obj).length() == 0;
        else if (obj instanceof Collection) return ((Collection) obj).isEmpty();
        else if (obj instanceof Map) return ((Map) obj).isEmpty();
        else if (obj.getClass().isArray()) return Array.getLength(obj) == 0;
        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 根据属性名获取属性值
     * @author jinpeng.bu
     * */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断对象是否包含某一属性
     * @param clazz
     * @param property
     * @return
     */
    public static boolean hasProperty(Class clazz, String property) {
        boolean result = false;
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getName().equals(property)) {
                    result = true;
                    break;
                }
            }
        } catch (Exception e) {
            log.warn("判断对象是否包含某一属性方法异常：", e);
        }
        return result;
    }

    /** 基本类型、包装类型、String类型 **/
    private static final String[] types = {
            "java.lang.Integer",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.Byte",
            "java.lang.Boolean",
            "java.lang.Character",
            "java.lang.String",
            "int","double","long",
            "short","byte","boolean",
            "char","float"
    };

    /**
     * 判断是否为基本数据类型及其封装数据类型
     * @param obj
     * @return
     */
    public static boolean whetherBasicType(Object obj) {
        boolean result = false;
        for(String str : types) {
            if(obj.getClass().getName().equals(str)) {
                result = true;
                break;
            }
        }
        return result;
    }
}