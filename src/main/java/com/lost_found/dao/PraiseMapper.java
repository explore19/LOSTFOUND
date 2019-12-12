package com.lost_found.dao;

import com.lost_found.pojo.Praise;
import org.apache.ibatis.annotations.Mapper;
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

    List<Praise> selectByUserId(Integer userId);

    Integer getPraiseNumber(Integer postId);
}