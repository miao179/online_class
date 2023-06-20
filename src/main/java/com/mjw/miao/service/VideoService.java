package com.mjw.miao.service;

import com.mjw.miao.model.entity.Video;
import com.mjw.miao.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {
    /**
     * 查询视频列表
     *
     * @return
     */
    List<Video> listVideo();

    /**
     * 首页轮播图列表
     *
     * @return
     */
    List<VideoBanner> listBanner();

    Video findDetailById(int videoId);
}
