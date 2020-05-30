package com.bycg.item.service;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.item.bo.GoodsBo;
import com.bycg.item.pojo.GoodsInfo;

import java.util.List;
import java.util.Map;

/**
 * @Description: 商品操作业务层接口
 * @className: GoodsService
 * @Author: Mirror
 * @CreateDate: 2020/5/10 18:32
 **/
public interface GoodsService {

    PageResult<GoodsBo> queryGoodsByPage(String order, Integer page, Integer limit);

    void editStatus(Long[] ids, Integer statusNum);

    GoodsInfo queryGoodsById(Long goodsId);

    PageResult<List<Map<String, Object>>> searchGoodsByPage(String order,String keyword, Long categoryId, Integer configType, Integer page, Integer limit);
}
