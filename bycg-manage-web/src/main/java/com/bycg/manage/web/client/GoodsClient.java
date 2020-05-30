package com.bycg.manage.web.client;

import com.bycg.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 远程调用goodsWeb接口
 * @className: GoodsApi
 * @Author: Mirror
 * @CreateDate: 2020/5/11 15:37
 **/
@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi {

}
