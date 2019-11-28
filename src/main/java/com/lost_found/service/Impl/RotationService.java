package com.lost_found.service.Impl;

import com.lost_found.common.ServerResponse;
import com.lost_found.dao.RotationChartMapper;
import com.lost_found.pojo.RotationChart;
import com.lost_found.service.IRotationService;
import com.lost_found.utils.FileUtil;
import com.lost_found.utils.UploadImgsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class RotationService implements IRotationService
{
    @Autowired
    RotationChartMapper rotationChartMapper;

    /**
     * 上添加首页的轮播图到数据库
     * @param record 轮播图对象
     * @return
     */
    @Override
    public ServerResponse addRotaChart(RotationChart record)
    {
        return rotationChartMapper.insert(record) > 0 ?
                ServerResponse.createBySuccessMessage("添加成功") :
                ServerResponse.createByErrorMessage("添加失败, 请重新尝试");
    }

//    /**
//     * 将轮播图存储到服务器上
//     * @return
//     */
//    @Override
//    public ServerResponse uploadRotaImg(HttpServletRequest request) throws IOException
//    {
//        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
//
//        //"file"是前端图片对应的name
//        MultipartFile multipartFile = req.getFile("file");
//
//        return UploadImgsUtil.uploadImg(request, multipartFile);
//    }

    /**
     * 根据id删除轮播图
     * @param id
     * @return
     */
    @Override
    public ServerResponse deleteRotaChart(Integer id)
    {
        return rotationChartMapper.deleteByPrimaryKey(id) > 0 ?
                ServerResponse.createBySuccessMessage("删除成功") :
                ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 根据优先级高低查询一定数量轮播图
     * @param num
     * @return
     */
    @Override
    public ServerResponse<List<RotationChart>> findRotaChart(Integer num)
    {
        int totalCount = rotationChartMapper.queryTotalCount();

        //如果输入的为空, 则给一个默认值
        if (num == null) num = totalCount;

        List<RotationChart> charts = rotationChartMapper.queryByPriority(num);
        for (RotationChart chart : charts)
        {
            System.out.println(chart);
        }
        if (charts != null)
        {
            return ServerResponse.createBySuccessMessage("查询成功!", charts);
        }
        return ServerResponse.createByErrorMessage("查询失败");
    }
}
