package com.bycg.item.controller;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.item.bo.GoodsBo;
import com.bycg.item.pojo.Carousel;
import com.bycg.item.pojo.IndexConfig;
import com.bycg.item.service.IndexConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 首页配置
 * @className: IndexConfigController
 * @Author: Mirror
 * @CreateDate: 2020/5/14 17:36
 **/
@Controller
@RequestMapping("indexConfig")
public class IndexConfigController {

    @Autowired
    private IndexConfigService indexConfigService;

    /**
     * 根据条件查询首页配置（分页）
     * @param order
     * @param page
     * @param limit
     * @param configType
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<PageResult<Object>> queryIndexConfigByPage(
            @RequestParam(value = "order", defaultValue = "DESC") String order,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit,
            @RequestParam(value = "configType",defaultValue = "2") Integer configType
            ) {

        if (configType == null || configType < 2 || configType > 5) {
            return ResponseEntity.badRequest().build();
        }
        PageResult<Object> pageResult = this.indexConfigService.queryIndexConfigByPage(order,page,limit,configType);
        if (!CollectionUtils.isEmpty(pageResult.getList())) {
            return ResponseEntity.ok(pageResult);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * 根据ID 查询 轮播图配置信息
     * @param ids
     * @return
     */
    @GetMapping("queryCarouselByIds/{ids}")
    public ResponseEntity<List<Carousel>> queryCarouselByIds(@PathVariable("ids") Integer[] ids) {

        List<Carousel> carousels = this.indexConfigService.queryCarouselByIds(ids);
        if (CollectionUtils.isEmpty(carousels)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carousels);
    }

    /**
     * 根据ID 查询 首页推荐商品配置信息
     * @param ids
     * @return
     */
    @GetMapping("queryIndexConfigByIds/{ids}")
    public ResponseEntity<List<IndexConfig>> queryIndexConfigByIds(@PathVariable("ids") Long[] ids) {

        List<IndexConfig> indexConfigs = this.indexConfigService.queryIndexConfigByIds(ids);
        if (CollectionUtils.isEmpty(indexConfigs)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(indexConfigs);
    }
    /**
     *
     * 保存新增轮播图配置
     * @param carousel
     * @return
     */
    @PostMapping("saveCarousel")
    public ResponseEntity<Void> saveCarousel(@RequestBody Carousel carousel) {
        if (carousel == null) {
            return ResponseEntity.badRequest().build();
        }
        this.indexConfigService.saveCarousel(carousel);

        return ResponseEntity.noContent().build();
    }

    /**
     * 修改轮播图配置
     * @param carousel
     * @return
     */
    @PostMapping("updateCarousel")
    public ResponseEntity<Void> updateCarousel(@RequestBody Carousel carousel) {
        if (carousel == null) {
            return ResponseEntity.badRequest().build();
        }
        this.indexConfigService.updateCarousel(carousel);

        return ResponseEntity.noContent().build();
    }

    /**
     * 修改首页商品推荐配置
     * @param indexConfig
     * @return
     */
    @PostMapping("updateIndexConfig")
    public ResponseEntity<Void> updateIndexConfig(@RequestBody IndexConfig indexConfig) {
        if (indexConfig == null) {
            return ResponseEntity.badRequest().build();
        }
        this.indexConfigService.updateIndexConfig(indexConfig);

        return ResponseEntity.noContent().build();
    }

    /**
     *
     * 保存新增首页商品推荐配置
     * @param indexConfig
     * @return
     */
    @PostMapping("saveIndexConfig")
    public ResponseEntity<Void> saveIndexConfig(@RequestBody IndexConfig indexConfig) {
        if (indexConfig == null) {
            return ResponseEntity.badRequest().build();
        }
        this.indexConfigService.saveIndexConfig(indexConfig);

        return ResponseEntity.noContent().build();
    }

    /**
     * 根据ID 删除首页推荐商品设置 (可批量)
     * @param ids
     * @return
     */
    @PostMapping("deleteIndexConfigByIds")
    public ResponseEntity<Void> deleteIndexConfigByIds (@RequestBody Long [] ids) {
        if (ids == null) {
            return ResponseEntity.badRequest().build();
        }
        this.indexConfigService.deleteIndexConfigByIds(ids);

        return ResponseEntity.ok().build();
    }

    /**
     * 根据ID 删除 首页轮播图配置 (可批量)
     * @param ids
     * @return
     */
    @PostMapping("deleteCarouselByIds")
    public ResponseEntity<Void> deleteCarouselByIds(@RequestBody Integer [] ids) {
        if (ids == null) {
            return ResponseEntity.badRequest().build();
        }
        this.indexConfigService.deleteCarouselByIds(ids);

        return ResponseEntity.ok().build();
    }


    /**
     * 查询所有轮播图配置信息
     * @return
     */
    @GetMapping("queryAllCarousel")
    public ResponseEntity<List<Carousel>> queryAllCarousel(@RequestParam("isDel") Boolean isDel) {
        List<Carousel> carousels = this.indexConfigService.queryAllCarousel(isDel);
        if (CollectionUtils.isEmpty(carousels)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carousels);
    }

}
