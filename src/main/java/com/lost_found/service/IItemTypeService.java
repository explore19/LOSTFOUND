package com.lost_found.service;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.ItemType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

public interface IItemTypeService {

    ServerResponse getType(Integer id);

    ServerResponse getAllType();

    ServerResponse addType(ItemType type);

    ServerResponse removeType(Integer id);

    ServerResponse updateType(ItemType type);



}
