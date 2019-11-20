package com.lost_found.service;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public interface IUserService {

    ServerResponse login(String code);

    ServerResponse getUserInfo(Integer id);

    ServerResponse updateInfo(User user);

    ServerResponse deleteInfo(User user);

    ServerResponse searchInfo(Integer id);

    ServerResponse uploadUserImg(HttpServletRequest request, MultipartFile file) throws IOException;
}
