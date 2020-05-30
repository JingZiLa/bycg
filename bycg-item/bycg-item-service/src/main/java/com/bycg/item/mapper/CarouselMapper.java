package com.bycg.item.mapper;


import com.bycg.item.pojo.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 轮播图推荐商品信息操作接口
 * @className: CarouselMapper
 * @Author: Mirror
 * @CreateDate: 2020/5/10 10:39
 **/
public interface CarouselMapper {
    /**
     * 根据条件查询轮播图配置
     * @param order
     * @param page
     * @param limit
     * @return
     */
    List<Carousel> queryCarouselByPage(String order, Integer page, Integer limit);
    /**
     * 保存轮播图配置
     * @param carousel
     */
    void saveCarousel(Carousel carousel);
    /**
     * 修改轮播图配置
     * @param carousel
     */
    void updateCarousel(Carousel carousel);
    /**
     * 根据ID 查询 轮播图配置信息
     * @param ids
     * @return
     */
    List<Carousel> queryCarouselByIds(@Param("ids") Integer[] ids);
    /**
     * 根据ID 删除 首页轮播图配置 (可批量)
     * @param ids
     * @return
     */
    void deleteCarouselByIds(@Param("ids") Integer[] ids);
}
