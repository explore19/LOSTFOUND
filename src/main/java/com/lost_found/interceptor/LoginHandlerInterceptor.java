package com.lost_found.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lost_found.common.Const;
import com.lost_found.common.ServerResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginHandlerInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession(false);
        response.setCharacterEncoding("UTF-8");
        //如果访问登录或者静态资源, 直接放行
        String uri = request.getRequestURI();
        if(request.getMethod().equals("OPTIONS")){
            return  true;     //      不拦截OPTIONS类型的请求
        }
        if (uri.contains("/login")|| uri.contains("/manager/login") || uri.contains("/static") || uri.contains("/img")|| uri.contains("/error")) return true;
        if (session == null)
        {
            response.getWriter().write(mapper.writeValueAsString(ServerResponse.createByErrorCodeMessage(1, "网络错误, 请重试")));
            response.sendError(403);
            return false;
        }
        String role = session.getAttribute("role").toString();

        if (Const.USER.equals(role))
        {
            if (uri.contains("/user") || uri.contains("/post") || uri.contains("/reply") || uri.contains("/upload") || uri.contains("/praise")||uri.contains("/itemType/getAllType")||uri.contains("/rotation_chart/find_rotation"))
            {
                return true;
            }
            response.sendError(403);
            response.getWriter().write(mapper.writeValueAsString(ServerResponse.createByErrorCodeMessage(1, "没有权限")));
            return false;
        }
        else if (Const.Manager.equals(role))
        {
            return true;
        }
        else
        {
            //未登录
            response.sendError(403);
            response.getWriter().write(mapper.writeValueAsString(ServerResponse.needLogin()));
            return false;
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception
    {

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception
    {

    }
}
