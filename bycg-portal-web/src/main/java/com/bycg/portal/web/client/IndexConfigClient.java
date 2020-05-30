package com.bycg.portal.web.client;

import com.bycg.item.api.IndexConfigApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 远程调用首页配置API
 * @className: IndexConfigClient
 * @Author: Mirror
 * @CreateDate: 2020/5/16 17:45
 **/

@FeignClient(value = "item-service")
public interface IndexConfigClient extends IndexConfigApi {
}
