package com.lost_found.controller;

import com.lost_found.VO.ReplyTree;
import com.lost_found.common.ServerResponse;
import com.lost_found.form.QueryPostForm;
import com.lost_found.pojo.Post;
import com.lost_found.pojo.Reply;
import com.lost_found.service.IPostService;
import com.lost_found.utils.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Api(tags="帖子")
@RestController
@RequestMapping("/post")
public class PostController
{
    @Autowired
    IPostService postService;


    /**
     * 根据post对象添加发布信息
     *
     * @param post
     * @return
     */
    @ApiOperation(value = "添加帖子")
    @PostMapping
    public ServerResponse<String> savePost(@RequestBody Post post)
    {
        return postService.save(post);
    }

    /**
     * 根据id删除帖子
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除帖子")
    @ApiImplicitParam(name="id",value="帖子Id",required=true,paramType="path",dataType = "int",example = "1")
    @DeleteMapping("/{id:\\d+}")
    public ServerResponse<String> removePost(@PathVariable Integer id)
    {
        if(!ServletUtils.getUserId().equals(postService.getPostUserId(id))){
            return ServerResponse.createByErrorMessage("权限不足");
        }
        return postService.remove(id);
    }

    /**
     * 更新帖子数据
     *
     * @param post
     * @return
     */
    @ApiOperation(value = "修改帖子")
//    @PutMapping
    public ServerResponse<String> updatePost(@RequestBody Post post)
    {
        return postService.update(post);
    }

    /**
     * 根据帖子的Id获得帖子
     * @return
     */
    @ApiOperation(value = "获得单个帖子信息")
    @ApiImplicitParam(name="id",value="帖子Id",required=true,paramType="query",dataType = "int",example = "1")
    @GetMapping
    public ServerResponse getPost( Integer id)
    {
        return postService.queryById(id);
    }


    /**
     * 根据获得多个帖子
     * @return
     */
    @ApiOperation(value = "根据QueryPostForm查询获得一定数目帖子")
    @ApiImplicitParam(name="queryPostForm",value="查询表单",required=true,paramType="query")
    @GetMapping("/query")
    public ServerResponse  query(QueryPostForm queryPostForm)
    {
        return postService.query(queryPostForm);
    }

    /**
     * 根据帖子id查询该帖子的所有回复
     * @param postId
     * @return
     */
    @ApiOperation(value = "根据帖子id查询该帖子的所有回复")
    @ApiImplicitParam(name = "id", value = "帖子id", required = true, paramType = "query", dataType = "int", example = "1")
    @GetMapping("/query_all_reply")
    public ServerResponse<ReplyTree> queryAllReplyByPostId(Integer postId)
    {
        return postService.getAllReply(postId);
    }

}
