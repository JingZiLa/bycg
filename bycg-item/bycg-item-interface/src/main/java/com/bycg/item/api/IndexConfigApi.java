package com.bycg.item.api;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.item.pojo.Carousel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: 首页配置Api
 * @className: IndexConfigApi
 * @Author: Mirror
 * @CreateDate: 2020/5/16 17:37
 **/
@RequestMapping("indexConfig")
public interface IndexConfigApi {


    /**
     * 根据条件查询首页配置（分页）
     * @param order
     * @param page
     * @param limit
     * @param configType
     * @return
     */
    @GetMapping("list")
    PageResult<Object> queryIndexConfigByPage(
            @RequestParam(value = "order", defaultValue = "asc") String order,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit,
            @RequestParam(value = "configType",defaultValue = "2") Integer configType
    );

    /**
     * 查询所有轮播图配置信息
     * @return
     */
    @GetMapping("queryAllCarousel")
    List<Carousel> queryAllCarousel(@RequestParam("isDel") Boolean isDel);
}
