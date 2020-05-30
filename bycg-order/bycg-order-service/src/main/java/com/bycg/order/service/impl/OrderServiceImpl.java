package com.bycg.order.service.impl;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.order.mapper.OrderGoodsMapper;
import com.bycg.order.mapper.OrderMapper;
import com.bycg.order.pojo.Order;
import com.bycg.order.pojo.OrderGoods;
import com.bycg.order.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 订单业务实现类
 * @className: OrderServiceImpl
 * @Author: Mirror
 * @CreateDate: 2020/5/14 11:57
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Override
    public PageResult<Order> queryOrderByPage(String order, Integer page, Integer limit,Integer status) {

        //添加分页条件
        PageHelper.startPage(page,limit);

        if (status != null && !(status >= -3) && !(status <= 4) ) {
            status = null;
        }
         List<Order> orders = this.orderMapper.queryOrderByPage(order,page,limit,status);

        PageInfo<Order> pageInfo = new PageInfo<>(orders);

        //封装结果集并返回
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),orders,pageInfo.getPageNum(),pageInfo.getPageSize());
    }

    /**
     * 根据订单ID 查询订单商品
     * @param orderId
     * @return
     */
    @Override
    public List<OrderGoods> querOrderGoodsById(Long orderId) {
        return this.orderGoodsMapper.querOrderGoodsById(orderId);
    }

    /**
     * 根据订单ID 修改订单信息
     * @param order
     */
    @Override
    public void updateOrderByorderId(Order order) {
        order.setUpdateTime(new Date());
        this.orderMapper.updateOrderByorderId(order);
    }
    /**
     * 根据订单ID 修改订单状态（可批量修改）
     * @param ids
     * @return
     */
    @Override
    public void updateOrderStatus(Long[] ids, Integer status) {
        this.orderMapper.updateOrderStatus(ids,status);
    }
}
