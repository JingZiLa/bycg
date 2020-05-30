package com.bycg.cart.controller;

import com.bycg.cart.pojo.CartItem;
import com.bycg.cart.service.CartService;
import com.bycg.cart.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description: 购物车控制层
 * @className: CartController
 * @Author: Mirror
 * @CreateDate: 2020/5/22 12:18
 **/
@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加商品到购物车
     * @param cartItem
     * @return
     */
    @PostMapping("addCart")
    public ResponseEntity<Void> addCart(@RequestBody CartItem cartItem) {
        this.cartService.addCart(cartItem);
        //201
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 查询用户购物车信息
     * @return
     */
    @GetMapping("queryAllCartsByUser")
    public ResponseEntity<List<Map<String,CartVo>>> queryAllCartsByUser() {
        List<CartVo> carts = this.cartService.queryAllCartsByUser();
        List<Map<String,CartVo>> mapList = new ArrayList<>();
        carts.forEach(cartVo -> {
            Map<String,CartVo> map = new HashMap<>();
            map.put("CartVo",cartVo);
            mapList.add(map);
        });
        if (CollectionUtils.isEmpty(carts)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapList);
    }
}
