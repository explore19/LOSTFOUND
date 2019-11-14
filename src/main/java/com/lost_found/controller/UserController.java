package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.dao.UserMapper;
import com.lost_found.pojo.User;
import com.lost_found.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public ServerResponse login(String code){
          return  userService.login(code);
    }

    @GetMapping("/get_user_info")
    public ServerResponse getUserInfo(){
        int id =1 ;
        return userService.getUserInfo(id);
    }

    @PostMapping("/update_info")
    public  ServerResponse updateInfo(@RequestBody User user){
        return userService.updateInfo(user);
    }


    @PostMapping("/delete_info")   //删除用户信息的方法
    public ServerResponse deleteInfo(@RequestBody User user){
        return userService.deleteInfo(user);
    }


    @PostMapping("/search_info")
    public ServerResponse searchInfo(Integer id) {
        return userService.searchInfo(id);
    }


//    @GetMapping("/register")
//    public ServerResponse register(String code){
//        return  userService.login(code);
//    }
}
