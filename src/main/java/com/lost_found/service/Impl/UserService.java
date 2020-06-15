package com.lost_found.service.Impl;

import com.lost_found.common.Const;
import com.lost_found.common.ServerResponse;
import com.lost_found.dao.PostMapper;
import com.lost_found.dao.PraiseMapper;
import com.lost_found.dao.ReplyMapper;
import com.lost_found.dao.UserMapper;
import com.lost_found.pojo.Post;
import com.lost_found.pojo.User;
import com.lost_found.service.IUserService;
import com.lost_found.utils.ServletUtils;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class UserService implements IUserService
{
    @Autowired
    UserMapper userMapper;
    @Autowired
    PostMapper postMapper;
    @Autowired
    ReplyMapper replyMapper;

    @Autowired
    PraiseMapper praiseMapper;

    @Override
    public ServerResponse login(String code) {

//        ServerResponse  result = getOpenId(code);
        ServerResponse  result = getOpenId(code);
        if(result.isSuccess()){
            String openId=result.getData().toString();
            User user = userMapper.login(openId);
            if (user == null)
            {
                if (!register(openId)){
                    return ServerResponse.createByErrorMessage("注册失败");
                }
                user = userMapper.login(openId);
            }
            if (user != null)
                {
                    if(user.getStatus().equals(Const.STATUS.PROHIBITION.getStatus())){
                        return ServerResponse.createByErrorMessage("账号已封禁");
                    }
                    //获取要返回的sessionId
                    HttpSession session = ServletUtils.getSession();
                    session.setAttribute("role", Const.USER);
                    session.setAttribute("userId", user.getId());
                    user.setCurrentTime(new Date());
                    userMapper.updateByPrimaryKeySelective(user);
                    return ServerResponse.createBySuccessMessage("登陆成功");
                }

        }
        return ServerResponse.createByErrorMessage("服务繁忙,请稍后再试");
    }


    @Override
    public ServerResponse getUserInfo()
    {
        Integer userId = Integer.valueOf(ServletUtils.getSession().getAttribute("userId").toString());
        User user = userMapper.selectByPrimaryKey(userId);
        if (null != user)
        {
            user.setOpenId("");
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("获取用户信息失败");
    }

    @Override

    public ServerResponse  updateInfo(User user){     //测试用的方法
        Integer userId = Integer.valueOf(ServletUtils.getSession().getAttribute("userId").toString());
        user.setId(userId);
        int  result = userMapper.updateByPrimaryKeySelective(user);
        if (result > 0){
            return ServerResponse.createBySuccessMessage("修改成功！");
        }
        return ServerResponse.createBySuccessMessage("修改失败！");
    }

    @Override
    public ServerResponse deleteInfo(User user)
    {  //删除功能的方法
        int result = userMapper.deleteByPrimaryKey(user.getId());
        if (result > 0)
        {
            return ServerResponse.createBySuccessMessage("删除成功！");
        }
        return ServerResponse.createBySuccessMessage("删除失败！");
    }

    @Override
    public ServerResponse searchInfo(Integer id)
    {  //查找

        return ServerResponse.createBySuccessMessage("查找成功");
    }


    private boolean register(String openId)
    {
        User user = new User();
        user.setOpenId(openId);
        user.setStatus(3);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int count = userMapper.insert(user);
        return count > 0;
    }


    private ServerResponse getOpenId(String code)
    {
        String open_id = "";
        HttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxf24110f2ea791989&secret" +
                "=c951680bb740f50168b072d3263d669d&js_code=" + code + "&grant_type=authorization_code";
        try
        {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1200).setSocketTimeout(1200).build();
            httpGet.setConfig(requestConfig);
            HttpResponse httpResponse = null;
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null && httpResponse.getStatusLine() != null)
            {
                String content = "";
                if (httpResponse.getEntity() != null)
                {
                    content = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                    JSONObject json = new JSONObject(content);
                    open_id = (String) json.get("openid");
                    return ServerResponse.createBySuccess(open_id);
                }
            }
        } catch (URISyntaxException e)
        {
            return ServerResponse.createByErrorMessage("发生异常错误");
        } catch (ClientProtocolException e)
        {
            return ServerResponse.createByErrorMessage("发生异常错误");
        } catch (IOException e)
        {
            return ServerResponse.createByErrorMessage("发生异常错误");
        }
        return ServerResponse.createByErrorMessage("发生异常错误");
    }

    /**
     * 根据用户id来查询其全部发布
     *
     * @return
     */
    @Override
    public ServerResponse queryByUserId()
    {
        Integer userId = Integer.valueOf(ServletUtils.getSession().getAttribute("userId").toString());
        List<Map<String, Object>> allData = new ArrayList<>();
        List<Post> postList =postMapper.queryByUserId(userId);
        for (Post post : postList)
        {
            User user = userMapper.selectByPrimaryKey(post.getUserId());
            Integer replyNumber = Optional.ofNullable(replyMapper.getReplyNumber(post.getId()))
                    .orElseGet(() -> 0);
            Integer praiseNumber = Optional.ofNullable(praiseMapper.getPraiseNumber(post.getId()))
                    .orElseGet(() -> 0);
            if (user != null)
            {
                Map<String, Object> data = new HashMap<>();
                data.put("nickName", user.getNickName());
                data.put("headPortrait", user.getHeadPortrait());
                data.put("post", post);
                data.put("replyNumber", replyNumber);
                data.put("praiseNumber", praiseNumber);
                allData.add(data);
            }
        }
        return ServerResponse.createBySuccess(allData);

    }

//    /**
//     * 上传用户头像
//     */
//    @Override
//    public ServerResponse uploadUserImg(HttpServletRequest request, MultipartFile file) throws IOException
//    {
//        request.setCharacterEncoding("utf-8");
//
//        return UploadImgsUtil.uploadImg(request, file);
//    }

}
