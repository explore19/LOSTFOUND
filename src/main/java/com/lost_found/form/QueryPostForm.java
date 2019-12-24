package com.lost_found.form;

import com.lost_found.common.BasePagination;

public class QueryPostForm extends BasePagination {

    private Integer postType;
    private String keyWords;
    private String lostPlace;
    private Integer itemType;
    private Integer sortType;

    public Integer getPostType()
    {
        return postType;
    }
    public void setPostType(Integer postType)
    {
        this.postType = postType;
    }
    public String getKeyWords()
    {
        return keyWords;
    }
    public void setKeyWords(String keyWords)
    {
        this.keyWords = keyWords;
    }
    public String getLostPlace()
    {
        return lostPlace;
    }
    public void setLostPlace(String lostPlace)
    {
        this.lostPlace = lostPlace;
    }
    public Integer getItemType()
    {
        return itemType;
    }
    public void setItemType(Integer itemType)
    {
        this.itemType = itemType;
    }
    public Integer getSortType()
    {
        return sortType;
    }
    public void setSortType(Integer sortType)
    {
        this.sortType = sortType;
    }
}
