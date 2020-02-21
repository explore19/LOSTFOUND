package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.form.LoginForm;
import com.lost_found.form.QueryUserForm;
import com.lost_found.pojo.Manager;
import com.lost_found.pojo.RotationChart;
import com.lost_found.service.IManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.jetbrains.annotations.Contract;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@Api(tags="管理员功能")
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    IManagerService managerService;

    @Contract(pure = true)
    public ManagerController() {
    }


    @ApiOperation("管理员登陆")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "username", value = "管理员用户名", required = true, paramType = "query", dataType = "String", example = "1"),
            @ApiImplicitParam(name = "password", value = "管理员密码", required = true, paramType = "query", dataType = "String", example = "1")}
    )
    @PostMapping("/login")
    public ServerResponse managerLogin(@RequestBody LoginForm loginForm)
    {
        return managerService.login(loginForm.username, loginForm.password);
    }

    @ApiOperation("管理员登出")
    @GetMapping("/logout")
    public ServerResponse managerLogin()
    {
        return managerService.logout();
    }

    @ApiOperation("批量查询用户")
    @GetMapping("/queryUser")
    public ServerResponse queryUser( QueryUserForm queryUserForm)
    {
        return managerService.queryUser(queryUserForm);
    }

    @PutMapping("/forbid_user/{id}")
    @ApiOperation("冻结用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "int", example = "1")
    public  ServerResponse forbidUser(@PathVariable("id")Integer id){
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
