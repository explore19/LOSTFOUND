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
        if (session == null)
        {
            response.getWriter().write(mapper.writeValueAsString(ServerResponse.createByErrorCodeMessage(500, "网络错误, 请重试")));
            return false;
        }
        String uri = request.getRequestURI();

        //如果访问登录或者静态资源, 直接放行
        if (uri.contains("/login") || uri.contains("/static") || uri.contains("/img")) return true;

        String role = session.getAttribute("role").toString();

        if (Const.USER.equals(role))
        {
            if (uri.contains("/user") || uri.contains("/post") || uri.contains("/reply") || uri.contains("/upload"))
            {
                return true;
            }
            response.getWriter().write(mapper.writeValueAsString(ServerResponse.createByErrorCodeMessage(403, "没有权限")));
            return false;
        }
        else if (Const.Manager.equals(role))
        {
            return true;
        }
        else
        {
            //未登录
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
