package com.bycg.portal.web.vo;

import com.bycg.item.pojo.GoodsCategory;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 前台首页VO类
 * @className: IndexVO
 * @Author: Mirror
 * @CreateDate: 2020/5/16 16:17
 **/
@Data
public class IndexVO {

    /**
     * 分类
     */
    private Map<String,Map<String,Map<String,Object>>> categoryMap;

    /**
     * 轮播图
     */
    private List<Map<String,String>> carousels;

    /**
     * 热销商品
     * List<Map<属性名，属性值>>
     *     goodsId, 18
     */
    private List<Map<String,Object>> hotSellGoods;

    /**
     * 新品推荐
     */
    private List<Map<String,Object>> newGoods;

    /**
     * 猜你喜欢
     */
    private  List<Map<String,Object>> GuessYouLikes;
}
