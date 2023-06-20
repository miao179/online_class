package com.mjw.miao.service.impl;

import com.mjw.miao.config.CacheKeyManager;
import com.mjw.miao.model.entity.Video;
import com.mjw.miao.model.entity.VideoBanner;
import com.mjw.miao.mapper.VideoMapper;
import com.mjw.miao.service.VideoService;
import com.mjw.miao.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;

    /**
     * 视频列表
     *
     * @return
     */
    @Override
    public List<Video> listVideo() {

        try {
            Object object = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_LIST,()->{
                List<Video> videoList = videoMapper.videoList();
                return videoList;
            });

            if(object instanceof List){
                List<Video> videoList = (List<Video>) object;
                return videoList;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //可以返回兜底数据 业务系统降级 -> SpringCloud专题课程
        return null;
    }

    /**
     * 首页轮播图列表
     *
     * @return
     */
    @Override
    public List<VideoBanner> listBanner() {
//        try {
//            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY, () -> {
                List<VideoBanner> bannerList = videoMapper.listBanner();

                System.out.println("从数据库里面找轮播图列表");
                return bannerList;
//            });
//
//            if(cacheObj instanceof List){
//                List<VideoBanner> bannerList = (List<VideoBanner>)cacheObj;
//                return bannerList;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
    }

    @Override
    public Video findDetailById(int videoId) {
        String videoCacheKey = String.format(CacheKeyManager.VIDEO_DEFAULT, videoId);
        //需要使用mybatis关联复杂查询
        try {
            Object object = baseCache.getOneHourCache().get(videoCacheKey,()->{
                Video video = videoMapper.findDetailById(videoId);
                return video;
            });

            if (object instanceof Video){
                Video video = (Video) object;
                return video;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;

    }
}
