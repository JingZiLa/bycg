package com.bycg.cart.mapper;


import com.bycg.cart.pojo.CartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 购物车数据操作类
 * @className: CartItemMapper
 * @Author: Mirror
 * @CreateDate: 2020/5/22 17:32
 **/
public interface CartItemMapper {
    void saveCartItem(CartItem cartItem);

    List<CartItem> queryAllCartsByUser(@Param("uId") Long uId, @Param("goodsId") Long goodsId);

    void updateCartByCid(CartItem cartItem1);
}
