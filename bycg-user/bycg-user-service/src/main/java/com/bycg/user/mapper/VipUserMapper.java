package com.bycg.user.mapper;

import com.bycg.user.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 商城会员用户操作类
 * @className: VipUserMapper
 * @Author: Mirror
 * @CreateDate: 2020/5/13 17:21
 **/
public interface VipUserMapper {

    /**
     * 查询会员用户数据（分页）
     * @param order
     * @param page
     * @param limit
     * @return
     */
    List<User> queryVipUserByPage(String order, Integer page, Integer limit);

    /**
     * 根据用户ID 修改用户状态
     * @param ids
     * @param lockStatus
     */
    void editLockStatus(@Param("ids") Long[] ids, @Param("lockStatus") Integer lockStatus);

    /**
     * 用户注册
     * @param user
     */
    void saveUser(User user);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User queryUserByUserName(String username);
}
