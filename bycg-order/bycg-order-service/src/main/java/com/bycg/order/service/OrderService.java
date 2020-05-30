package com.bycg.order.service;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.order.pojo.Order;
import com.bycg.order.pojo.OrderGoods;

import java.util.List;

/**
 * @Description: 订单业务接口
 * @className: OrderService
 * @Author: Mirror
 * @CreateDate: 2020/5/14 11:56
 **/
public interface OrderService {

    PageResult<Order> queryOrderByPage(String order, Integer page, Integer limit,Integer status);

    List<OrderGoods> querOrderGoodsById(Long orderId);

    void updateOrderByorderId(Order order);

    void updateOrderStatus(Long[] ids,Integer status);
}
