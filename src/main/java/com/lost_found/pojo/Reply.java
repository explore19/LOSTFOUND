package com.lost_found.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class Reply {
    @ApiModelProperty(value = "回复Id")
    private Integer id;

    @ApiModelProperty(value = "添加回复的用户Id")
    private Integer userId;

    @ApiModelProperty(value = "被回复的帖子的Id")
    private Integer postId;

    @ApiModelProperty(value = "被回复帖子的Id")
    private Integer replyId;

    @ApiModelProperty(value = "回复的类型, 0-回帖, 1-回复用户")
    private Integer type;

    @ApiModelProperty(value = "回复的内容")
    private String info;

    @ApiModelProperty(value = "第一次回复的时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "修改回复的时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "该用户是否已读")
    private Integer isRead;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsRead()
    {
        return isRead;
    }
    public void setIsRead(Integer isRead)
    {
        this.isRead = isRead;
    }
}