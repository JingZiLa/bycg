package com.bycg.cart.service.impl;


import com.bycg.auth.pojo.UserInfo;
import com.bycg.cart.client.GoodsClient;
import com.bycg.cart.interceptor.LoginInterceptor;
import com.bycg.cart.mapper.CartItemMapper;
import com.bycg.cart.pojo.CartItem;
import com.bycg.cart.service.CartService;
import com.bycg.cart.vo.CartVo;
import com.bycg.item.pojo.GoodsInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;


/**
 * @Description: 购物车业务层实现
 * @className: CartServiceImpl
 * @Author: Mirror
 * @CreateDate: 2020/5/22 12:25
 **/
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private CartItemMapper cartMapper;
    /**
     * 添加商品到购物车
     * @param cartItem
     * @return
     */
    @Override
    public void addCart(CartItem cartItem) {
        //获取当前用户信息
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        List<CartItem> cartItems = this.cartMapper.queryAllCartsByUser(userInfo.getId(),cartItem.getGoodsId());
        if (CollectionUtils.isEmpty(cartItems)) {
            cartItem.setCreateTime(new Date());
            cartItem.setCartItemId(null);
            cartItem.setIsDeleted(false);
            cartItem.setUpdateTime(new Date());
            cartItem.setUserId(userInfo.getId());
            this.cartMapper.saveCartItem(cartItem);
        } else {
            CartItem cartItem1 = cartItems.get(0);
            cartItem1.setUpdateTime(new Date());
            cartItem1.setGoodsCount(cartItem1.getGoodsCount() + cartItem.getGoodsCount());
            this.cartMapper.updateCartByCid(cartItem1);
        }
    }
    /**
     * 查询用户购物车信息
     * @return
     */
    @Override
    public List<CartVo> queryAllCartsByUser() {
        List<CartVo> carts = new ArrayList<>();
        //获取当前用户信息
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        if (userInfo == null) {
            return null;
        }
       List<CartItem> cartItems = this.cartMapper.queryAllCartsByUser(userInfo.getId(),null);

       if (!CollectionUtils.isEmpty(cartItems)) {
            cartItems.forEach(cartItem -> {
                GoodsInfo goodsInfo = goodsClient.queryGoodsById(cartItem.getGoodsId());
                CartVo cartVo = new CartVo();
                BeanUtils.copyProperties(cartItem,cartVo);
                BeanUtils.copyProperties(goodsInfo,cartVo);
                carts.add(cartVo);
            });
       }
        return carts;
    }
}
