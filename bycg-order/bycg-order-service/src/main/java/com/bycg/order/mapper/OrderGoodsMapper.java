package com.bycg.order.mapper;

import com.bycg.order.pojo.OrderGoods;

import java.util.List;

/**
 * @Description: 订单商品操作类
 * @className: OrderGoodsMapper
 * @Author: Mirror
 * @CreateDate: 2020/5/14 16:07
 **/
public interface OrderGoodsMapper {

    List<OrderGoods> querOrderGoodsById(Long orderId);
}
