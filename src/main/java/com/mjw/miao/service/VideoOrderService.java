package com.mjw.miao.service;

import com.mjw.miao.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderService {

    int save(int userId, int videoId);

    List<VideoOrder> listOrderByUserId(Integer userId);

    VideoOrder userOrderById(Integer userId,Integer videoId);

}
