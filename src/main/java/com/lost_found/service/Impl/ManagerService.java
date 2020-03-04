package com.lost_found.service.Impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lost_found.common.Const;
import com.lost_found.common.ServerResponse;
import com.lost_found.dao.*;
import com.lost_found.form.QueryUserForm;
import com.lost_found.pojo.Manager;
import com.lost_found.pojo.RotationChart;
import com.lost_found.pojo.User;
import com.lost_found.service.IManagerService;
import com.lost_found.utils.ServletUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class ManagerService implements IManagerService
{
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

    /**
     * 管理员登陆
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse login(String username, String password)
    {
        Manager manager = queryByUsername(username);
        if (manager != null &&  manager.getPassword().equals(password))
        {
            HttpSession session = ServletUtils.getSession();
            session.setAttribute("role", Const.Manager);
            session.setAttribute("manageId", manager.getId());
            return ServerResponse.createBySuccessMessage("登陆成功");
        }
        return ServerResponse.createByErrorMessage("用户名或密码错误");
    }

    @Override
    public ServerResponse logout() {
        HttpSession session=ServletUtils.getSession();
        if(Const.Manager.equals(session.getAttribute("role"))){
            session.invalidate();

        }
        return ServerResponse.createBySuccessMessage("注销成功");
    }



    @Override
    public ServerResponse forbidUser(Integer id)
    {
        User user = userMapper.selectByPrimaryKey(id);
        String msg = "封禁成功!";
        if(user.getStatus()==1){
            user.setStatus(0);
            msg = "解禁成功!";
        }else {
            user.setStatus(1);
        }
        user.setUpdateTime(new Date());
        int result = userMapper.updateByPrimaryKeySelective(user);
        if (result > 0)
        {
            return ServerResponse.createBySuccessMessage(msg);
        }
        return ServerResponse.createBySuccessMessage("封禁失败！");
    }

    @Override
    public ServerResponse deleteUserPost(Integer id)
    {
        return postMapper.deleteByPrimaryKey(id) > 0 ?
                ServerResponse.createBySuccessMessage("删除成功") :
                ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse deleteReply(Integer id)
    {
        return replyMapper.deleteByPrimaryKey(id) > 0 ?
                ServerResponse.createBySuccessMessage("删除成功") :
                ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse uploadRotationChart(@NotNull RotationChart rotationChart){

        rotationChart.setUpdateTime(new Date());
        rotationChart.setCreateTime(new Date());
        return this.rotationChartMapper.updateByPrimaryKeySelective(rotationChart) > 0 ?
                ServerResponse.createBySuccess("上传成功") :
                ServerResponse.createByErrorMessage("上传失败");
    }

    @Override
    public ServerResponse deleteRotationChar(Integer id)
    {
        return this.rotationChartMapper.deleteByPrimaryKey(id) > 0 ?
                ServerResponse.createBySuccessMessage("删除成功") :
                ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse setRotationCharPriority(Integer id, Integer priority)
    {
        RotationChart rotationChart = rotationChartMapper.selectByPrimaryKey(id);
        rotationChart.setPriority(priority);
        return this.rotationChartMapper.updateByPrimaryKeySelective(rotationChart) > 0 ?
                ServerResponse.createBySuccessMessage("修改成功") :
                ServerResponse.createByErrorMessage("修改失败");
    }

    @Override
    public ServerResponse queryUser(QueryUserForm queryUserForm) {
        Page page= PageHelper.startPage(queryUserForm.getPage(), queryUserForm.getPageSize());
        List<User> postList = userMapper.queryByForm(queryUserForm);
        Map<String,Object> data=new HashMap<>();
        data.put("list",postList);
        data.put("total",(int)page.getTotal());
        data.put("page",queryUserForm.getPage());
        data.put("pageSize",queryUserForm.getPageSize());
        return ServerResponse.createBySuccess(data);
    }

    /**
     * 根据管理员用户名去查询
     *
     * @param username
     * @return
     */
    @Override
    public Manager queryByUsername(String username)
    {
        return managerMapper.queryByUsername(username);
    }

}
