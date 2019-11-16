package com.lost_found.service;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface IPostService {

    /**
     * 发布寻物启事/拾物启示
     * @param post
     * @return
     */
    ServerResponse save(Post post);

    /**
     * 根据id删除发布信息
     * @param id
     * @return
     */
    ServerResponse remove(Integer id);

    /**
     * 根据Post对象来修改信息
     * @param post
     * @return
     */
    ServerResponse update(Post post);


    /**
     * 根据postId查询
     * @param id
     * @return
     */
    ServerResponse<Post> queryById(Integer id);

    /**
     * 根据userId查询用户所有帖子
     * @param userId
     * @return
     */
    ServerResponse<List<Post>> queryByUserId(Integer userId);
}
