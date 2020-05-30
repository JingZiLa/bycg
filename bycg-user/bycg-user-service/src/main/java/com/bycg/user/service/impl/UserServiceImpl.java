package com.bycg.user.service.impl;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.conmmon.utils.MD5Utils;
import com.bycg.user.bo.UserBo;
import com.bycg.user.mapper.AdminUserMapper;
import com.bycg.user.mapper.VipUserMapper;
import com.bycg.user.pojo.AdminUser;
import com.bycg.user.pojo.User;
import com.bycg.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 用户业务实现类
 * @className: UserServiceImpl
 * @Author: Mirror
 * @CreateDate: 2020/5/13 17:20
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private VipUserMapper vipUserMapper;

    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * 查询用户（列表，分页）
     *
     * @param order
     * @param page
     * @param limit
     * @param isAU
     * @return
     */
    @Override
    public PageResult<UserBo> queryUserByPage(String order, Integer page, Integer limit, Boolean isAU) {
        //添加分页条件
        PageHelper.startPage(page, limit);
        List<Object> users = new ArrayList<>();
        //判断请求的用户类型
        if (!isAU) {
            //为商城会员用户
            users.addAll(this.vipUserMapper.queryVipUserByPage(order, page, limit));
        } else {
            //为商城管理用户
            users.addAll(this.adminUserMapper.queryAdminUserByPage(order, page, limit));
        }


        if (!CollectionUtils.isEmpty(users)) {

            List<UserBo> list = users.stream().map(user -> {
                UserBo userBo = new UserBo();
                if (isAU) {
                    //封装管理用户信息
                    AdminUser auser = (AdminUser) user;
                    userBo.setUserId(auser.getAdminUserId().longValue());
                    userBo.setCreateTime(auser.getCreateTime());
                    userBo.setLockedFlag(auser.getLocked());
                    userBo.setLoginName(auser.getLoginUserName());
                    userBo.setNickName(auser.getNickName());
                    userBo.setIsDeleted(auser.getLocked());
                } else {
                    //封装会员用户信息
                    User vuser = (User) user;
                    BeanUtils.copyProperties(vuser, userBo);
                }
                return userBo;
            }).collect(Collectors.toList());

            PageInfo<Object> pageInfo = new PageInfo<>(users);

            //封装结果集并返回
            return new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(), list, pageInfo.getPageNum(), pageInfo.getPageSize());
        }

        return null;
    }

    /**
     * 根据用户ID 修改用户状态
     *
     * @param ids
     * @param lockStatus
     * @param isAU
     */
    @Override
    public void editLockStatus(Long[] ids, Integer lockStatus, Boolean isAU) {
        if (isAU) {
            this.adminUserMapper.editLockStatus(ids, lockStatus);
        } else {
            this.vipUserMapper.editLockStatus(ids, lockStatus);
        }
    }

    /**
     * 用户注册
     * @param user
     */
    @Override
    public void userRegister(User user) {
        if (user.getLoginName() != null &&  user.getLoginName() != "" && user.getPasswordMd5() != null && user.getPasswordMd5() != ""){
            user.setPasswordMd5(MD5Utils.md5(user.getPasswordMd5()));
            user.setAddress("无");
            user.setCreateTime(new Date());
            user.setIsDeleted(false);
            user.setLockedFlag(false);
            user.setIntroduceSign("该用户有点懒，还没有发表过个性签名^_^");
            user.setNickName(user.getLoginName());
            user.setUserId(null);
            this.vipUserMapper.saveUser(user);
        }
    }

    /**
     * 根据用户名密码查询用户信息
     * @param username
     * @param password
     * @return
     */
    @Override
    public User queryUser(String username, String password) {

        //根据用户名查询用户信息
        User user = this.vipUserMapper.queryUserByUserName(username);

        if (user !=  null) {
            String pwd = MD5Utils.md5(password);

            if (StringUtils.equals(user.getPasswordMd5(),pwd)){
                return user;
            }
        }
        return null;
    }
}
