package com.lost_found.form;

import com.lost_found.common.BasePagination;

public class QueryPostForm extends BasePagination {
    private Integer postType;
    private String key;
    private String lostPlace;
    public String getKey()
    {
        return key;
    }
    public void setKey(String key)
    {
        this.key = key;
    }
    public String getLostPlace()
    {
        return lostPlace;
    }
    public void setLostPlace(String lostPlace)
    {
        this.lostPlace = lostPlace;
    }
    public Integer getPostType()
    {
        return postType;
    }
    public void setPostType(Integer postType)
    {
        this.postType = postType;
    }
}
