package com.bycg.item.service;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.item.pojo.GoodsCategory;

import java.util.List;
import java.util.Map;

/**
 * @Description: 商品分类业务层接口
 * @className: CategoryService
 * @Author: Mirror
 * @CreateDate: 2020/5/11 20:17
 **/
public interface CategoryService {
    PageResult<GoodsCategory> queryCategoryByPage(String order, Integer page, Integer limit, Integer categoryLevel, Integer parentId);

    void saveGoodsCategory(GoodsCategory goodsCategory);

    Boolean updateGoodsCategory(GoodsCategory goodsCategory);

    Map<String, List<GoodsCategory>> cascadeFindCategorysById(Long categoryId);

    Map<String, List<GoodsCategory>> findAllCategory();

    List<GoodsCategory> queryAllCategory(Boolean isDel);
}
