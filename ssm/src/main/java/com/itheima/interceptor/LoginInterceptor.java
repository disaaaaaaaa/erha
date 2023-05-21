package com.itheima.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response, Object handler)throws Exception{
        String uri = request.getRequestURI();
        if(uri.indexOf("/Login") >= 0){
            return true;
        }
        if(uri.indexOf("/toRegister") >= 0){
            return true;
        }
        if(uri.indexOf("/Register") >= 0){
            return true;
        }
        HttpSession session = request.getSession();
        if(session.getAttribute("USER_SESSION") != null){
            return true;
        }
        request.setAttribute("msg","你还没有登陆,请先登录！！！");
        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
        return false;
    }

}
