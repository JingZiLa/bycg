package com.bycg.cart.service;

import com.bycg.cart.pojo.CartItem;
import com.bycg.cart.vo.CartVo;

import java.util.List;
import java.util.Map;

/**
 * @Description: 购物车业务层接口
 * @className: CartService
 * @Author: Mirror
 * @CreateDate: 2020/5/22 12:23
 **/
public interface CartService {

    void addCart(CartItem cartItem);

    List<CartVo> queryAllCartsByUser();
}
