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
        System.out.println(uri);
        if(request.getMethod().contains("OPTIONS")){
            return  true;     //      不拦截OPTIONS类型的请求
        }
        if (uri.contains("/user/login")||uri.contains("/logout")|| uri.contains("/manager/login") || uri.contains("/static") || uri.contains("/img")|| uri.contains("/error")) {
            return true;
        }

        //说明session过期了
        if (session == null)
        {
            System.out.println(request.getHeader("JSESSIONID"));
            response.sendError(Const.STATUS.OUTSESSION.getStatus());
            return false;
        }
        String role = session.getAttribute("role").toString();

        if (Const.USER.contains(role))
        {
            if (uri.contains("/user") || uri.contains("/post") || uri.contains("/reply") || uri.contains("/upload") || uri.contains("/praise")||uri.contains("/itemType/getAllType")||uri.contains("/rotation_chart/find_rotation"))
            {
                return true;
            }
            response.sendError(Const.STATUS.NOPERMISSION.getStatus());
            response.getWriter().write(mapper.writeValueAsString(ServerResponse.createByErrorCodeMessage(1, "没有权限")));
            return false;
        }
        else if (Const.Manager.contains(role))
        {
            return true;
        }
        else
        {
            //未登录
            response.sendError(Const.STATUS.NEEDLOGIN.getStatus());
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
