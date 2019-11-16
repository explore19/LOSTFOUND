package com.lost_found.service.Impl;


import com.lost_found.common.ServerResponse;
import com.lost_found.dao.PostMapper;
import com.lost_found.pojo.Post;
import com.lost_found.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService implements IPostService
{
    @Autowired
    PostMapper postMapper;



    @Override
    public ServerResponse announceInfo(Post info)
    {
        //将新添加的post信息加入到数据库中
        int result = postMapper.insert(info);
        if (result > 0)
        {
            //添加成功
            //传递'1'告诉前端添加成功, 跳转到首页并重新查询数据库
            return ServerResponse.createBySuccessMessage("发布成功");
        }
        else
        {
            //添加发布失败
            //告诉前端添加发布失败, 重新跳转到发布页面
            return ServerResponse.createByErrorMessage("添加发布失败, 请重新添加");
        }
    }

    /**
     * 根据id删除
     * @param info
     * @return 大于零为成功
     */
    @Override
    public ServerResponse delAnnounceInfo(Post info)
    {
        int result = postMapper.deleteByPrimaryKey(info.getId());
        if(result > 0)
        {
            //删除成功
            //跳转到我的发布页面
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        else
        {
            //删除失败
            return ServerResponse.createByErrorMessage("删除失败, 请重新删除");
        }
    }

    /**
     * 根据Post对象来修改信息
     * @param info
     * @return
     */
    @Override
    public ServerResponse updateAnnounceInfo(Post info)
    {
        int result = postMapper.updateByPrimaryKeySelective(info);
        if(result > 0)
        {
            //修改成功
            //跳转到我的发布页面
            return ServerResponse.createBySuccess("修改成功");
        }
        else
        {
            //修改失败
            return ServerResponse.createByErrorMessage("修改失败, 请重新修改");
        }
    }

    /**
     * 根据用户id来查询其全部发布
     * @param id
     * @return
     */
    @Override
    public ServerResponse queryByUserId(Integer id)
    {
        List<Post> posts = postMapper.queryByUserId(id);
        if(posts != null)
        {
            //查询成功
            //跳转到我的发布页面
            return ServerResponse.createBySuccess(posts);
        }
        else
        {
            //查询失败
            return ServerResponse.createByErrorMessage("查询失败, 请重新查询");
        }
    }

    /**
     * 根据postId查询
     * @param info
     * @return
     */
    @Override
    public ServerResponse queryById(Post info)
    {
        Post post = postMapper.selectByPrimaryKey(info.getId());
        if(post != null)
        {
            //查询成功
            //跳转到我的发布页面
            return ServerResponse.createBySuccess(post);
        }
        else
        {
            //查询失败
            return ServerResponse.createByErrorMessage("查询失败, 请重新查询");
        }
    }

    @Override
    public ServerResponse add(Post post)
    {
        return null;
    }
}
