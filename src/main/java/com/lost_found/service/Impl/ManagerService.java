package com.lost_found.service.Impl;


import com.lost_found.common.ServerResponse;
import com.lost_found.dao.*;
import com.lost_found.pojo.Manager;
import com.lost_found.pojo.RotationChart;
import com.lost_found.pojo.User;
import com.lost_found.service.IManagerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class ManagerService implements IManagerService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    PostMapper postMapper;

    @Autowired
    ReplyMapper replyMapper;

    @Autowired
    RotationChartMapper rotationChartMapper;

    @Autowired
    ManagerMapper managerMapper;


    @Override
    public ServerResponse forbidUser(Integer id){

        User user = userMapper.selectByPrimaryKey(id);

        user.setStatus(1);

        int  result = userMapper.updateByPrimaryKeySelective(user);

        if (result > 0){

            return ServerResponse.createBySuccessMessage("封禁成功！");

        }

        return ServerResponse.createBySuccessMessage("封禁失败！");

    }


    @Override
    public ServerResponse deleteUserPost(Integer id) {

        return postMapper.deleteByPrimaryKey(id)>0?

                ServerResponse.createBySuccessMessage("删除成功"):

                ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse deleteReply(Integer id){

        return replyMapper.deleteByPrimaryKey(id)>0?

                ServerResponse.createBySuccessMessage("删除成功"):

                ServerResponse.createByErrorMessage("删除失败");
    }

    @Override

    public ServerResponse uploadRotationChart(@NotNull RotationChart rotationChart){

        rotationChart.setUpdateTime(new Date());

        rotationChart.setCreatTime(new Date());

        return this.rotationChartMapper.updateByPrimaryKeySelective(rotationChart)>0?

                ServerResponse.createBySuccess("上传成功"):

                ServerResponse.createByErrorMessage("上传失败");
    }



    @Override
    public ServerResponse deleteRotationChar(Integer id){

        return this.rotationChartMapper.deleteByPrimaryKey(id)>0?

                ServerResponse.createBySuccessMessage("删除成功"):

                ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse setRotationCharPriority(Integer id,Integer priority){


        RotationChart rotationChart = rotationChartMapper.selectByPrimaryKey(id);

        rotationChart.setPriority(priority);

        return this.rotationChartMapper.updateByPrimaryKeySelective(rotationChart)>0?

                ServerResponse.createBySuccessMessage("修改成功"):

                ServerResponse.createByErrorMessage("修改失败");
    }

    /**
     * 根据管理员用户名去查询
     * @param username
     * @return
     */
    @Override
    public Manager queryByUsername(String username)
    {
        Manager manager = managerMapper.queryByUsername(username);
        return manager;
    }

    /**
     * 管理员登陆
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse login(String username, String password)
    {
        Manager manager = queryByUsername(username);
        String _password = manager.getPassword();
        if (manager != null && password.equals(_password))
        {
            return ServerResponse.createBySuccessMessage("登陆成功");
        }
        return ServerResponse.createByErrorMessage("用户名或密码错误");
    }
}
