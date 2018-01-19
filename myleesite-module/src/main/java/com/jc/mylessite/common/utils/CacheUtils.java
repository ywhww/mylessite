package com.jc.mylessite.common.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtils {

    private static CacheManager cacheManager = ((CacheManager) SpringContextHolder.getBean("cacheManager"));

    private static final String SYS_CACHE = "sysCache";

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public static Object get(String key){
        Cache cache = getCache(SYS_CACHE);

        Element element = cache.get(key);
        return  element == null? null : element.getObjectValue();
    }

    /**
     * 放入缓存
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value){
        Element element = new Element(key, value);
        getCache(cacheName).put(element);
    }

    public static void put(String key,Object value){
        Element element = new Element(key,value);
        getCache(SYS_CACHE).put(element);
    }


    /**
     * 清除缓存
     * @param key
     */
    public static void remove(String key){
        Cache cache = getCache(SYS_CACHE);
        cache.remove(key);
    }


    /**
     * 获取cache没有就创一个
     * @param cachename
     * @return
     */
    public static Cache getCache(String cachename){
        Cache cache = cacheManager.getCache(cachename);
        if (cache==null){
            cacheManager.addCache(SYS_CACHE);
            cache = cacheManager.getCache(SYS_CACHE);
            cache.getCacheConfiguration().setEternal(true);
        }
        return cache;
    }
}
