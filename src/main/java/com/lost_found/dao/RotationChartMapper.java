package com.lost_found.dao;

import com.lost_found.pojo.RotationChart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RotationChartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RotationChart record);

    int insertSelective(RotationChart record);

    RotationChart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RotationChart record);

    int updateByPrimaryKey(RotationChart record);

    List<RotationChart> queryByPriority(Integer num);

    int queryTotalCount();
}