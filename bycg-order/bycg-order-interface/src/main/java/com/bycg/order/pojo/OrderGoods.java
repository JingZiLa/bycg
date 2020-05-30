package com.bycg.order.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 订单商品信息实体类
 * @className: OrderGoods
 * @Author: Mirror
 * @CreateDate: 2020/5/9 11:35
 **/
@Data
public class OrderGoods {
	/**
	 * 订单关联购物项主键id
	 */
	private Long orderItemId;
	/**
	 * 订单主键id
	 */
	private Long orderId;
	/**
	 * 关联商品id
	 */
	private Long goodsId;
	/**
	 * 下单时商品的名称(订单快照)
	 */
	private String goodsName;
	/**
	 * 下单时商品的主图(订单快照)
	 */
	private String goodsCoverImg;
	/**
	 * 下单时商品的价格(订单快照)
	 */
	private Integer sellingPrice;
	/**
	 * 数量(订单快照)
	 */
	private Integer goodsCount;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
