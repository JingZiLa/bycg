package com.bycg.cart.interceptor;

import com.bycg.auth.pojo.UserInfo;
import com.bycg.auth.utils.JwtUtils;
import com.bycg.cart.config.JwtProperties;
import com.bycg.conmmon.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 登陆拦截器
 * @className: LoginInterceptor
 * @Author: Mirror
 * @CreateDate: 2020/5/22 12:24
 **/
@Component
@EnableConfigurationProperties(JwtProperties.class)
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtProperties jwtProperties;

    private static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //获取cookie信息
        String token = CookieUtils.getCookieValue(request, jwtProperties.getCookieName());

        //进行解析token
        UserInfo userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        if (userInfo == null) {
            return false;
        }
        THREAD_LOCAL.set(userInfo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        THREAD_LOCAL.remove();
    }

    /**
     * 获取当前用户
     * @return
     */
    public static UserInfo getUserInfo() {
        return THREAD_LOCAL.get();
    }

}
