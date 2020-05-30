package com.bycg.item.mapper;

import com.bycg.item.pojo.IndexConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 首页配置操作类
 * @className: IndexConfigMapper
 * @Author: Mirror
 * @CreateDate: 2020/5/14 17:51
 **/
public interface IndexConfigMapper {

    /**
     * 根据条件查询首页配置
     * @param order
     * @param page
     * @param limit
     * @param configType
     * @return
     */
    List<IndexConfig> queryIndexConfigByPage(@Param("order")String order, @Param("page")Integer page, @Param("limit") Integer limit, @Param("configType") Integer configType);
    /**
     *
     * 保存新增首页商品推荐配置
     * @param indexConfig
     * @return
     */
    void saveIndexConfig(IndexConfig indexConfig);
    /**
     *
     * 修改首页商品推荐配置
     * @param indexConfig
     * @return
     */
    void updateIndexConfig(IndexConfig indexConfig);

    /**
     * 根据ID 查询 首页推荐商品配置信息
     * @param ids
     * @return
     */
    List<IndexConfig> queryIndexConfigByIds(@Param("ids") Long[] ids);
    /**
     * 根据ID 删除首页推荐商品设置 (可批量)
     * @param ids
     * @return
     */
    void deleteIndexConfigByIds(@Param("ids") Long[] ids);
}
