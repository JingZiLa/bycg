package com.bycg.portal.web.client;

import com.bycg.item.api.GoodsCategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 远程调用分类api接口
 * @className: GoodsCategoryClient
 * @Author: Mirror
 * @CreateDate: 2020/5/16 16:56
 **/
@FeignClient(value = "item-service")
public interface GoodsCategoryClient extends GoodsCategoryApi {
}

