package com.lost_found.service.Impl;

import com.lost_found.common.ServerResponse;
import com.lost_found.dao.PostMapper;
import com.lost_found.dao.PraiseMapper;
import com.lost_found.dao.UserMapper;
import com.lost_found.pojo.Post;
import com.lost_found.pojo.Praise;
import com.lost_found.service.IPraiseService;
import com.lost_found.utils.ServletUtils;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PraiseService implements IPraiseService
{
    @Autowired
    PraiseMapper praiseMapper;

    @Autowired
    PostMapper postMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 增加点赞
     *
     * @param postId
     * @return
     */
    @Override
    public ServerResponse praise(Integer postId)
    {
        Post post = postMapper.selectByPrimaryKey(postId);
        if(post==null){
            return ServerResponse.createByErrorMessage("帖子不存在");
        }
        Integer userId = Integer.valueOf(ServletUtils.getSession().getAttribute("userId").toString());
        Praise praise = praiseMapper.selectByUserIdAndPostId(userId,postId);
        if(praise ==null){
            praise =new Praise();
            praise.setPostId(postId);
            praise.setUserId(userId);
            praise.setCreateTime(new Date());
            praise.setUpdateTime(new Date());
            if(praiseMapper.insert(praise)>0){
                return ServerResponse.createBySuccessMessage("点赞成功");
            }
        }else{
            if(praiseMapper.deleteByPrimaryKey(praise.getId())>0){
                return ServerResponse.createBySuccessMessage("取消点赞成功");
            }

        }

        return ServerResponse.createByErrorMessage("网络错误请稍后再试");
    }

    @Override
    public boolean checkPraise(Integer postId) {
        Integer userId = Integer.valueOf(ServletUtils.getSession().getAttribute("userId").toString());
        return praiseMapper.selectByUserIdAndPostId(userId, postId) != null;
    }

}
