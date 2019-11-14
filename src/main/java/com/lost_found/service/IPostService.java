package com.lost_found.service;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPostService {

    ServerResponse add(Post post);


}
