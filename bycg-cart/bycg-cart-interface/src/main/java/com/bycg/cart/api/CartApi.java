package com.bycg.cart.api;

import com.bycg.cart.vo.CartVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Description: 对外暴露的购物车访问接口
 * @className: CartApi
 * @Author: Mirror
 * @CreateDate: 2020/5/22 18:20
 **/
@Controller
public interface CartApi {
    /**
     * 查询用户购物车信息
     * @return
     */
    @GetMapping("queryAllCartsByUser")
    List<CartVo> queryAllCartsByUser();
}
