package com.mjw.miao.mapper;

import com.mjw.miao.model.entity.Video;
import com.mjw.miao.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper {

    /**
     * 视频列表
     *
     * @return
     */
    List<Video> videoList();

    /**
     * 首页轮播图列表
     *
     * @return
     */
    List<VideoBanner> listBanner();

    /**
     * 查询视频详情
     *
     * @param videoId
     * @return
     */
    Video findDetailById(@Param("videoId") int videoId);

    /**
     * 简单查询视频详情
     *
     * @param videoId
     * @return
     */
    Video findById(@Param("video_id") int videoId);
}
