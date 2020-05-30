package com.bycg.cart.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 购物车商品信息实体类
 * @className: CartItem
 * @Author: Mirror
 * @CreateDate: 2020/5/9 11:18
 **/
@Data
public class CartItem {
	/**
	 * 购物项主键id
	 */
	private Long cartItemId;
	/**
	 * 购物项主键id
	 */
	private Long userId;
	/**
	 * 关联商品id
	 */
	private Long goodsId;
	/**
	 * 数量(最大为5)
	 */
	private Integer goodsCount;
	/**
	 * 删除标识字段(0-未删除 1-已删除)
	 */
	private Boolean isDeleted;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最新修改时间
	 */
	private Date updateTime;

}
