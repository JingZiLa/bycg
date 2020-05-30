package com.bycg.order.controller;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.order.pojo.Order;
import com.bycg.order.pojo.OrderGoods;
import com.bycg.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 订单控制层
 * @className: OrderController
 * @Author: Mirror
 * @CreateDate: 2020/5/14 11:52
 **/
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("list")
    public ResponseEntity<PageResult<Order>> queryGoodsByPage(
            @RequestParam(value = "order", defaultValue = "asc") String order,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit,
            @RequestParam(value = "status", required = false)Integer status) {

        //查询订单
        PageResult<Order> pageResult = this.orderService.queryOrderByPage(order,page,limit,status);
        //判断订单数据是否为空
        if (!CollectionUtils.isEmpty(pageResult.getList())) {

            return ResponseEntity.ok(pageResult);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * 根据订单ID 查询 订单商品
     * @param orderId
     * @return
     */
    @GetMapping("querOrderGoodsById/{orderId}")
    public ResponseEntity<List<OrderGoods>> querOrderGoodsById(@PathVariable("orderId") Long orderId) {
        List<OrderGoods> orderGoods = this.orderService.querOrderGoodsById(orderId);

        if (CollectionUtils.isEmpty(orderGoods)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(orderGoods);
    }

    /**
     * 根据订单ID 修改订单信息
     * @param order
     */
    @PostMapping("updateOrder")
    public ResponseEntity<Void> updateOrderByorderId(@RequestBody Order order) {
        this.orderService.updateOrderByorderId(order);
        return ResponseEntity.noContent().build();
    }

    /**
     * 根据订单ID 修改订单状态（可批量修改）
     * @param ids
     * @return
     */
    @PostMapping("updateOrderStatus/{status}")
    public ResponseEntity<Void> updateOrderStatus(@RequestBody Long [] ids,@PathVariable("status") Integer status) {

       if (ids == null || status == null || !(status >= -3 && status <= 4) ) {
           return ResponseEntity.badRequest().build();
       }

        this.orderService.updateOrderStatus(ids,status);
        return ResponseEntity.noContent().build();
    }
}
