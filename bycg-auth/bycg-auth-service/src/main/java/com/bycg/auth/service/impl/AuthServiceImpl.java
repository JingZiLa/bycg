package com.bycg.auth.service.impl;

import com.bycg.auth.client.UserClient;
import com.bycg.auth.config.JwtProperties;
import com.bycg.auth.service.AuthService;
import com.bycg.auth.pojo.UserInfo;
import com.bycg.auth.utils.JwtUtils;
import com.bycg.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 授权中心业务接口实现
 * @className: AuthServiceImpl
 * @Author: Mirror
 * @CreateDate: 2020/5/21 16:30
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户登陆认证方法
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        //根据用户名查询用户
        User user = this.userClient.queryUser(username, password);
        //判断用户是否存在
        if (user == null) {
            return null;
        }

        try {
            //生成token
            return JwtUtils.generateToken(new UserInfo(user.getUserId(), user.getNickName()), jwtProperties.getPrivateKey(), jwtProperties.getExpire());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
