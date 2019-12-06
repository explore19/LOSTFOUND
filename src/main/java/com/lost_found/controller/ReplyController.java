package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Reply;
import com.lost_found.service.IReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
     * @return
     */
    @ApiOperation(value = "发表回复")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "postId", value = "帖子Id", required = true, paramType = "query", dataType = "Integer")
//    })
    @PostMapping("/announce_reply")
    public ServerResponse postReply(@RequestBody Reply reply)
    {
        return replyService.insert(reply);
    }

    /**
     * 根据回复id来删除回复
     * @param id
     * @return
     */
    @ApiOperation(value = "删除回复")
    @ApiImplicitParam(name = "replyId", value = "回复Id", required = true, paramType = "path", dataType = "int", example = "1")
    @DeleteMapping("/{id:\\d+}")
    public ServerResponse deleteReply(@PathVariable Integer id)
    {
        return replyService.delete(id);
    }


    /**
     * 修改回复
     * @param reply
     * @return
     */
    @ApiOperation(value = "修改回复")
    @PutMapping("/update_reply")
    public ServerResponse updateReply(@RequestBody Reply reply)
    {
        return replyService.update(reply);
    }

    /**
     * 根据用户Id查询用户的所有回复
     * @return
     */
    @ApiOperation(value = "查询用户的所有回复")
    @GetMapping("/select_user_reply")
    public ServerResponse queryByUserId()
    {
        return replyService.queryByUserId();
    }

}
