package com.lost_found.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel
public class Post {
    @ApiModelProperty(value = "帖子Id")
    private Integer id;

    @ApiModelProperty(value = "用户Id")
    private Integer userId;

    @ApiModelProperty(value = "帖子标题")
    private String name;

    @ApiModelProperty(value = "失物拾物时间")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date loseTime;

    @ApiModelProperty(value = "失物拾物地点")
    private String lostPlace;

    @ApiModelProperty(value = "发布者联系方式")
    private String contact;

    @ApiModelProperty(value = "帖子所携带图片")
    private String image;

    @ApiModelProperty(value = "失物拾物物品类型")
    private Integer type;

    @ApiModelProperty(value = "帖子详情")
    private String details;

    @ApiModelProperty(value = "帖子类型 0-失物 1-拾物")
    private Integer postType;

    @ApiModelProperty(value = "帖子预览数")
    private Integer browsePoints;

    @ApiModelProperty(value = "帖子点赞数")
    private Integer praisePoints;

    @ApiModelProperty(value = "帖子状态 0-正常 1-待审核")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getLoseTime() {
        return loseTime;
    }

    public void setLoseTime(Date loseTime) {
        this.loseTime = loseTime;
    }

    public String getLostPlace() {
        return lostPlace;
    }

    public void setLostPlace(String lostPlace) {
        this.lostPlace = lostPlace == null ? null : lostPlace.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    public Integer getPostType() {
        return postType;
    }

    public void setPostType(Integer postType) {
        this.postType = postType;
    }

    public Integer getBrowsePoints() {
        return browsePoints;
    }

    public void setBrowsePoints(Integer browsePoints) {
        this.browsePoints = browsePoints;
    }

    public Integer getPraisePoints() {
        return praisePoints;
    }

    public void setPraisePoints(Integer praisePoints) {
        this.praisePoints = praisePoints;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}