package com.bycg.order.mapper;

import com.bycg.order.pojo.Order;

import java.util.List;

/**
 * @Description: 订单操作实体类
 * @className: OrderMapper
 * @Author: Mirror
 * @CreateDate: 2020/5/14 11:59
 **/
public interface OrderMapper {

    /**
     * 根据条件查询订单
     * @param order
     * @param page
     * @param limit
     * @param status
     * @return
     */
    List<Order> queryOrderByPage(String order, Integer page, Integer limit, Integer status);

    /**
     * 根据订单ID 修改订单信息
     * @param order
     */
    void updateOrderByorderId(Order order);

    /**
     * 根据订单ID 修改订单状态（可批量修改）
     * @param ids
     * @return
     */
    void updateOrderStatus(Long[] ids, Integer status);
}
