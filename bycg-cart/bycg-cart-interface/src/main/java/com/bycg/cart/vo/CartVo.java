package com.bycg.cart.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @className:
 * @Author: Mirror
 * @CreateDate: 2020/5/23 18:16
 **/
@Data
public class CartVo {

    private Integer goodsCount;

    private Long cartItemId;

    private String goodsCoverImg;

    private String goodsName;

    private BigDecimal sellingPrice;

}
