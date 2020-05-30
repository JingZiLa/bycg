package com.bycg.item.mapper;

import com.bycg.item.pojo.GoodsCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 商品分类信息操作接口
 * @className: GoodsCategoryMapper
 * @Author: Mirror
 * @CreateDate: 2020/5/10 10:44
 **/
public interface GoodsCategoryMapper {
    /**
     * 根据条件查询分类
     * @param order
     * @param page
     * @param limit
     * @param categoryLevel
     * @param parentId
     * @return
     */
    List<GoodsCategory> queryCategoryByPage(@Param("order") String order, @Param("page") Integer page, @Param("limit") Integer limit, @Param("categoryLevel") Integer categoryLevel, @Param("parentId") Integer parentId);

    /**
     * 新增商品分类
     * @param goodsCategory
     */
    void saveGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 根据分类ID查询分类信息
     * @param categoryId
     * @return
     */
    GoodsCategory queryCategoryById(Long categoryId);

    /**
     * 更新分类信息
     * @param goodsCategory
     */
    void updateGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 根据ParentId查询分类信息
     * @param categoryId
     * @return
     */
    List<GoodsCategory> queryCategoryByParentId(Long categoryId);

    /**
     * 查询所有分类信息
     * @return
     */
    List<GoodsCategory> queryAllCategory(Boolean isDel);
}
