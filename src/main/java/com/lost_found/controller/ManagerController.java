package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Post;
import com.lost_found.pojo.User;
import com.lost_found.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    IManagerService managerService;

    @PostMapping("/forbid_user")
    public  ServerResponse forbidUser(@RequestBody User user){
        return managerService.forbidUser(user);
    }

    @DeleteMapping("/delete_post/{id}")
    public ServerResponse deleteUserPost(@PathVariable("id") Integer id){
        //        return managerService.deleteUserPost(post);
        return null;
    }


}
