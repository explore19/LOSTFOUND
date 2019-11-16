package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Post;
import com.lost_found.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announce")
public class PostController
{
    @Autowired
    IPostService postService;

    @PostMapping("/post")
    public ServerResponse add(Post post)
    {
        return postService.add(post);
    }

    /**
     * 根据post对象添加发布信息
     *
     * @param info
     * @return
     */
    @PostMapping("/announce_info")
    public ServerResponse announceInfo(@RequestBody Post info)
    {
        return postService.announceInfo(info);
    }


    /**
     * 根据Postid删除发布信息
     *
     * @param info
     * @return
     */
    @PostMapping("/delete_post")
    public ServerResponse delAnnounceInfo(@RequestBody Post info)
    {
        return postService.delAnnounceInfo(info);
    }

    /**
     * 根据Post对象来修改信息
     *
     * @param info
     * @return
     */
    @PostMapping("/update_post")
    public ServerResponse updateAnnounceInfo(@RequestBody Post info)
    {
        return postService.updateAnnounceInfo(info);
    }

    /**
     * 根据UserId查找发布信息
     *
     * @return
     */
    @GetMapping("/query_posts")
    public ServerResponse queryByUserId(Integer userId)
    {
        return postService.queryByUserId(userId);
    }

    @GetMapping("/query_post")
    public ServerResponse queryById(@RequestBody Post info)
    {
        return postService.queryById(info);
    }
}
