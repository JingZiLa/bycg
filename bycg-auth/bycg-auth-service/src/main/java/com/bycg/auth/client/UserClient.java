package com.bycg.auth.client;

import com.bycg.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 远程调用用户Api接口
 * @className: UserClient
 * @Author: Mirror
 * @CreateDate: 2020/5/21 16:13
 **/
@FeignClient(value = "user-service")
public interface UserClient extends UserApi {
}
