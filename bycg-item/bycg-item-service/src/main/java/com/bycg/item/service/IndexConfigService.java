package com.bycg.item.service;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.item.pojo.Carousel;
import com.bycg.item.pojo.IndexConfig;

import java.util.List;

/**
 * @Description: 首页配置业务接口
 * @className: IndexConfigService
 * @Author: Mirror
 * @CreateDate: 2020/5/14 17:47
 **/
public interface IndexConfigService {

    PageResult<Object> queryIndexConfigByPage(String order, Integer page, Integer limit, Integer configType);

    void saveCarousel(Carousel carousel);

    void updateCarousel(Carousel carousel);

    void updateIndexConfig(IndexConfig indexConfig);

    void saveIndexConfig(IndexConfig indexConfig);

    List<Carousel> queryCarouselByIds(Integer[] ids);

    List<IndexConfig> queryIndexConfigByIds(Long[] ids);

    void deleteIndexConfigByIds(Long[] ids);

    void deleteCarouselByIds(Integer[] ids);

    List<Carousel> queryAllCarousel(Boolean isDel);
}
