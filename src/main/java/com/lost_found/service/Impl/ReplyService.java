package com.lost_found.service.Impl;

import com.lost_found.VO.ReplyVO;
import com.lost_found.common.ServerResponse;
import com.lost_found.dao.PostMapper;
import com.lost_found.dao.ReplyMapper;
import com.lost_found.dao.UserMapper;
import com.lost_found.pojo.Post;
import com.lost_found.pojo.Reply;
import com.lost_found.pojo.User;
import com.lost_found.service.IReplyService;
import com.lost_found.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ReplyService implements IReplyService
{
    @Autowired
    ReplyMapper replyMapper;

    @Autowired
    PostMapper postMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 根据帖子Id发表回复
     *
     * @param reply
     * @return
     */
    @Override
    public ServerResponse insert(Reply reply)
    {
        //查询被回复的帖子
        if(reply.getType()!=null&&reply.getType()==0){
            reply.setReplyId(null);
        }
        Post post = postMapper.selectByPrimaryKey(reply.getPostId());
        post.setUpdateTime(new Date());
        postMapper.updateByPrimaryKey(post);
        reply.setCreateTime(new Date());
        reply.setUpdateTime(new Date());
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
            return replyMapper.deleteByPrimaryKey(id) > 0 ?
                    ServerResponse.createBySuccessMessage("删除回复成功!") :
                    ServerResponse.createByErrorMessage("删除回复失败");

    }

    /**
     * 修改回复
     * @param reply
     * @return
     */
    @Override
    public ServerResponse update(Reply reply)
    {
            return replyMapper.updateByPrimaryKey(reply) > 0 ?
                    ServerResponse.createBySuccessMessage("修改回复成功") :
                    ServerResponse.createByErrorMessage("修改回复失败");
    }

    /**
     * 根据用户Id查询用户的所有回复
     * @return
     */
    @Override
    public ServerResponse queryByUserId()
    {
        Integer userId = Integer.valueOf(ServletUtils.getSession().getAttribute("userId").toString());
        User user =userMapper.selectByPrimaryKey(userId);
        if(user==null){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        List<Reply> replyList = replyMapper.queryByUserId(userId);
        List<Map<String,Object>> allData= new ArrayList<>();
        for(Reply reply:replyList){
            Map<String,Object> data =new HashMap<>();
            ReplyVO replyVO =new ReplyVO();
            replyVO.setReply(reply);
            replyVO.setNickName(user.getNickName());
            replyVO.setHeadPortrait(user.getHeadPortrait());
            data.put("myReply",replyVO);
            if(reply.getReplyId()==null){
                Post post=postMapper.selectByPrimaryKey(reply.getPostId());
                data.put("data",post);
                data.put("type",0);
            }else{
                Reply reply1 =replyMapper.selectByPrimaryKey(reply.getReplyId());
                User user1=userMapper.selectByPrimaryKey(reply1.getUserId());
                if(user1==null){
                  continue;
                }
                replyVO.setReplyedUserNickName(user1.getNickName());
                data.put("data",reply1);
                data.put("type",1);
            }
            allData.add(data);
        }
        return ServerResponse.createBySuccess(allData);
    }

    @Override
    public ServerResponse getUserMessage() {
        Integer userId = Integer.valueOf(ServletUtils.getSession().getAttribute("userId").toString());
        List<Reply> replyList= replyMapper.selectUserMessage(userId);
        List<Map<String,Object>> allData= new ArrayList<>();
        for(Reply reply:replyList){
            User user =userMapper.selectByPrimaryKey(reply.getUserId());
            if(user==null){
                continue;
            }
            Map<String,Object> data =new HashMap<>();
            ReplyVO replyVO =new ReplyVO();
            replyVO.setReply(reply);
            replyVO.setNickName(user.getNickName());
            replyVO.setHeadPortrait(user.getHeadPortrait());
            data.put("message",replyVO);
            if(reply.getReplyId()==null){
                Post post=postMapper.selectByPrimaryKey(reply.getPostId());
                data.put("data",post);
                data.put("type",0);
            }else{
                Reply reply1 =replyMapper.selectByPrimaryKey(reply.getReplyId());
                data.put("data",reply1);
                data.put("type",1);
            }
            allData.add(data);
        }
        return ServerResponse.createBySuccess(allData);
    }


}
