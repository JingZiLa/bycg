package com.bycg.item.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 商品信息实体类
 * @className: GoodsInfo
 * @Author: Mirror
 * @CreateDate: 2020/5/9 11:30
 **/
@Data
public class GoodsInfo {


	/**
	 * 商品表主键id
	 */
	private Long goodsId;
	/**
	 * 商品名
	 */
	private String goodsName;
	/**
	 * 商品简介
	 */
	private String goodsIntro;
	/**
	 * 关联分类id
	 */
	private Long goodsCategoryId;
	/**
	 * 商品主图
	 */
	private String goodsCoverImg;
	/**
	 * 商品轮播图
	 */
	private String goodsCarousel;
	/**
	 * 商品详情
	 */
	private String goodsDetailContent;
	/**
	 * 商品价格
	 */
	private Integer originalPrice;
	/**
	 * 商品实际售价
	 */
	private BigDecimal sellingPrice;
	/**
	 * 商品库存数量
	 */
	private Integer stockNum;
	/**
	 * 商品标签
	 */
	private String tag;
	/**
	 * 商品上架状态 0-下架 1-上架
	 */
	private Boolean goodsSellStatus;
	/**
	 * 添加者主键id
	 */
	private Integer createUser;
	/**
	 * 商品添加时间
	 */
	private Date createTime;
	/**
	 * 修改者主键id
	 */
	private Integer updateUser;
	/**
	 * 商品修改时间
	 */
	private Date updateTime;

}
