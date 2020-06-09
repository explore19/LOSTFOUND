package com.lost_found.service.Impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lost_found.VO.ReplyTree;
import com.lost_found.common.Const;
import com.lost_found.common.ServerResponse;
import com.lost_found.dao.PostMapper;
import com.lost_found.dao.PraiseMapper;
import com.lost_found.dao.ReplyMapper;
import com.lost_found.dao.UserMapper;
import com.lost_found.form.QueryPostForm;
import com.lost_found.pojo.Post;
import com.lost_found.pojo.Praise;
import com.lost_found.pojo.Reply;
import com.lost_found.pojo.User;
import com.lost_found.service.IPostService;
import com.lost_found.utils.FileUtil;
import com.lost_found.utils.ServletUtils;
import com.lost_found.utils.TreeUtil;
import io.swagger.models.auth.In;
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

    @Autowired
    ReplyMapper replyMapper;

    @Autowired
    PraiseMapper praiseMapper;

    @Autowired
    PraiseService praiseService;

    private static int PASS = 0;

    private static  int UNPASS = 2;



    @Override
    public ServerResponse<String> save(Post post)
    {
        post.setStatus(Const.STATUS.NEED_EXAMINE_POST.getStatus());  //需要审核
        User user =userMapper.selectByPrimaryKey(ServletUtils.getUserId());
        if(user==null||user.getStatus()!=0){
            return   ServerResponse.createByErrorMessage("请先完善信息再发布帖子");
        }
        post.setUserId(ServletUtils.getUserId());
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
        return postMapper.deleteByPrimaryKey(id) > 0 ?
                ServerResponse.createBySuccessMessage("删除成功") :
                ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<String> update(Post post)
    {
        Integer postUserId = post.getUserId();
        Integer userId = Integer.valueOf(ServletUtils.getSession().getAttribute("userId").toString());

        post.setUpdateTime(new Date());
        return postMapper.updateByPrimaryKeySelective(post) > 0 ?
                ServerResponse.createBySuccessMessage("更新帖子成功") :
                ServerResponse.createByErrorMessage("删除失败");

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
        if(post==null){
            return ServerResponse.createByErrorMessage("未找到帖子信息");
        }
        post.setBrowsePoints(post.getBrowsePoints()+1);
        postMapper.updateByPrimaryKey(post);
        User user = userMapper.selectByPrimaryKey(post.getUserId());
        Integer replyNumber = Optional.ofNullable(replyMapper.getReplyNumber(post.getId()))
                .orElseGet(() -> 0);
        Integer praiseNumber = Optional.ofNullable(praiseMapper.getPraiseNumber(post.getId()))
                .orElseGet(() -> 0);
        if (user != null)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("nickName", user.getNickName());
            data.put("headPortrait", user.getHeadPortrait());
            data.put("post", post);
            data.put("praiseNumber", praiseNumber);
            data.put("replyNumber", replyNumber);
            data.put("isPraise", praiseService.checkPraise(post.getId()));
            return ServerResponse.createBySuccess(data);
        }
        return ServerResponse.createByErrorMessage("未找到帖子信息");
    }

    /**
     * 根据条件查询帖子
     * @param queryPostForm
     * @return
     */
    @Override
    public ServerResponse query(QueryPostForm queryPostForm)
    {
        Page page=PageHelper.startPage(queryPostForm.getPage(), queryPostForm.getPageSize());
        List<Post> postList = postMapper.queryByForm(queryPostForm);
        List<Map<String, Object>> allData = new ArrayList<>();
        for (Post post : postList)
        {
            User user = userMapper.selectByPrimaryKey(post.getUserId());
            Integer replyNumber = Optional.ofNullable(replyMapper.getReplyNumber(post.getId()))
                    .orElseGet(() -> 0);
            Integer praiseNumber = Optional.ofNullable(praiseMapper.getPraiseNumber(post.getId()))
                    .orElseGet(() -> 0);
            if (user != null)
            {
                Map<String, Object> data = new HashMap<>();
                data.put("nickName", user.getNickName());
                data.put("headPortrait", user.getHeadPortrait());
                data.put("post", post);
                data.put("replyNumber", replyNumber);
                data.put("praiseNumber", praiseNumber);
                if(Const.USER.equals(ServletUtils.getRole())){
                    data.put("isPraise", praiseService.checkPraise(post.getId()));
                }

                allData.add(data);
            }
        }
        HashMap<String,Object> list = new HashMap<>();
        list.put("data",allData);
        list.put("total",(int)page.getTotal());
        return ServerResponse.createBySuccess(list);
    }

    /**
     * 根据帖子id查询该帖子的所有回复
     *
     * @param postId
     * @return
     */
    @Override
    public ServerResponse<ReplyTree> getAllReply(Integer postId)
    {
        List<Reply> replyList = replyMapper.getAllReply(postId);
        ReplyTree replyTree = TreeUtil.getTree(replyList);
        return ServerResponse.createBySuccessMessage("查询成功", replyTree);
    }

    @Override
    public Integer getPostUserId(Integer postId) {
        return  postMapper.selectByPrimaryKey(postId).getUserId();
    }

    @Override
    /**
     * @Author: 0045M
     * @Description:
     * @Param [id, operation]
     * @return com.lost_found.common.ServerResponse
     * @Date: 13:35 2020/6/7
     */
    public ServerResponse AuditPost(Integer id, Integer operation) {
        Post post = postMapper.selectByPrimaryKey(id);
        if(operation.equals(PASS)||operation.equals(UNPASS)) {
            post.setStatus(operation);
            return postMapper.updateByPrimaryKeySelective(post) > 0 ?
                    ServerResponse.createBySuccessMessage("操作成功") :
                    ServerResponse.createByErrorMessage("操作失败");
        }else{
            return ServerResponse.createByErrorMessage("操作数错误！");
        }
    }



}
