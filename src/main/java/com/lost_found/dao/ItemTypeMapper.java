package com.lost_found.dao;

import com.lost_found.pojo.ItemType;
import com.lost_found.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ItemTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemType record);

    int insertSelective(ItemType record);

    ItemType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemType record);

    int updateByPrimaryKey(ItemType record);

    List<ItemType> getAllType();
}