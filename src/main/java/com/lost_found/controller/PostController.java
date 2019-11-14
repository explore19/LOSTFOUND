package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.dao.PostMapper;
import com.lost_found.pojo.Post;
import com.lost_found.service.IPostService;
import com.lost_found.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    IPostService postService;

    @PostMapping("/announce_info")
    public ServerResponse announceInfo(Post info){
        return postService.announceInfo(info);
    }


}
