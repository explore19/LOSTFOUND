package com.lost_found.dao;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);

    List<Reply> queryByUserId(Integer userId);

    List<Reply> getAllReply(Integer id);

    Integer getReplyNumber(Integer postId);

    List<Reply> selectUserMessage(Integer userId);

    Integer selectDisReadMessageCount(Integer userId);

    Integer updateDisreadMessage(Integer userId);
}