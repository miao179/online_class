package com.mjw.miao.config;

/**
 * 缓存key管理类
 */
public class CacheKeyManager {
    /**
     * 首页轮播同缓存key
     */
    public static final String INDEX_BANNER_KEY = "index:banner:list";
    /**
     * 首页视频列表缓存key
     */
    public static final String INDEX_VIDEO_LIST = "index:video:list";

    /**
     * 视频详情缓存key, %s是视频id
     */
    public static final String VIDEO_DEFAULT = "video:detail:%s";
}
