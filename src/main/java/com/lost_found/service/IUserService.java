package com.lost_found.service;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Post;
import com.lost_found.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;


public interface IUserService {

    ServerResponse login(String code);

    ServerResponse getUserInfo();

    ServerResponse updateInfo(User user);

    ServerResponse deleteInfo(User user);

    ServerResponse searchInfo(Integer id);

    /**
     * 根据userId查询用户所有帖子
     * @return
     */
    ServerResponse<List<Post>> queryByUserId();

//    ServerResponse uploadUserImg(HttpServletRequest request, MultipartFile file) throws IOException;
}
