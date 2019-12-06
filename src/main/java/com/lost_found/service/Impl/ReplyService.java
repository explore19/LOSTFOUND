package com.lost_found.service.Impl;

import com.lost_found.common.ServerResponse;
import com.lost_found.dao.ReplyMapper;
import com.lost_found.pojo.Reply;
import com.lost_found.service.IReplyService;
import com.lost_found.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReplyService implements IReplyService
{
    @Autowired
    ReplyMapper replyMapper;

    /**
     * 根据帖子Id发表回复
     *
     * @param reply
     * @return
     */
    @Override
    public ServerResponse insert(Reply reply)
    {
        /*int type = reply.getType();//回帖的类型
        String info = reply.getInfo(); //回复的信息*/
        //将reply对象存储到数据库
        return replyMapper.insert(reply) > 0 ?
                ServerResponse.createBySuccessMessage("发表回复成功!") :
                ServerResponse.createByErrorMessage("发表回复失败");
    }

    /**
     * 根据回复id删除回复
     * @param id
     * @return
     */
    @Override
    public ServerResponse delete(Integer id)
    {
        Reply reply = replyMapper.selectByPrimaryKey(id);
        Integer replyUserId = reply.getUserId();
        Integer userId = Integer.valueOf(ServletUtils.getSession().getAttribute("userId").toString());
//        if (userId == replyUserId)
//        {
            return replyMapper.deleteByPrimaryKey(id) > 0 ?
                    ServerResponse.createBySuccessMessage("删除回复成功!") :
                    ServerResponse.createByErrorMessage("删除回复失败");
//        }
//        return ServerResponse.createByErrorCodeMessage(403, "没有权限");
    }

    /**
     * 修改回复
     * @param reply
     * @return
     */
    @Override
    public ServerResponse update(Reply reply)
    {
        Integer replyUserId = reply.getUserId();
        Integer userId = Integer.valueOf(ServletUtils.getSession().getAttribute("userId").toString());
//        if (userId == replyUserId)
//        {
            return replyMapper.updateByPrimaryKey(reply) > 0 ?
                    ServerResponse.createBySuccessMessage("修改回复成功") :
                    ServerResponse.createByErrorMessage("修改回复失败");
//        }
//        return ServerResponse.createByErrorCodeMessage(403, "没有权限");
    }

    /**
     * 根据用户Id查询用户的所有回复
     * @return
     */
    @Override
    public ServerResponse queryByUserId()
    {
        Integer userId = Integer.valueOf(ServletUtils.getSession().getAttribute("userId").toString());
        List<Reply> replies = replyMapper.queryByUserId(userId);
        if (replies != null)
        {
            return  ServerResponse.createBySuccessMessage("查询所有回复成功", replies);
        }

        return ServerResponse.createByErrorMessage("查询所有回复失败");
    }


}
