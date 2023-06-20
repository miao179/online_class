package com.mjw.miao.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * 视频轮播图
 */
public class VideoBanner {

    private Integer id;

    private String url;

    private String img;

    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS", timezone = "GMT+8")
    @JsonProperty("create_time")
    private Date createTime;

    private Integer weight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "VideoBanner{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", img='" + img + '\'' +
                ", createTime=" + createTime +
                ", weight=" + weight +
                '}';
    }
}
