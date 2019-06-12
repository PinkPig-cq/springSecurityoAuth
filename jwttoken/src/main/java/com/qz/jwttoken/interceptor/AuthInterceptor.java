package com.qz.jwttoken.interceptor;

import com.qz.jwttoken.context.AuthSecurityContext;
import com.qz.jwttoken.context.HttpHandlerContext;
import com.qz.jwttoken.entity.Users;
import com.qz.jwttoken.utils.JwtTokenUtil;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限验证 拦截器   验证token
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpHandlerContext.setRequestContext(request);
        String token = request.getHeaders("token").toString();
        Users users = JwtTokenUtil.decodeToken(token);
        if (token == null || users == null) {
            response.setHeader("403","用户没有登录!");
            return false;
        }else {
            AuthSecurityContext.setUserContext(users);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {

    }
}
