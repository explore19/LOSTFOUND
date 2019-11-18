package com.lost_found.service.Impl;

import com.lost_found.common.ServerResponse;
import com.lost_found.dao.ReplyMapper;
import com.lost_found.pojo.Reply;
import com.lost_found.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReplyService implements IReplyService
{
    @Autowired
    ReplyMapper replyMapper;

    /**
     * 根据帖子Id发表回复
     * @param reply
     * @param postId
     * @return
     */
    @Override
    public ServerResponse insert(Reply reply, Integer postId)
    {
        //判断回复帖子的类型
        int type = reply.getType();//回帖的类型
        String info = reply.getInfo(); //回复的信息
        if (type == 0)
        {
            //回复帖子内容

        }
        return null;
    }
}
