package com.bycg.item.service.impl;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.item.mapper.GoodsCategoryMapper;
import com.bycg.item.pojo.GoodsCategory;
import com.bycg.item.pojo.GoodsInfo;
import com.bycg.item.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 商品分类业务层实现类
 * @className: CategoryServiceImpl
 * @Author: Mirror
 * @CreateDate: 2020/5/11 20:17
 **/
@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    /**
     * 根据条件查询分类（分页）
     * @param order
     * @param page
     * @param limit
     * @param categoryLevel
     * @param parentId
     * @return
     */
    @Override
    public PageResult<GoodsCategory> queryCategoryByPage(String order, Integer page, Integer limit, Integer categoryLevel, Integer parentId) {
        //添加分页条件
        PageHelper.startPage(page, limit);
        List<GoodsCategory> goodsCategories = this.goodsCategoryMapper.queryCategoryByPage(order, page, limit, categoryLevel, parentId);
        PageInfo<GoodsCategory> pageInfo = new PageInfo<>(goodsCategories);

        //封装结果集并返回
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(), goodsCategories, pageInfo.getPageNum(), pageInfo.getPageSize());
    }

    /**
     * 新增商品分类
     * @param goodsCategory
     */
    @Override
    public void saveGoodsCategory(GoodsCategory goodsCategory) {
        goodsCategory.setCategoryId(null);
        goodsCategory.setCreateTime(new Date());
        goodsCategory.setUpdateTime(new Date());
        goodsCategory.setIsDeleted(false);
        goodsCategory.setCreateUser(1);
        goodsCategory.setUpdateUser(1);
        this.goodsCategoryMapper.saveGoodsCategory(goodsCategory);
    }

    /**
     * 修改商品分类
     * @param goodsCategory
     */
    @Override
    public Boolean updateGoodsCategory(GoodsCategory goodsCategory) {
        //根据分类ID查询未修改前分类的信息
        GoodsCategory oldGoodsCategory = this.goodsCategoryMapper.queryCategoryById(goodsCategory.getCategoryId());
        if (oldGoodsCategory != null) {
            goodsCategory.setUpdateUser(1);
            goodsCategory.setUpdateTime(new Date());
            //更新分类数据
            this.goodsCategoryMapper.updateGoodsCategory(goodsCategory);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据分类ID查询分类信息 及其子分类信息
     * @param categoryId
     * @return
     */
    @Override
    public Map<String, List<GoodsCategory>> cascadeFindCategorysById(Long categoryId) {

        //根据分类ID 查询分类信息
        GoodsCategory category = this.goodsCategoryMapper.queryCategoryById(categoryId);
        Map<String, List<GoodsCategory>> map = new HashMap<>();
        if (category == null || category.getCategoryLevel() == 3) {
            return null;
        } else {

            if (category.getCategoryLevel() == 1) {
                //如果是一级分类则返回当前一级分类下的所有二级分类，以及二级分类列表中第一条数据下的所有三级分类列表
                //查询一级分类列表中第一个实体的所有二级分类
                List<GoodsCategory> goodsCategories = this.goodsCategoryMapper.queryCategoryByParentId(category.getCategoryId());
                if (!CollectionUtils.isEmpty(goodsCategories)) {

                    map.put("secondLevelCategories",goodsCategories);

                    List<GoodsCategory> goodsCategoryList = this.goodsCategoryMapper.queryCategoryByParentId(goodsCategories.get(0).getCategoryId());

                    if (!CollectionUtils.isEmpty(goodsCategoryList)) {
                        map.put("thirdLevelCategories",goodsCategoryList);
                    }
                }
            } else if (category.getCategoryLevel() == 2) {
                //如果是二级分类则返回当前分类下的所有三级分类列表
                List<GoodsCategory> goodsCategories = this.goodsCategoryMapper.queryCategoryByParentId(category.getCategoryId());
                if (CollectionUtils.isEmpty(goodsCategories)) {
                    return null;
                }
                map.put("thirdLevelCategories", goodsCategories);
            }
        }
        return map;
    }

    /**‘
     * 查询所有分类信息
     * @return
     */
    @Override
    public Map<String, List<GoodsCategory>> findAllCategory() {
        //查询1级分类信息
        List<GoodsCategory> goodsCategories1 = this.goodsCategoryMapper.queryCategoryByParentId(0l);

        //查询第一个1级分类下所有的2级分类信息
        List<GoodsCategory> goodsCategories2 = this.goodsCategoryMapper.queryCategoryByParentId(goodsCategories1.get(0).getCategoryId());

        //查询第一个2级分类下所有的3级分类信息
        List<GoodsCategory> goodsCategories3 = this.goodsCategoryMapper.queryCategoryByParentId(goodsCategories2.get(0).getCategoryId());

        Map<String, List<GoodsCategory>> map = new HashMap<>();
        if (!CollectionUtils.isEmpty(goodsCategories1)) {
            map.put("firstLevelCategories",goodsCategories1);
        }
        if (!CollectionUtils.isEmpty(goodsCategories2)) {
            map.put("secondLevelCategories",goodsCategories2);
        }
        if (!CollectionUtils.isEmpty(goodsCategories3)) {
            map.put("thirdLevelCategories",goodsCategories3);
        }
        return map;
    }

    /**
     * 删除已经逻辑删除的分类数据
     * @param list
     * @return
     */
    private List<GoodsCategory> RMIsDelete(List<GoodsCategory> list) {
        list.forEach(category -> {
            if (category.getIsDeleted() == true) {
                list.remove(category);
            }
        });

        return list;
    }
    /**
     * 查询所有分类信息
     * @return
     */
    @Override
    public List<GoodsCategory> queryAllCategory(Boolean isDel) {
        return this.goodsCategoryMapper.queryAllCategory(isDel);
    }
}
