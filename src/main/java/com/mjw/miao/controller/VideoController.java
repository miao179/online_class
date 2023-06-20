package com.mjw.miao.controller;

import com.mjw.miao.model.entity.Video;
import com.mjw.miao.model.entity.VideoBanner;
import com.mjw.miao.service.VideoService;
import com.mjw.miao.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 视频列表
     *
     * @return
     */
    @RequestMapping("/list")
    Object list() {
        List<Video> videoList = videoService.listVideo();
        return JsonData.buildSuccess(videoList);
    }

    /**
     * 轮播图列表
     *
     * @return
     */
    @GetMapping("list_banner")
    public JsonData indexBanner() {

        List<VideoBanner> bannerList = videoService.listBanner();
        return JsonData.buildSuccess(bannerList);
    }

    /**
     * 查询视频详情 包含 章 集 信息
     *
     * @param videoId
     * @return
     */
    @GetMapping("/find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id", required = true) int videoId) {
        Video video = videoService.findDetailById(videoId);
        return JsonData.buildSuccess(video);
    }

}
