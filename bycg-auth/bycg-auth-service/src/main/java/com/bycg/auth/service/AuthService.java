package com.bycg.auth.service;

/**
 * @Description: 授权中心业务接口
 * @className: AuthService
 * @Author: Mirror
 * @CreateDate: 2020/5/21 16:28
 **/
public interface AuthService {
    String login(String username, String password);
}
