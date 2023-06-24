package com.mjw.miao.service.impl;


import com.mjw.miao.exception.XDException;
import com.mjw.miao.mapper.EpisodeMapper;
import com.mjw.miao.mapper.PlayRecordMapper;
import com.mjw.miao.mapper.VideoMapper;
import com.mjw.miao.mapper.VideoOrderMapper;
import com.mjw.miao.model.entity.Episode;
import com.mjw.miao.model.entity.PlayRecord;
import com.mjw.miao.model.entity.Video;
import com.mjw.miao.model.entity.VideoOrder;
import com.mjw.miao.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {
    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;

    /**
     * 下单操作
     * 未来版本: 优惠券抵扣 风控用户检查 生成订单基础信息 生成支付信息
     *
     * @param userId
     * @param videoId
     * @return
     */
    @Override
    @Transactional
    public int save(int userId, int videoId) {

        //判断是否已经购买
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId, videoId, 1);

        if (videoOrder != null) {
            return 0;
        }

        Video video = videoMapper.findById(videoId);
        if (video == null) {
            throw new XDException(-1, "该视频不存在");
        }
        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1);
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoTitle(video.getTitle());
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setUserId(userId);

        int rows = videoOrderMapper.saveOrder(newVideoOrder);

        //生成播放记录
        if (rows == 1) {
            Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);

            if (episode == null) {
                throw new XDException(-1, "视频没有集信息 请运营人员检查");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCreateTime(new Date());
            playRecordMapper.saveRecord(playRecord);
        }
        return rows;
    }

    /**
     * 视频列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {
        return videoOrderMapper.listOrderByUserId(userId);
    }

    /**
     * 通过用户id查询相应的订单
     * @param userId
     * @return
     */
    @Override
    public VideoOrder userOrderById(Integer userId,Integer videoId) {
        VideoOrder videoOrder = videoMapper.selectOrderById(userId,videoId);
        return videoOrder;
    }
}
