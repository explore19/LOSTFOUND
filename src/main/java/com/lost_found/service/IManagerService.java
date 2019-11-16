package com.lost_found.service;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Manager;
import com.lost_found.pojo.User;
import com.lost_found.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Mapper
public interface IManagerService {
    ServerResponse forbidUser(User user);

    ServerResponse deleteUserPost(Post post);

}


