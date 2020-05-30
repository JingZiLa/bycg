package com.bycg.item.api;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.item.pojo.GoodsInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Description: 对外暴露的商品访问Api
 * @className: GoodsApi
 * @Author: Mirror
 * @CreateDate: 2020/5/10 17:23
 **/
@RequestMapping("goods")
public interface GoodsApi {

    @GetMapping("list")
    void queryGoodsByPage();

    /**
     * 根据商品ID 查询商品信息
     *
     * @param goodsId
     * @return
     */
    @GetMapping("queryGoodsById")
    GoodsInfo queryGoodsById(@RequestParam("goodsId") Long goodsId);


    /**
     * 根据条件搜索商品
     *
     * @param keyword
     * @param categoryId
     * @param configType
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("searchGoods")
    PageResult<List<Map<String, Object>>> searchGoodsByPage(@RequestParam(value = "keyword", required = false) String keyword,
                                                            @RequestParam(value = "categoryId", required = false) Long categoryId,
                                                            @RequestParam(value = "configType", required = false) Integer configType,
                                                            @RequestParam(value = "order", defaultValue = "desc") String order,
                                                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                            @RequestParam(value = "limit", defaultValue = "20") Integer limit);
}
