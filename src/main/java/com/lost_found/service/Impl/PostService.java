package com.lost_found.service.Impl;


import com.lost_found.common.Const;
import com.lost_found.common.ServerResponse;
import com.lost_found.dao.PostMapper;
import com.lost_found.pojo.Post;
import com.lost_found.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostService implements IPostService
{
    @Autowired
    PostMapper postMapper;


    @Override
    public ServerResponse save(Post post) {
        post.setStatus(Const.STATUS.NEED_EXAMINE_POST.getStatus());  //需要审核
        post.setCreateTime(new Date());
        post.setUpdateTime(new Date());
        //将新添加的post信息加入到数据库中
       return postMapper.insert(post)>0?
               ServerResponse.createBySuccessMessage("发布成功"):
               ServerResponse.createByErrorMessage("发布失败,请稍后再试");
    }

    @Override
    public ServerResponse remove(Integer id) {
        return postMapper.deleteByPrimaryKey(id)>0?
                ServerResponse.createBySuccessMessage("删除成功"):
                ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse update(Post post) {
        post.setUpdateTime(new Date());
        return postMapper.updateByPrimaryKeySelective(post)>0?
                ServerResponse.createBySuccessMessage("更新帖子成功"):
                ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 根据postId查询
     * @param id
     * @return
     */
    @Override
    public ServerResponse<Post> queryById(Integer id)
    {
        Post post = postMapper.selectByPrimaryKey(id);
        if(null != post )
        {
            //查询成功
            return ServerResponse.createBySuccess(post);
        }
        return ServerResponse.createByErrorMessage("未找到帖子信息");
    }


    /**
     * 根据用户id来查询其全部发布
     * @param userId
     * @return
     */
    @Override
    public ServerResponse<List<Post>> queryByUserId(Integer userId)
    {
        return ServerResponse.createBySuccess(postMapper.queryByUserId(userId));
    }


}
