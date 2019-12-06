package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Post;
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
import java.util.List;

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
    @GetMapping("/get_user_info")
    public ServerResponse getUserInfo()
    {
        return userService.getUserInfo();
    }

    @ApiOperation(value = "更新用户")
    @PostMapping("/update_info")
    public ServerResponse updateInfo(@RequestBody User user)
    {
        return userService.updateInfo(user);
    }

    /*应该写在管理员层?*/
    @ApiOperation(value = "删除用户")
    @PostMapping("/delete_info")   //删除用户信息的方法
    public ServerResponse deleteInfo(@RequestBody User user)
    {
        return userService.deleteInfo(user);
    }

    /**
     * 根据UserId查找发布信息
     *
     * @return
     */
    @ApiOperation(value = "获得用户所有帖子")
    @GetMapping("/query_posts")
    public ServerResponse<List<Post>> queryByUserId()
    {
        return userService.queryByUserId();
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
