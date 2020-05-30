package com.bycg.user.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 管理员信息实体类
 * @className: AdminUser
 * @Author: Mirror
 * @CreateDate: 2020/5/9 11:08
 **/
@Data
public class AdminUser {

	/**
	 * 管理员id
	 */
	private Integer adminUserId;
	/**
	 * 管理员登陆名称
	 */
	private String loginUserName;
	/**
	 * 管理员登陆密码
	 */
	private String loginPassword;
	/**
	 * 管理员显示昵称
	 */
	private String nickName;
	/**
	 * 是否锁定 0未锁定 1已锁定无法登陆
	 */
	private Boolean locked;

	/**
	 * 创建时间
	 */
	private Date createTime;
}
