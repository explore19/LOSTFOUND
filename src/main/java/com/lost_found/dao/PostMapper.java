package com.lost_found.dao;

import com.lost_found.common.ServerResponse;
import com.lost_found.form.QueryPostForm;
import com.lost_found.pojo.Post;


import com.lost_found.pojo.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
@Mapper
public interface PostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

    List<Post> queryByForm(QueryPostForm queryPostForm);

    List<Reply> getAllReply(Integer id);
}