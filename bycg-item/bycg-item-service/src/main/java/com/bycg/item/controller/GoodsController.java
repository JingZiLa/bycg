package com.bycg.item.controller;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.item.bo.GoodsBo;
import com.bycg.item.pojo.GoodsInfo;
import com.bycg.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description: 商品Controller
 * @className: GoodsController
 * @Author: Mirror
 * @CreateDate: 2020/5/10 17:17
 **/
@Controller
@RequestMapping("goods")
public class GoodsController {


    @Autowired
    private GoodsService goodsService;

    /**
     * 查询商品列表（分页）
     *
     * @param order
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<PageResult<GoodsBo>> queryGoodsByPage(@RequestParam(value = "order", defaultValue = "asc") String order,
                                                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        //查询商品
        PageResult<GoodsBo> pageResult = this.goodsService.queryGoodsByPage(order, page, limit);
        //判断商品数据是否为空
        if (!CollectionUtils.isEmpty(pageResult.getList())) {
            return ResponseEntity.ok(pageResult);
        }
        return ResponseEntity.notFound().build();
    }


    /**
     * 上下架商品
     *
     * @param ids
     * @param statusNum
     * @return
     */
    @PutMapping("editStatus/{statusNum}")
    public ResponseEntity<Void> editStatus(@RequestBody Long[] ids, @PathVariable("statusNum") Integer statusNum) {
        this.goodsService.editStatus(ids, statusNum);
        return ResponseEntity.ok().build();
    }


    /**
     * 根据商品ID 查询商品信息
     *
     * @param goodsId
     * @return
     */
    @GetMapping("queryGoodsById")
    public ResponseEntity<GoodsInfo> queryGoodsById(@RequestParam("goodsId") Long goodsId) {
        GoodsInfo goodsInfo = this.goodsService.queryGoodsById(goodsId);
        if (goodsId == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(goodsInfo);
    }


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
    public ResponseEntity<PageResult<List<Map<String, Object>>>> searchGoodsByPage(@RequestParam(value = "keyword", required = false) String keyword,
                                                                                   @RequestParam(value = "categoryId", required = false) Long categoryId,
                                                                                   @RequestParam(value = "configType", required = false) Integer configType,
                                                                                   @RequestParam(value = "order", defaultValue = "desc") String order,
                                                                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                                   @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        PageResult<List<Map<String, Object>>> pageResult = this.goodsService.searchGoodsByPage(order, keyword, categoryId, configType, page, limit);

        if (pageResult == null || CollectionUtils.isEmpty(pageResult.getMapList())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pageResult);
    }

}
