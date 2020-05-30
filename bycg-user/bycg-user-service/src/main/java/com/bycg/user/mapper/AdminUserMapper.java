package com.bycg.user.mapper;


import com.bycg.user.pojo.AdminUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 后台管理用户操作类
 * @className: AdminUser
 * @Author: Mirror
 * @CreateDate: 2020/5/13 17:21
 **/
public interface AdminUserMapper {


    List<AdminUser> queryAdminUserByPage (String order, Integer page, Integer limit);

    /**
     * 根据用户ID 修改用户状态
     * @param ids
     * @param lockStatus
     */
    void editLockStatus(@Param("ids") Long[] ids, @Param("lockStatus") Integer lockStatus);
}
