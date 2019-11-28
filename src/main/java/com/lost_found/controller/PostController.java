package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Post;
import com.lost_found.service.IPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
        return postService.remove(id);
    }

    /**
     * 更新帖子数据
     *
     * @param post
     * @return
     */
    @ApiOperation(value = "修改帖子")
    @PutMapping
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
    public ServerResponse<Post> getPost(@PathVariable Integer id)
    {
        return postService.queryById(id);
    }


//    暂定是否需要把查询用户所有帖子放在帖子控制层
//    /**
//     * 根据UserId查找发布信息
//     *
//     * @return
//     */
//        @ApiOperation(value = "获得用户所有帖子")
//    @GetMapping("/query_posts")
//    public ServerResponse<List<Post>> queryByUserId(Integer userId)
//    {
//        return postService.queryByUserId(userId);
//    }


//    /**
//     * 上传帖子图片
//     * @param files
//     * @return
//     */
//    @ApiOperation(value = "上传图片")
//    @PostMapping("/upload_img")
//    public ServerResponse<String[]> uploadImg(MultipartFile[] files) throws IOException
//    {
//        return postService.uploadImg(files);
//    }


}
