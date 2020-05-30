package com.bycg.portal.web.service.impl;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.item.pojo.Carousel;
import com.bycg.item.pojo.GoodsCategory;
import com.bycg.item.pojo.GoodsInfo;
import com.bycg.item.pojo.IndexConfig;
import com.bycg.portal.web.client.CartClient;
import com.bycg.portal.web.client.GoodsCategoryClient;
import com.bycg.portal.web.client.GoodsClient;
import com.bycg.portal.web.client.IndexConfigClient;
import com.bycg.portal.web.service.PortalWebService;
import com.bycg.portal.web.vo.IndexVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 前台系统页面控制层业务实现
 * @className: PortalWebServiceImpl
 * @Author: Mirror
 * @CreateDate: 2020/5/16 13:11
 **/
@Service
public class PortalWebServiceImpl implements PortalWebService {

    @Autowired
    private GoodsCategoryClient goodsCategoryClient;

    @Autowired
    private IndexConfigClient indexConfigClient;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private CartClient cartClient;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(PortalWebServiceImpl.class);

    /**
     * 加载Index页面数据
     *
     * @return
     */
    @Override
    public IndexVO loadIndexData() {
        //初始化返回结果类
        IndexVO indexVO = new IndexVO();

        //获取所有分类信息
        List<GoodsCategory> goodsCategories = this.goodsCategoryClient.queryAllCategory(false);

        //初始化分类返回数据容器
        Map<String,Map<String,Map<String,Object>>> categoryMap = new HashMap<>();


        for (int i = 0; i < goodsCategories.size(); i++) {
            //获取分类信息
            GoodsCategory category = goodsCategories.get(i);

            //判断是否为顶级分类
            if (category.getParentId() == 0) {
                //初始化当前一级分类下的二级分类的容器
                Map<String,Map<String,Object>> category1Map = new HashMap<>();

                for (int j = i+1; j < goodsCategories.size()-i-1; j++) {

                    GoodsCategory category1 = goodsCategories.get(j);

                    if (category1.getParentId() == category.getCategoryId()) {
                        //初始化当前二级分类下的三级分类的容器
                        Map<String,Object> map = new HashMap<>();

                        for (int z = j+1; z < goodsCategories.size()-i-1; z++) {

                            GoodsCategory category2 = goodsCategories.get(z);

                            if (category2.getParentId() == category1.getCategoryId()) {
                                //设置当前三级分类 数据 key：当前三级分类名称 ， value ：当前三级分类ID
                                map.put(category2.getCategoryName(),category2.getCategoryId());
                            }
                        }
                        //设置当前二级分类数据  key : 二级分类名称, value : 当前二级分类下的三级分类数据 Map<String,Object>
                        category1Map.put(category1.getCategoryName(),map);
                    }
                }
                //设置当前一级分类数据  key : 一级分类名称, value : 当前一级分类下的二级分类数据Map<String,Map<String,Object>>
                categoryMap.put(category.getCategoryName(),category1Map);
            }
        }


        /**
         * 轮播图数据
         */
        List<Map<String, String>> carousels = new ArrayList<>();

        List<Carousel> carouselList = this.indexConfigClient.queryAllCarousel(true);
        if (!CollectionUtils.isEmpty(carouselList)) {
            carouselList.forEach(carousel -> {

                Map<String, String> carouselMap = new HashMap<>();

                carouselMap.put("carouselUrl", carousel.getCarouselUrl());
                carouselMap.put("redirectUrl", carousel.getRedirectUrl());

                carousels.add(carouselMap);
            });
        }
        //获取热门商品数据
        List<Map<String, Object>> hotSellGoods = getGoodsByType(4, 3);

        //获取新品推荐商品数据
        List<Map<String, Object>> newGoods = getGoodsByType(5, 4);
        //获取猜你喜欢商品数据
        List<Map<String, Object>> GuessYouLikes = getGoodsByType(10, 5);

        //设置分类数据
        indexVO.setCategoryMap(categoryMap);

        //设置轮播图数据
        indexVO.setCarousels(carousels);
        //设置热销商品数据
        indexVO.setHotSellGoods(hotSellGoods);
        //设置新品推荐商品数据
        indexVO.setNewGoods(newGoods);
        //设置猜你喜欢商品数据
        indexVO.setGuessYouLikes(GuessYouLikes);
        LOGGER.info("indexVO: {}", indexVO.toString());
        LOGGER.info("categoryMap: {}", indexVO.getCategoryMap());
        return indexVO;
    }

    /**
     * 根据条件获取搜索商品数据
     * @param keyword
     * @param categoryId
     * @param configType
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageResult<List<Map<String, Object>>> loadSearchGoodsByPage(String keyword, Long categoryId, Integer configType, String order,Integer page, Integer limit) {
        PageResult<List<Map<String, Object>>> pageResult = this.goodsClient.searchGoodsByPage(keyword, categoryId, configType, order, page, limit);
        return pageResult;
    }

    /**
     * 根据商品ID加载商品详情信息
     * @param goodsId
     * @return
     */
    @Override
    public Map<String, Object> loadGoodsData(Long goodsId) {
        GoodsInfo goodsInfo = this.goodsClient.queryGoodsById(goodsId);

        Map<String,Object> map = new HashMap<>();

        map.put("goodsId",goodsInfo.getGoodsId());
        map.put("goodsName",goodsInfo.getGoodsName());
        map.put("goodsCoverImg",goodsInfo.getGoodsCoverImg());
        map.put("sellingPrice",goodsInfo.getSellingPrice());
        map.put("originalPrice",goodsInfo.getOriginalPrice());
        map.put("goodsDetailContent",goodsInfo.getGoodsDetailContent());
        map.put("goodsIntro",goodsInfo.getGoodsIntro());
        return map;
    }

    /**
     * 加载购物车数据
     * @return
     */
    @Override
    public List<Map<String, Object>> loadCart() {
        return null;
    }


    /**
     * 获取配置商品并处理
     *
     * @param limit
     * @param configType
     * @return
     */
    private List<Map<String, Object>> getGoodsByType(Integer limit, Integer configType) {
        //初始化商品返回结果
        List<Map<String, Object>> list = new ArrayList<>();

        //获取配置商品数据
        List<Object> indexConfigList = this.indexConfigClient.queryIndexConfigByPage(null, 1, limit, configType).getList();

        indexConfigList.forEach(o -> {

            IndexConfig indexConfig = MAPPER.convertValue(o, IndexConfig.class);

            //初始化Map
            Map<String, Object> map = new HashMap<>();
            //获取商品信息
            GoodsInfo goodsInfo = this.goodsClient.queryGoodsById(indexConfig.getGoodsId());

            //封装商品数据
            //商品ID
            map.put("goodsId", goodsInfo.getGoodsId());
            //商品图片
            map.put("goodsCoverImg", goodsInfo.getGoodsCoverImg());
            //商品名字
            map.put("goodsName", goodsInfo.getGoodsName());

            if (configType == 4 || configType == 5) {
                //商品标签
                map.put("goodsTag", goodsInfo.getTag());
                //商品简介
                map.put("goodsIntro", goodsInfo.getGoodsIntro());
                //商品价格
                map.put("sellingPrice", goodsInfo.getSellingPrice());
            }

            list.add(map);
        });
        return list;
    }



}
