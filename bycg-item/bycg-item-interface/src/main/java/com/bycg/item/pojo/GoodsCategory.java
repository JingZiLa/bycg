package com.bycg.item.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 商品分类信息实体类
 * @className: GoodsCategory
 * @Author: Mirror
 * @CreateDate: 2020/5/9 11:22
 **/
@Data
public class GoodsCategory {
	/**
	 * 类别id
	 */
	private Long categoryId;
	/**
	 * 类别级别
	 */
	private Byte categoryLevel;
	/**
	 * parentid
	 */
	private Long parentId;
	/**
	 * 类别名称
	 */
	private String categoryName;
	/**
	 * 类别排名
	 */
	private Integer categoryRank;
	/**
	 * 是否删除
	 */
	private Boolean isDeleted;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建用户
	 */
	private Integer createUser;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 *	更新用户
	 */
	private Integer updateUser;
}
