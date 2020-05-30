package com.bycg.item.service.impl;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.item.bo.GoodsBo;
import com.bycg.item.mapper.GoodsInfoMapper;
import com.bycg.item.pojo.GoodsInfo;
import com.bycg.item.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 商品操作业务层实现类
 * @className: GoodsServiceImpl
 * @Author: Mirror
 * @CreateDate: 2020/5/10 18:32
 **/
@Service
public class GoodsServiceImpl implements GoodsService {


    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    /**
     * 根据条件查询商品数据（分页）
     *
     * @param order
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageResult<GoodsBo> queryGoodsByPage(String order, Integer page, Integer limit) {

        //添加分页条件
        PageHelper.startPage(page, limit);
        PageHelper.orderBy("update_time "+ order);
        List<GoodsInfo> goodsInfos = this.goodsInfoMapper.queryGoodsByPage(order, page, limit, null, null, null);

        PageInfo<GoodsInfo> pageInfo = new PageInfo<>(goodsInfos);

        List<GoodsBo> goodsBos = goodsInfos.stream().map(goodsInfo -> {
            GoodsBo goodsBo = new GoodsBo();
            // copy共同属性的值到新的对象
            BeanUtils.copyProperties(goodsInfo, goodsBo);
            return goodsBo;
        }).collect(Collectors.toList());

        //封装结果集并返回
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(), goodsBos, pageInfo.getPageNum(), pageInfo.getPageSize());
    }

    /**
     * 上下架商品
     *
     * @param ids
     * @param statusNum
     */
    @Override
    public void editStatus(Long[] ids, Integer statusNum) {

        this.goodsInfoMapper.editStatus(ids, statusNum);

        System.out.println("修改成功");
    }

    /**
     * 根据商品ID 查询商品信息
     *
     * @param goodsId
     * @return
     */
    @Override
    public GoodsInfo queryGoodsById(Long goodsId) {
        return this.goodsInfoMapper.queryGoodsById(goodsId);
    }

    /**
     * 根据条件搜索商品
     *
     * @param keyword
     * @param categoryId
     * @param configType
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageResult<List<Map<String, Object>>> searchGoodsByPage(String order, String keyword, Long categoryId, Integer configType, Integer page, Integer limit) {

        PageHelper.startPage(page, limit);
        PageHelper.orderBy("selling_price "+ order);
        if (keyword != null && keyword != "") {
            keyword = "%" + keyword + "%";
        }
        List<GoodsInfo> goodsInfos = this.goodsInfoMapper.queryGoodsByPage(order, page, limit, keyword, categoryId, configType);


        PageInfo<GoodsInfo> pageInfo = new PageInfo<>(goodsInfos);
        if (!CollectionUtils.isEmpty(goodsInfos)) {


            List<Map<String, Object>> goodsList = goodsInfos.stream().map(goodsInfo -> {
                Map<String, Object> goodsMap = new HashMap<>();
                goodsMap.put("goodsId", goodsInfo.getGoodsId());
                goodsMap.put("goodsName", goodsInfo.getGoodsName());
                goodsMap.put("goodsIntro", goodsInfo.getGoodsIntro());
                goodsMap.put("goodsCoverImg", goodsInfo.getGoodsCoverImg());
                goodsMap.put("sellingPrice", goodsInfo.getSellingPrice());
                return goodsMap;
            }).collect(Collectors.toList());

            return new PageResult<>(goodsList,pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getPageNum(), pageInfo.getPageSize());
        }
        return null;
    }
}
