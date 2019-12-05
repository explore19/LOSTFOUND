package com.lost_found.service;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Manager;
import com.lost_found.pojo.RotationChart;
import com.lost_found.pojo.User;
import com.lost_found.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Mapper
public interface IManagerService {
    ServerResponse forbidUser(Integer id);//封禁用户

    ServerResponse deleteUserPost(Integer id);//删除帖子

    ServerResponse deleteReply(Integer id);//删除帖子回复

    ServerResponse uploadRotationChart(RotationChart rotationChart);//上传轮播图

    ServerResponse deleteRotationChar(Integer id);//删除轮播图

    ServerResponse setRotationCharPriority(Integer id,Integer priority);//设置轮播图的优先级



}



