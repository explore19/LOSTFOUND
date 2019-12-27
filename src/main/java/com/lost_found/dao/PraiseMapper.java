package com.lost_found.dao;

import com.lost_found.pojo.Praise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PraiseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Praise record);

    int insertSelective(Praise record);

    Praise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Praise record);

    int updateByPrimaryKey(Praise record);

    Praise selectByUserIdAndPostId(@Param("userId") Integer userId,@Param("postId") Integer postId);

    Integer getPraiseNumber(Integer postId);

    Integer checkPraise(@Param("userId") Integer userId,@Param("postId") Integer postId);
}