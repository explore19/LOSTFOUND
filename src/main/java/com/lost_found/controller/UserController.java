package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.dao.UserMapper;
import com.lost_found.pojo.User;
import com.lost_found.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags="用户")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ServerResponse login(String code){
          return  userService.login(code);
    }

    @ApiOperation(value = "查询用户信息")
    @GetMapping("/get_user_info")
    public ServerResponse getUserInfo(){
        int id =1 ;
        return userService.getUserInfo(id);
    }

    @ApiOperation(value = "更新用户信息")
    @PostMapping("/update_info")
    public  ServerResponse updateInfo(@RequestBody User user){
        return userService.updateInfo(user);
    }

    @ApiOperation(value = "删除用户信息")
    @PostMapping("/delete_info")   //删除用户信息的方法
    public ServerResponse deleteInfo(@RequestBody User user){
        return userService.deleteInfo(user);
    }


//    @GetMapping("/register")
//    public ServerResponse register(String code){
//        return  userService.login(code);
//    }
}
