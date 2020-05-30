package com.bycg.cart.client;

import com.bycg.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;
/**
 * @Description: 远程调用商品接口
 * @className: GoodsClient
 * @Author: Mirror
 * @CreateDate: 2020/5/22 12:00
 **/
@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi {
}
