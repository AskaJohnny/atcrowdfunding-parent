package com.johnny.atcrowdfunding.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author johnny
 * @create 2018-07-18 下午1:45
 **/

public class LoginIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (StringUtils.isEmpty(request.getSession().getAttribute("user"))) {
            request.setAttribute("errorMsg", "请先登录用户名密码");
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }
        return true;
    }
}