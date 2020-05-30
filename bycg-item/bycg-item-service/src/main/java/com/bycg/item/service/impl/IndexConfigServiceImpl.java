package com.bycg.item.service.impl;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.item.mapper.CarouselMapper;
import com.bycg.item.mapper.IndexConfigMapper;
import com.bycg.item.pojo.Carousel;
import com.bycg.item.pojo.IndexConfig;
import com.bycg.item.service.IndexConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 首页配置业务实现类
 * @className: IndexConfigServiceImpl
 * @Author: Mirror
 * @CreateDate: 2020/5/14 17:47
 **/
@Service
public class IndexConfigServiceImpl implements IndexConfigService {

    @Autowired
    private IndexConfigMapper indexConfigMapper;

    @Autowired
    private CarouselMapper carouselMapper;



    /**
     * 根据条件查询首页配置（分页）
     * @param order
     * @param page
     * @param limit
     * @param configType
     * @return
     */
    @Override
    public PageResult<Object> queryIndexConfigByPage(String order, Integer page, Integer limit, Integer configType) {

        PageHelper.startPage(page,limit);

        List<Object> list = new ArrayList<>();

        if (configType == 2) {
            List<Carousel> carousels = this.carouselMapper.queryCarouselByPage(order,page,limit);
            list.addAll(carousels);
        } else {
            List<IndexConfig> indexConfigs = this.indexConfigMapper.queryIndexConfigByPage(order,page,limit,configType);
            list.addAll(indexConfigs);
        }

        PageInfo<Object> pageInfo = new PageInfo<>();

        //封装结果集并返回
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(), list, pageInfo.getPageNum(), pageInfo.getPageSize());
    }

    /**
     * 根据ID 查询 轮播图配置信息
     * @param ids
     * @return
     */
    @Override
    public List<Carousel> queryCarouselByIds(Integer[] ids) {
        return this.carouselMapper.queryCarouselByIds(ids);
    }
    /**
     * 根据ID 查询 首页推荐商品配置信息
     * @param ids
     * @return
     */
    @Override
    public List<IndexConfig> queryIndexConfigByIds(Long[] ids) {

        return this.indexConfigMapper.queryIndexConfigByIds(ids);
    }

    /**
     * 根据ID 删除首页推荐商品设置 (可批量)
     * @param ids
     * @return
     */
    @Override
    public void deleteIndexConfigByIds(Long[] ids) {
        List<IndexConfig> indexConfigs = this.queryIndexConfigByIds(ids);
        if (CollectionUtils.isEmpty(indexConfigs)) {
            return;
        }

        this.indexConfigMapper.deleteIndexConfigByIds(ids);
    }
    /**
     * 根据ID 删除 首页轮播图配置 (可批量)
     * @param ids
     * @return
     */
    @Override
    public void deleteCarouselByIds(Integer[] ids) {

        List<Carousel> carousels = this.queryCarouselByIds(ids);
        if (CollectionUtils.isEmpty(carousels)) {
            return;
        }

        this.carouselMapper.deleteCarouselByIds(ids);
    }

    /**
     * 查询所有轮播图配置信息
     * @return
     */
    @Override
    public List<Carousel> queryAllCarousel(Boolean isDel) {
        List<Carousel> carousels = this.carouselMapper.queryCarouselByPage(null, null, null);
        if (!isDel) {
            carousels.forEach(carousel -> {
                if (carousel.getIsDeleted()) {
                    carousels.remove(carousel);
                }
            });
        }
        return carousels;
    }

    /**
     * 保存轮播图配置
     * @param carousel
     */
    @Override
    public void saveCarousel(Carousel carousel) {
        carousel.setCreateTime(new Date());
        carousel.setUpdateTime(new Date());
        carousel.setCreateUser(1);
        carousel.setUpdateUser(1);
        carousel.setIsDeleted(false);
        this.carouselMapper.saveCarousel(carousel);
    }

    /**
     * 修改轮播图配置
     * @param carousel
     */
    @Override
    public void updateCarousel(Carousel carousel) {
        carousel.setUpdateUser(1);
        carousel.setUpdateTime(new Date());
        this.carouselMapper.updateCarousel(carousel);
    }

    /**
     *
     * 修改首页商品推荐配置
     * @param indexConfig
     * @return
     */
    @Override
    public void updateIndexConfig(IndexConfig indexConfig) {
        indexConfig.setUpdateUser(1);
        indexConfig.setUpdateTime(new Date());
        this.indexConfigMapper.updateIndexConfig(indexConfig);
    }
    /**
     *
     * 保存新增首页商品推荐配置
     * @param indexConfig
     * @return
     */
    @Override
    public void saveIndexConfig(IndexConfig indexConfig) {
        indexConfig.setCreateTime(new Date());
        indexConfig.setCreateUser(1);
        indexConfig.setUpdateTime(new Date());
        indexConfig.setUpdateUser(1);
        indexConfig.setIsDeleted(false);
        this.indexConfigMapper.saveIndexConfig(indexConfig);
    }

}
