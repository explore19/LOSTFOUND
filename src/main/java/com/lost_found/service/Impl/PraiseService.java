package com.lost_found.service.Impl;

import com.lost_found.common.ServerResponse;
import com.lost_found.dao.PostMapper;
import com.lost_found.dao.PraiseMapper;
import com.lost_found.dao.UserMapper;
import com.lost_found.pojo.Post;
import com.lost_found.pojo.Praise;
import com.lost_found.service.IPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @param praise
     * @return
     */
    @Override
    public ServerResponse addPraise(Praise praise)
    {
//        List<Praise> praiseList = praiseMapper.selectByUserId(praise.getUserId());
//        if (praiseList != null)
//        {
//            for (Praise praise1 : praiseList)
//            {
//                if (praise1.getPostId() == praise.getPostId())
//                {
//                    praise.setUpdateTime(new Date());
//                    Post post = postMapper.selectByPrimaryKey(praise.getPostId());
//                    post.setBrowsePoints(post.getPraisePoints() - 1);
//                    return ServerResponse.createByErrorMessage("已经点过赞了");
//                }
//            }
//        }

        if (praise.getStatus() == null)
        {
            praise.setCreateTime(new Date());
            praise.setStatus(1);
        }
        praise.setUpdateTime(new Date());

        Post post = postMapper.selectByPrimaryKey(praise.getPostId());
        post.setPraisePoints(post.getPraisePoints() + 1);
        postMapper.updateByPrimaryKey(post);

        return praiseMapper.insert(praise) > 0 ?
                ServerResponse.createBySuccessMessage("点赞成功") :
                ServerResponse.createByErrorMessage("点赞失败");
    }

    /**
     * 取消点赞
     *
     * @param praise
     * @return
     */
    @Override
    public ServerResponse deletePraise(Praise praise)
    {
        praise.setUpdateTime(new Date());
        Post post = postMapper.selectByPrimaryKey(praise.getPostId());
        post.setPraisePoints(post.getPraisePoints() - 1);
        postMapper.updateByPrimaryKey(post);

        return praiseMapper.updateByPrimaryKey(praise) > 0 ?
                ServerResponse.createBySuccessMessage("取消点赞成功") :
                ServerResponse.createByErrorMessage("取消点赞失败");
    }
}
