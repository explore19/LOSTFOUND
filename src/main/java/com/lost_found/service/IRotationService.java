package com.lost_found.service;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.RotationChart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IRotationService
{

    /**
     * 上添加首页的轮播图到数据库
     * @param record 轮播图对象
     * @return
     */
    ServerResponse addRotaChart(RotationChart record);

    ServerResponse updateRotaChart(RotationChart record);


    /**
     * 根据id删除轮播图
     * @param id
     * @return
     */
    ServerResponse deleteRotaChart(Integer id);

    /**
     * 根据优先级高低查询一定数量轮播图
     * @param num
     * @return
     */
    ServerResponse<List<RotationChart>> findRotaChart(Integer num,String name);
}
