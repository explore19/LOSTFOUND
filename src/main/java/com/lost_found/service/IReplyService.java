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
}
