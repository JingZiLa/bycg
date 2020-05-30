package com.bycg.item.mapper;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.item.bo.GoodsBo;
import com.bycg.item.pojo.GoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 商品信息操作接口
 * @className: GoodsInfoMapper
 * @Author: Mirror
 * @CreateDate: 2020/5/10 10:45
 **/
public interface GoodsInfoMapper {

    /**
     * 根据条件查询商品数据（分页）
     * @param order
     * @param page
     * @param limit
     * @return
     */
    List<GoodsInfo> queryGoodsByPage(@Param("order") String order, @Param("page")Integer page, @Param("limit")Integer limit,@Param("keyword")String keyword, @Param("categoryId")Long categoryId, @Param("configType")Integer configType);

    /**
     * 批量修改商品状态
     * @param ids
     * @param statusNum
     */
    void editStatus(@Param("ids") Long[] ids, @Param("statusNum") Integer statusNum);
    /**
     * 根据商品ID 查询商品信息
     * @param goodsId
     * @return
     */
    GoodsInfo queryGoodsById(Long goodsId);
}
