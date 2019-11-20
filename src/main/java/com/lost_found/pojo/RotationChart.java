package com.lost_found.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class RotationChart {
    @ApiModelProperty(value = "轮播图Id")
    private Integer id;

    @ApiModelProperty(value = "轮播图类型 0-帖子轮播图, 1-广告轮播图")
    private Integer type;

    @ApiModelProperty(value = "轮播的帖子Id")
    private Integer postId;

    @ApiModelProperty(value = "轮播图Id")
    private String image;

    @ApiModelProperty(value = "轮播图路径")
    private String url;

    @ApiModelProperty(value = "轮播图Id")
    private Integer priority;

    @ApiModelProperty(value = "轮播图上传的时间")
    private Date creatTime;

    @ApiModelProperty(value = "轮播图更新的时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString()
    {
        return "RotationChart{" +
                "id=" + id +
                ", type=" + type +
                ", postId=" + postId +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                ", priority=" + priority +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                '}';
    }
}