package com.lost_found.service.Impl;

import com.lost_found.common.ResponseCode;
import com.lost_found.common.ServerResponse;
import com.lost_found.dao.UserMapper;
import com.lost_found.pojo.Post;
import com.lost_found.pojo.User;
import com.lost_found.service.IManagerService;
import com.lost_found.service.IUserService;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

@Service
@Transactional
public class ManagerService implements IManagerService {
    @Autowired
    UserMapper userMapper;


    @Override
    public ServerResponse forbidUser(User user){
        user.setStatus(1);
        int  result = userMapper.updateByPrimaryKeySelective(user);
        if (result > 0){
            return ServerResponse.createBySuccessMessage("封禁成功！");
        }
        return ServerResponse.createBySuccessMessage("封禁失败！");

    }

    @Override
    public ServerResponse deleteUserPost(Post post) {
        return null;
    }


}
