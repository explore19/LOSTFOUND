package com.lost_found.dao;

import com.lost_found.form.LoginForm;
import com.lost_found.pojo.Post;
import com.lost_found.pojo.User;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);  //有什么属性 就更新哪些属性

    int updateByPrimaryKey(User record);

    User login(String openId);

    List<Post> queryByUserId(Integer userId);
}