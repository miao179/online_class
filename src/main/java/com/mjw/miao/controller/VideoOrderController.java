package com.mjw.miao.controller;

import com.mjw.miao.model.entity.VideoOrder;
import com.mjw.miao.model.request.VideoOrderRequest;
import com.mjw.miao.service.VideoOrderService;
import com.mjw.miao.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pri/order")
public class VideoOrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * 下单接口
     *
     * @param videoOrderRequest
     * @param request
     * @return
     */
    @RequestMapping("save")
    public JsonData saveOrder(@RequestBody VideoOrderRequest videoOrderRequest, HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute("user_id");

        int rows = videoOrderService.save(userId, videoOrderRequest.getVideoId());
        return rows != 0 ? JsonData.buildSuccess(rows) : JsonData.buildError("下单失败");
    }

    @GetMapping("/list")
    public JsonData listOrder(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");
        List<VideoOrder> videoOrderList = videoOrderService.listOrderByUserId(userId);
        return JsonData.buildSuccess(videoOrderList);
    }

    @GetMapping("/userOrder")
    public JsonData userOrder(HttpServletRequest request,Integer videoId){
        Integer userId = (Integer) request.getAttribute("user_id");

        VideoOrder videoOrder = videoOrderService.userOrderById(userId,videoId);
        return JsonData.buildSuccess(videoOrder);
    }
}
