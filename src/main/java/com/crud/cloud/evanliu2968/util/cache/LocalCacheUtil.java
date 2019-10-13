package com.crud.cloud.evanliu2968.util.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by evanliu2968 on 2019-10-14.
 * 本地缓存工具类
 */
public class LocalCacheUtil {

    // 缓存map
    public static Map<String, Object> cacheMap = new HashMap<String, Object>();
    // 缓存有效期map
    public static Map<String, Long> expireTimeMap = new HashMap<String, Long>();

//    //默认的缓存容量
//    private static int DEFAULT_CAPACITY = 512;
//    //最大容量
//    private static int MAX_CAPACITY = 100000;
//    //刷新缓存的频率
//    private static int MONITOR_DURATION = 2;
//    // 启动监控线程
//    static {
//        new Thread(new TimeoutTimerThread()).start();
//    }
//    //使用默认容量创建一个Map
//    private static ConcurrentHashMap<String, CacheEntity> cache = new ConcurrentHashMap<String, CacheEntity>(
//            DEFAULT_CAPACITY);


    /**
     * 获取指定的value，如果key不存在或者已过期，则返回null
     * @param key
     * @return
     */
    public static Object get(String key) {
        if (!cacheMap.containsKey(key)) {
            return null;
        }
        if (expireTimeMap.containsKey(key)) {
            if (expireTimeMap.get(key) < System.currentTimeMillis()) { // 缓存失效，已过期
                return null;
            }
        }
        return cacheMap.get(key);
    }

    /**
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getT(String key) {
        Object obj = get(key);
        return obj == null ? null : (T) obj;
    }

    /**
     * 设置value（不过期）
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        cacheMap.put(key, value);
    }

    /**
     * 设置value
     * @param key
     * @param value
     * @param millSeconds 过期时间（毫秒）
     */
    public static void set(final String key, Object value, int millSeconds) {
        final long expireTime = System.currentTimeMillis() + millSeconds;
        cacheMap.put(key, value);
        expireTimeMap.put(key, expireTime);
        if (cacheMap.size() > 2) { // 清除过期数据
            new Thread(new Runnable() {
                public void run() {
                    // 此处若使用foreach进行循环遍历，删除过期数据，会抛出java.util.ConcurrentModificationException异常
                    Iterator<Map.Entry<String, Object>> iterator = cacheMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, Object> entry = iterator.next();
                        if (expireTimeMap.containsKey(entry.getKey())) {
                            long expireTime = expireTimeMap.get(key);
                            if (System.currentTimeMillis() > expireTime) {
                                iterator.remove();
                                expireTimeMap.remove(entry.getKey());
                            }
                        }
                    }
                }
            }).start();
        }
    }
//
//    /**
//     * key是否存在
//     * @param key
//     * @return
//     */
//    public static boolean isExist(String key) {
//        return cacheMap.containsKey(key);
//    }



    /**
     * 将key-value 保存到本地缓存并制定该缓存的过期时间
     *
     * @param key
     * @param value
     * @param expireTime 过期时间，如果是-1 则表示永不过期
     * @return
     */
//    public static boolean putValue(String key, Object value, int expireTime) {
//        return putCloneValue(key, value, expireTime);
//    }

    /**
     * 将值通过序列化clone 处理后保存到缓存中，可以解决值引用的问题
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
//    public static boolean putCloneValue(String key, Object value, int expireTime) {
//        try {
//            if (cache.size() >= MAX_CAPACITY) {
//                return false;
//            }
//            // 序列化赋值
//            CacheEntity entityClone = clone(new CacheEntity(value, System.nanoTime(), expireTime));
//            cache.put(key, entityClone);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    /**
     *
     * 序列化 克隆处理
     * @param object
     * @return
     */
//    public static <T extends Serializable> T clone(T object) {
//        T cloneObject = null;
//        try {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(baos);
//            oos.writeObject(object);
//            oos.close();
//            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
//            ObjectInputStream ois = new ObjectInputStream(bais);
//            cloneObject = (T) ois.readObject();
//            ois.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return cloneObject;
//    }

    /**
     *从本地缓存中获取key对应的值，如果该值不存则则返回null
     *
     * @param key
     * @return
     */
//    public static Object getValue(String key) {
//        CacheEntity cacheEntity = cache.get(key);
//        return cache.get(key).getValue();
//
//    }
//
//    /**
//     * 清空所有
//     */
//    public void clear() {
//        cache.clear();
//    }

    /**
     * 过期处理线程
     *
     */
//    static class TimeoutTimerThread implements Runnable {
//        public void run() {
//            while (true) {
//                try {
//                    TimeUnit.SECONDS.sleep(MONITOR_DURATION);
//                    checkTime();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        /**
//         * 过期缓存的具体处理方法
//         * @throws Exception
//         */
//        private void checkTime() throws Exception {
//            //"开始处理过期 "
//            for (String key : cache.keySet()) {
//                CacheEntity tce = cache.get(key);
//                long timoutTime = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime()
//                        - tce.getGmtModify());
//                //" 过期时间 : "+timoutTime);
//                if (tce.getExpire() > timoutTime) {
//                    continue;
//                }
//                System.out.println(" 清除过期缓存 ： " + key);
//                //清除过期缓存和删除对应的缓存队列
//                cache.remove(key);
//            }
//        }
//    }


}
