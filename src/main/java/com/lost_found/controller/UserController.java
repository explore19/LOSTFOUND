package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.User;
import com.lost_found.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    IUserService userService;

    @ApiOperation(value = "用户登录")
    @ApiImplicitParam(name = "code", value = "登录验证码", required = true, paramType = "query", dataType = "String", example = "1")
    @PostMapping("/login")
    public ServerResponse login(@RequestParam("code") String code)
    {
        return userService.login(code);
    }

    @ApiOperation(value = "查询用户")
    @ApiImplicitParam(name="id",value="用户Id",required=true,paramType="query",dataType = "int",example = "1")
    @GetMapping("/get_user_info")
    public ServerResponse getUserInfo(@PathVariable Integer id)
    {
        return userService.getUserInfo(id);
    }

    @ApiOperation(value = "更新用户")
    @PostMapping("/update_info")
    public ServerResponse updateInfo(@RequestBody User user)
    {
        return userService.updateInfo(user);
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/delete_info")   //删除用户信息的方法
    public ServerResponse deleteInfo(@RequestBody User user)
    {
        return userService.deleteInfo(user);
    }


//    @GetMapping("/register")
//    public ServerResponse register(String code){
//        return  userService.login(code);
//    }

//    /**
//     * 上传用户头像
//     */
//    @ApiOperation(value = "上传图片")
//    @PostMapping("/upload_user_img")
//    public ServerResponse uploadImg(HttpServletRequest request, MultipartFile file) throws IOException
//    {
//        return userService.uploadUserImg(request, file);
//    }


}
