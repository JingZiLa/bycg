package com.bycg.user.api;

import com.bycg.user.pojo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 用户API接口
 * @className: UserApi
 * @Author: Mirror
 * @CreateDate: 2020/5/21 16:00
 **/
public interface UserApi {

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/query")
     User queryUser(@RequestParam("username")String username, @RequestParam("password")String password);
}
