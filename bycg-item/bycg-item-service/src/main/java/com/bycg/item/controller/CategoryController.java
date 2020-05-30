package com.bycg.item.controller;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.item.pojo.GoodsCategory;
import com.bycg.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description: 商品分类控制层
 * @className: CategoryController
 * @Author: Mirror
 * @CreateDate: 2020/5/11 19:58
 **/
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据条件查询分类（分页）
     * @param order
     * @param page
     * @param limit
     * @param categoryLevel
     * @param parentId
     * @return
     */
    @RequestMapping("list")
    public ResponseEntity<PageResult<GoodsCategory>> queryCategoryByPage(
             @RequestParam(value = "order", defaultValue = "DESC") String order,
             @RequestParam(value = "page", defaultValue = "1") Integer page,
             @RequestParam(value = "categoryLevel", defaultValue = "1") Integer categoryLevel,
             @RequestParam(value = "parentId", defaultValue = "0") Integer parentId,
             @RequestParam(value = "limit", defaultValue = "20") Integer limit) {

        //查询分类
        PageResult<GoodsCategory> pageResult = this.categoryService.queryCategoryByPage(order,page,limit,categoryLevel,parentId);
        //判断商品数据是否为空
        if (!CollectionUtils.isEmpty(pageResult.getList())) {

            return ResponseEntity.ok(pageResult);
        }
        return ResponseEntity.notFound().build();

    }

    /**
     * 新增商品分类
     * @param goodsCategory
     * @return
     */
    @PostMapping("saveCategory")
    public ResponseEntity<Void> saveGoodsCategory(GoodsCategory goodsCategory) {
        this.categoryService.saveGoodsCategory(goodsCategory);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 修改商品分类
     * @param goodsCategory
     * @return
     */
    @PostMapping("updateCategory")
    public ResponseEntity<Void> updateGoodsCategory(GoodsCategory goodsCategory) {
       if ( this.categoryService.updateGoodsCategory(goodsCategory)) {
           return ResponseEntity.noContent().build();
       }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    /**
     * 根据分类ID查询分类信息 及其子分类信息
     * @param categoryId
     * @return
     */
    @GetMapping("findCategorysById")
    public ResponseEntity<Map<String, List<GoodsCategory>>> findCategorysById(@RequestParam("categoryId") Long categoryId) {
        Map<String, List<GoodsCategory>> map = this.categoryService.cascadeFindCategorysById(categoryId);
        if (CollectionUtils.isEmpty(map)) {
            return ResponseEntity.notFound().build();
        }
       return ResponseEntity.ok(map);
    }

    /**
     * 查询分类信息
     * @return
     */
    @GetMapping("findAllCategory")
    public ResponseEntity<Map<String, List<GoodsCategory>>> findAllCategory() {
        Map<String, List<GoodsCategory>> map = this.categoryService.findAllCategory();
        if (CollectionUtils.isEmpty(map)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(map);
    }

    /**
     * 查询所有分类信息
     * @return
     */
    @GetMapping("queryAllCategory")
    public ResponseEntity<List<GoodsCategory>> queryAllCategory(Boolean isDel) {

        List<GoodsCategory> goodsCategories = this.categoryService.queryAllCategory(isDel);
        if (CollectionUtils.isEmpty(goodsCategories)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(goodsCategories);
    }
}
