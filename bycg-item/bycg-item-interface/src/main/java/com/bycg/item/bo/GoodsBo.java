package com.bycg.item.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 为完成商品列表查询
 * @className: GoodsBo
 * @Author: Mirror
 * @CreateDate: 2020/5/11 12:26
 **/
@Data
public class GoodsBo {

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
     * 商品主图
     */
    private String goodsCoverImg;

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
     * 商品上架状态 0-下架 1-上架
     */
    private Boolean goodsSellStatus;
    /**
     * 商品添加时间
     */
    private Date createTime;
}
