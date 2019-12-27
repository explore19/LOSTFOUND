package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Praise;
import com.lost_found.service.IPraiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "点赞")
@RestController
@RequestMapping("/praise")
public class PraiseController
{
    @Autowired
    IPraiseService praiseService;

    @ApiOperation(value = "点赞")
    @PostMapping
    public ServerResponse praise(Integer postId )
    {
        return praiseService.praise(postId);
    }

}
