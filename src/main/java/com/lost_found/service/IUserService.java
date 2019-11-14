package com.lost_found.service;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.User;


public interface IUserService {

    ServerResponse login(String code);

    ServerResponse getUserInfo(Integer id);

    ServerResponse updateInfo(User user);

    ServerResponse deleteInfo(User user);

    ServerResponse searchInfo(Integer id);
}
