package com.bycg.user.service;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.user.bo.UserBo;
import com.bycg.user.pojo.User;

/**
 * @Description: 用户业务接口
 * @className: UserService
 * @Author: Mirror
 * @CreateDate: 2020/5/13 17:19
 **/
public interface UserService {
    PageResult<UserBo> queryUserByPage(String order, Integer page, Integer limit, Boolean isAU);

    void editLockStatus(Long[] ids, Integer lockStatus, Boolean isAU);

    void userRegister(User user);

    User queryUser(String username, String password);
}
