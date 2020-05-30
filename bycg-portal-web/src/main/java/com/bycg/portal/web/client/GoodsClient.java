package com.bycg.portal.web.client;

import com.bycg.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 远程调用GoodsAPI接口
 * @className: GoodsClient
 * @Author: Mirror
 * @CreateDate: 2020/5/16 19:10
 **/
@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi {
}
