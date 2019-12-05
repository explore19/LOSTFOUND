package com.lost_found.interceptor;

import com.lost_found.common.Const;
import com.lost_found.common.ServerResponse;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginHandlerInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {

        HttpSession session = request.getSession(false);

        if (session == null)
        {
            PrintWriter out = response.getWriter();
            out.write("403");
            return false;
        }

        String role = session.getAttribute("role").toString();

        if (Const.USER.equals(role))
        {
            //未登录, 返回到登陆页面
            request.setAttribute("msg", "没有权限, 请先登录");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }
        else
        {
            //放行
            return true;
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
