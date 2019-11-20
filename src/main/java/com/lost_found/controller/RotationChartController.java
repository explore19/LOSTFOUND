package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.RotationChart;
import com.lost_found.service.IRotationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Api(tags = "首页轮播图")
@RestController
@RequestMapping("/rotation_chart")
public class RotationChartController
{
    @Autowired
    IRotationService rotationService;

    /**
     * 上传首页的轮播图
     * @param record 轮播图对象
     * @return
     */
    @ApiOperation(value = "添加轮播图")
    @PostMapping("/add_rotation_chart")
    public ServerResponse addRotaChart(@RequestBody RotationChart record)
    {
        return rotationService.addRotaChart(record);
    }

    /**
     * 将轮播图存储到服务器上
     * 前端循环上传需要上传的图片
     * @param request
     * @return
     */
    @ApiOperation(value = "上传轮播图到服务器")
    @PostMapping("/upload_img")
    public ServerResponse uploadRotaImg(HttpServletRequest request) throws IOException
    {
        return rotationService.uploadRotaImg(request);
    }

    /**
     * 根据id删除轮播图
     * @param id
     * @return
     */
    @ApiOperation(value = "删除轮播图")
    @ApiImplicitParam(name = "id", value = "轮播图Id", required = true, paramType = "path", dataType = "int", example = "1")
    @DeleteMapping("/delete_rotation_chart/{id:\\d+}")
    public ServerResponse deleteRotaChart(@PathVariable Integer id)
    {
        return rotationService.deleteRotaChart(id);
    }

    /**
     * 根据优先级高低查询一定数量轮播图
     * @param num
     * @return
     */
    @ApiOperation(value = "按照优先级查询轮播图")
    @ApiImplicitParam(name = "num", value = "要查询的数量", required = false, paramType = "query", dataType = "int", example = "1")
    @GetMapping("/find_rotation")
    public ServerResponse<List<RotationChart>> changeRotaChart(Integer num)
    {
        return rotationService.findRotaChart(num);
    }
}
