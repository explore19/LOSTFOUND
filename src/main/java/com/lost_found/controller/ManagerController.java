package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.RotationChart;

import com.lost_found.service.IManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags="管理员功能")
@Controller

@RestController
@RequestMapping("/manager")


public class ManagerController {
    @Autowired
    IManagerService managerService;

    public ManagerController() {
    }

    @RequestMapping("/forbid_user")
    @ApiOperation("冻结用户")
    @PutMapping
    public  ServerResponse forbidUser(Integer id){

        return managerService.forbidUser(id);
    }

    @DeleteMapping("/delete_user_post/{id}")
    @ApiOperation("删除用户贴子")
    @ApiImplicitParam(
            name = "id",
            value = "帖子的Id",
            required = true,
            paramType = "path",
            dataType = "Integer"
    )
    public ServerResponse deleteUserPost(@PathVariable("id") Integer id){

        return this.managerService.deleteUserPost(id);

    }

    @DeleteMapping("/delete_reply/{id}")
    @ApiOperation("删除用户帖子里的回复")
    @ApiImplicitParam(
            name = "id",
            value = "回复的Id",
            required = true,
            paramType = "path",
            dataType = "Integer"
    )
    public ServerResponse deleteReply(@PathVariable("id") Integer id){

        return this.managerService.deleteReply(id);
    }



    @PostMapping("/update_rotation_chart")
    @ApiOperation("更新轮播图")
    public ServerResponse uploadRotationChart(RotationChart rotationChart){
        return this.managerService.uploadRotationChart(rotationChart);
    }



    @DeleteMapping("/delete_rotation_chart/{id}")
    @ApiOperation("删除轮播图")
    @ApiImplicitParam(
            name = "id",
            value = "轮播图的ID",
            required = true,
            paramType = "path",
            dataType = "Integer"
    )
    public ServerResponse deleteRotationChar(@PathVariable("id") Integer id){

        return this.managerService.deleteRotationChar(id);
    }



    @RequestMapping("/set_rotation_chart_priority")
    @ApiOperation("设置轮播图的优先级")
    @ApiImplicitParam(
            name = "priority",
            value = "优先级的等级",
            required = true,
            paramType = "query",
            dataType = "Integer"
    )
    public ServerResponse setRotationCharPriority(Integer id, Integer priority){

        return this.managerService.setRotationCharPriority(id,priority);
    }




}
