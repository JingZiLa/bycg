package com.bycg.item.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 轮播图推荐商品信息实体类
 * @className: Carousel
 * @Author: Mirror
 * @CreateDate: 2020/5/9 11:10
 **/
@Data
public class Carousel {
	/**
	 * 首页轮播图主键id
	 */
	private Integer carouselId;
	/**
	 * url轮播图
	 */
	private String carouselUrl;
	/**
	 * redirect_url点击后的跳转地址(默认不跳转)
	 */
	private String redirectUrl;
	/**
	 * carousel_rank排序值(字段越大越靠前)
	 */
	private Integer carouselRank;
	/**
	 * is_deleted删除标识字段(0-未删除 1-已删除)
	 */
	private Boolean isDeleted;
	/**
	 * create_time创建时间
	 */
	private Date createTime;
	/**
	 * create_user创建者id
	 */
	private Integer createUser;
	/**
	 * update_time修改时间
	 */
	private Date updateTime;
	/**
	 * update_user修改者id
	 */
	private Integer updateUser;
}
