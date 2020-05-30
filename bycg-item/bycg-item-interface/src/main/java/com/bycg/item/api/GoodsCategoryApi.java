package com.bycg.item.api;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.item.pojo.GoodsCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Description: 对外暴露的商品分类访问Api
 * @className: GoodsCategoryApi
 * @Author: Mirror
 * @CreateDate: 2020/5/11 20:28
 **/
@RequestMapping("/category")
public interface GoodsCategoryApi {

    /**
     * 根据条件查询分类（分页）
     * @param order
     * @param page
     * @param limit
     * @param categoryLevel
     * @param parentId
     * @return
     */
    @RequestMapping("/list")
    PageResult<GoodsCategory> queryCategoryByPage(
            @RequestParam(value = "order", defaultValue = "asc") String order,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "categoryLevel", defaultValue = "1") Integer categoryLevel,
            @RequestParam(value = "parentId", defaultValue = "0") Integer parentId,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit);

    /**
     * 查询所有分类信息
     * @return
     */
    @GetMapping("findAllCategory")
    Map<String,List<GoodsCategory>> findAllCategory();

    /**
     * 查询所有分类信息
     * @return
     */
    @GetMapping("queryAllCategory")
    List<GoodsCategory> queryAllCategory(@RequestParam("isDel") Boolean isDel);
}
