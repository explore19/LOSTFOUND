package com.lost_found.service.Impl;

import com.lost_found.common.ServerResponse;
import com.lost_found.dao.UserMapper;
import com.lost_found.pojo.User;
import com.lost_found.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public ServerResponse login(String code) {
       //ServerResponse  result = getOpenId(code);
        ServerResponse  result = ServerResponse.createBySuccess("123");
        //System.out.println("dawdad");
        if(result.isSuccess()){
            String openId=result.getData().toString();
            if (userMapper.login(openId)>0){
                return ServerResponse.createBySuccessMessage("登陆成功");
            }
            if(register(openId)){
                return ServerResponse.createBySuccessMessage("登陆成功");
            }
        }
        //1.发送请求
        //2.判断微信服务器请求返回是否成功
        //3.若成功 获取用户的openId
        //4.登录检测
//        Integer count = userMapper.login(openId);
//        if (count>0){
//            return  ServerResponse.createBySuccessMessage("登录成功");
//        }
//        ServerResponse.createByErrorCodeMessage(100,"用户信息不完整");
        // 构造User对象
        // userMapper.insert(user)
//        return ServerResponse.createByErrorMessage("账号或密码错误");
        return  ServerResponse.createByErrorMessage("服务繁忙,请稍后再试");
    }



    @Override
    public ServerResponse getUserInfo(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if(null !=user){
            user.setOpenId("");
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("获取用户信息失败");
    }

    @Override
    public ServerResponse  updateInfo(User user){     //测试用的方法
            int  result = userMapper.updateByPrimaryKeySelective(user);
            if (result > 0){
                return ServerResponse.createBySuccessMessage("修改成功！");
            }
            return ServerResponse.createBySuccessMessage("修改失败！");
    }

    @Override
    public ServerResponse deleteInfo(User user) {  //删除功能的方法
        int result = userMapper.deleteByPrimaryKey(user.getId());
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("删除成功！");
        }
        return ServerResponse.createBySuccessMessage("删除失败！");
    }

    @Override
    public ServerResponse searchInfo(Integer id){  //查找
        int result = 0;

        return ServerResponse.createBySuccessMessage("查找成功");
    }

    private boolean register(String openId){
        User user = new User();
        user.setOpenId(openId);
        user.setStatus(2);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int count = userMapper.insert(user);
        return count > 0;
    }


    private ServerResponse getOpenId(String code){
        String open_id="";
        HttpClient httpClient = HttpClients.createDefault();
        String url="https://api.weixin.qq.com/sns/jscode2session?appid=wx7ea1bf8afa838fc9&secret=10048938ca183fdad2f72e0d0f586eb2&js_code="+code+"&grant_type=authorization_code";
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1200).setSocketTimeout(1200).build();
            httpGet.setConfig(requestConfig);
            HttpResponse httpResponse = null;
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null && httpResponse.getStatusLine() != null) {
                String content = "";
                if (httpResponse.getEntity() != null) {
                    content = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                    JSONObject json = new JSONObject(content);
                    open_id=(String) json.get("openid");
                    return  ServerResponse.createBySuccess(open_id);
                }
            }
        } catch (URISyntaxException e) {
            return ServerResponse.createByErrorMessage("发生异常错误");
        } catch (ClientProtocolException e) {
            return ServerResponse.createByErrorMessage("发生异常错误");
        } catch (IOException e) {
            return ServerResponse.createByErrorMessage("发生异常错误");
        }
        return ServerResponse.createByErrorMessage("发生异常错误");
    }
}
