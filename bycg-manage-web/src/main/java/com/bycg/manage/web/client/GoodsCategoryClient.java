package com.bycg.manage.web.client;

import com.bycg.item.api.GoodsCategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 远程调用GoodsCategoryApi接口
 * @className: GoodsCategoryClient
 * @Author: Mirror
 * @CreateDate: 2020/5/12 16:04
 **/
@FeignClient(value = "item-service")
public interface GoodsCategoryClient extends GoodsCategoryApi {
}
