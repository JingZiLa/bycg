package com.bycg.user.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;


/**
 * @Description: 商城用户信息实体类
 * @className: User
 * @Author: Mirror
 * @CreateDate: 2020/5/9 11:37
 **/
@Data
public class User {
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
    @Length(min = 4,max = 30,message = "用户名必需在4~30位之间！")
    private String loginName;
    /**
     *  MD5加密后的密码
     */
    @Length(min = 6,max = 30,message = "密码必需在4~30位之间！")
    @JsonIgnore //对象序列化为json字符串时忽略该属性
    private String passwordMd5;
    /**
     *  个性签名
     */
    private String introduceSign;
    /**
     *收货地址
     */
    private String address;
    /**
     * 注销标识字段(0-正常 1-已注销)
     */
    private Boolean isDeleted;
    /**
     * 锁定标识字段(0-未锁定 1-已锁定)
     */
    private Boolean lockedFlag;
    /**
     * 注册时间
     */
    private Date createTime;

}
