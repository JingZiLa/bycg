package com.bycg.portal.web.client;

import com.bycg.cart.api.CartApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 远程访问购物车接口
 * @className: CartClient
 * @Author: Mirror
 * @CreateDate: 2020/5/22 18:39
 **/
@FeignClient(value = "cart-service")
public interface CartClient extends CartApi {
}
