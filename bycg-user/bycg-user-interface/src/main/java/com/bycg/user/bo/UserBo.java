package com.bycg.user.bo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @className: UserBo
 * @Author: Mirror
 * @CreateDate: 2020/5/13 17:46
 **/
@Data
public class UserBo {

    /**
     *  用户主键id
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 登陆名称(默认为手机号)
     */
    private String loginName;

    /**
     * 锁定标识字段(0-未锁定 1-已锁定)
     */
    private Boolean lockedFlag;

    /**
     * 注销标识字段(0-正常 1-已注销)
     */
    private Boolean isDeleted;

    /**
     * 注册时间
     */
    private Date createTime;
}
