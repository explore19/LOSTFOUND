package com.lost_found.service;

import com.lost_found.common.ServerResponse;
import com.lost_found.form.QueryPostForm;
import com.lost_found.form.QueryUserForm;
import com.lost_found.pojo.Manager;
import com.lost_found.pojo.RotationChart;
import com.lost_found.pojo.User;
import com.lost_found.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

public interface IManagerService {
    ServerResponse forbidUser(Integer id);//封禁用户

    ServerResponse deleteUserPost(Integer id);//删除帖子

    ServerResponse deleteReply(Integer id);//删除帖子回复

    ServerResponse uploadRotationChart(RotationChart rotationChart);//上传轮播图

    ServerResponse deleteRotationChar(Integer id);//删除轮播图

    ServerResponse setRotationCharPriority(Integer id,Integer priority);//设置轮播图的优先级

    ServerResponse queryUser(QueryUserForm queryUserForm); //查询用户

    /**
     * 根据管理员用户名去查询
     * @param username
     * @return
     */
    Manager queryByUsername(String username);

    /**
     * 管理员登陆
     * @param username
     * @param password
     * @return
     */
    ServerResponse login(String username, String password);

    /**
     * 管理员登出
     * @return
     */
    ServerResponse logout();



}



