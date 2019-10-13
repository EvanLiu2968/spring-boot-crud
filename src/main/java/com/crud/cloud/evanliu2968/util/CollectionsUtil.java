package com.crud.cloud.evanliu2968.util;

import java.util.*;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
public class CollectionsUtil {

    /**
     * 判断集合是否为空（null或者data empty）
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断集合是否不为空（非空且不empty）
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }


    /**
     * 判断Map是否为空（null或者data empty）
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    /**
     * 集合找交集
     * 不支持Arrays.asList(1, 2, 3)
     *
     * @return
     */
    public static <T> List<T> findIntersections(List<T>... list) {
        List<T> listRtn = new ArrayList<>();

        for (int i = 0; i < list.length; i++) {
            if (CollectionsUtil.isEmpty(list[i])) {
                return null;
            }
            if (list[i] != null) {
                if (listRtn.size() == 0) {
                    listRtn.addAll(list[i]);
                } else {
                    listRtn.retainAll(list[i]);
                    if (listRtn.isEmpty()) {
                        return listRtn;
                    }
                }
            }
        }
        return listRtn;
    }

    /**
     * 合并list集合
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> mergeAll(List<T>... list) {
        List<T> mergeList = new ArrayList<>();

        for (int i = 0; i < list.length; i++) {
            if (CollectionsUtil.isEmpty(list[i])) {
                continue;
            }
            mergeList.addAll(list[i]);
        }
        return mergeList;
    }

    /**
     * 根据给定元素生成一个ArrayList
     *
     * @param element
     * @param <T>
     * @return
     */
    public static <T> List<T> asList(T... element) {
        List<T> result = new ArrayList<>();

        if (element != null && element.length > 0) {
            for (int i = 0; i < element.length; i++) {
                result.add(element[i]);
            }
        }

        return result;
    }

    public static <T> void add(List<T> list, T... element) {
        if (isNotEmpty(list) && element != null && element.length > 0) {
            for (int i = 0; i < element.length; i++) {
                list.add(element[i]);
            }
        }
    }

}
