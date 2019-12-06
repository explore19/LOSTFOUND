package com.lost_found.service.Impl;


import com.lost_found.common.Const;
import com.lost_found.common.ServerResponse;
import com.lost_found.dao.PostMapper;
import com.lost_found.dao.UserMapper;
import com.lost_found.form.QueryPostForm;
import com.lost_found.pojo.Post;
import com.lost_found.pojo.User;
import com.lost_found.service.IPostService;
import com.lost_found.utils.FileUtil;
import com.lost_found.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class PostService implements IPostService
{
    @Autowired
    PostMapper postMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public ServerResponse<String> save(Post post)
    {
        post.setStatus(Const.STATUS.NEED_EXAMINE_POST.getStatus());  //需要审核
        post.setCreateTime(new Date());
        post.setUpdateTime(new Date());
        post.setBrowsePoints(0);
        post.setPraisePoints(0);
        //将新添加的post信息加入到数据库中
        return postMapper.insert(post) > 0 ?
                ServerResponse.createBySuccessMessage("发布成功") :
                ServerResponse.createByErrorMessage("发布失败,请稍后再试");
    }

    @Override
    public ServerResponse<String> remove(Integer id)
    {
        Integer postUserId = postMapper.selectByPrimaryKey(id).getUserId();
        Integer userId = Integer.valueOf(ServletUtils.getSession().getAttribute("userId").toString());

//        if (postUserId == userId)
//        {
            return postMapper.deleteByPrimaryKey(id) > 0 ?
                    ServerResponse.createBySuccessMessage("删除成功") :
                    ServerResponse.createByErrorMessage("删除失败");
//        }
//        return ServerResponse.createByErrorCodeMessage(403, "没有权限");
    }

    @Override
    public ServerResponse<String> update(Post post)
    {
        Integer postUserId = post.getUserId();
        Integer userId = Integer.valueOf(ServletUtils.getSession().getAttribute("userId").toString());

//        if (postUserId == userId)
//        {
            post.setUpdateTime(new Date());
            return postMapper.updateByPrimaryKeySelective(post) > 0 ?
                    ServerResponse.createBySuccessMessage("更新帖子成功") :
                    ServerResponse.createByErrorMessage("删除失败");
//        }
//        return ServerResponse.createByErrorCodeMessage(403, "没有权限");
    }

    /**
     * 根据postId查询
     *
     * @param id
     * @return
     */
    @Override
    public ServerResponse queryById(Integer id)
    {
        Post post = postMapper.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(post.getUserId());
        if(user != null){
            Map<String,Object> data =new HashMap<>();
            data.put("nickName",user.getNickName());
            data.put("headPortrait",user.getHeadPortrait());
            data.put("post",post);
            return ServerResponse.createBySuccess(data);
        }
        return ServerResponse.createByErrorMessage("未找到帖子信息");
    }


    @Override
    public ServerResponse query(QueryPostForm queryPostForm) {
        List<Post> postList =postMapper.queryByForm(queryPostForm);
        List<Map<String,Object>> allData = new ArrayList<>();
        for (Post post:postList) {
            User user = userMapper.selectByPrimaryKey(post.getUserId());
            if(user != null){
                Map<String,Object> data =new HashMap<>();
                data.put("nickName",user.getNickName());
                data.put("headPortrait",user.getHeadPortrait());
                data.put("post",post);
                allData.add(data);
            }
        }
        return ServerResponse.createBySuccess(allData);
    }


//    /**
//     * 上传帖子的图片并将图片的地址存储到数据库
//     *
//     * @param files
//     * @return
//     */
//    @Override
//    public ServerResponse<String[]> uploadImg(MultipartFile[] files) throws IOException
//    {
//        String[] data = uploadImgsUtil.uploadImg(files);
//        if ("-1".equals(data[0]))
//        {
//            return ServerResponse.createByErrorMessage("上传图片失败, 图片大侠超过限制(2M)");
//        }
//        else if ("0".equals(data[0]))
//        {
//            return ServerResponse.createByErrorMessage("上传失败, 请重新上传");
//        }
//        else
//        {
//            return ServerResponse.createBySuccessMessage("上传成功", data);
//        }
//    }


}
