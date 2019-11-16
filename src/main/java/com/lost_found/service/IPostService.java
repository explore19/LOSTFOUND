package com.lost_found.service;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

public interface IPostService {

    ServerResponse add(Post post);

    /**
     * 发布寻物启事/拾物启示
     * @param info
     * @return
     */
    ServerResponse announceInfo(Post info);

    /**
     * 根据id删除发布信息
     * @param info
     * @return
     */
    ServerResponse delAnnounceInfo(Post info);

    /**
     * 根据Post对象来修改信息
     * @param info
     * @return
     */
    ServerResponse updateAnnounceInfo(Post info);


    /**
     * 根据用户id来查询其全部发布
     * @param id
     * @return
     */
    ServerResponse queryByUserId(Integer id);

    /**
     * 根据postId查询
     * @param info
     * @return
     */
    ServerResponse queryById(Post info);
}
