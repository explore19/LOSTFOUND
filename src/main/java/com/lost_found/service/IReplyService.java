package com.lost_found.service;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Reply;

public interface IReplyService
{
    /**
     * 根据帖子Id发表回复
     * @param reply
     * @param postId
     * @return
     */
    ServerResponse insert(Reply reply, Integer postId);

    /**
     * 根据回复id删除回复
     * @param id
     * @return
     */
    ServerResponse delete(Integer id);

    /**
     * 修改回复
     * @param reply
     * @return
     */
    ServerResponse update(Reply reply);

    /**
     * 根据用户Id查询用户的所有回复
     * @param userId
     * @return
     */
    ServerResponse queryByUserId(Integer userId);
}
