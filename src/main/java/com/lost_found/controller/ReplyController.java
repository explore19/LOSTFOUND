package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Post;
import com.lost_found.pojo.Reply;
import com.lost_found.service.IReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "回帖")
@RestController
@RequestMapping("/reply")
public class ReplyController
{
    @Autowired
    IReplyService replyService;

    /**
     * 根据帖子id来添加回复
     * @param reply
     * @param postId 帖子id
     * @return
     */
    @ApiOperation(value = "发表回复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "帖子Id", required = true, paramType = "query", dataType = "Integer")
    })
    @PostMapping
    public ServerResponse postReply(@RequestBody Reply reply, Integer postId)
    {
        return replyService.insert(reply, postId);
    }
}
