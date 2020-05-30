package com.bycg.portal.web.service;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.portal.web.vo.IndexVO;

import java.util.List;
import java.util.Map;

/**
 * @Description: 前台系统页面业务接口
 * @className: PortalWebService
 * @Author: Mirror
 * @CreateDate: 2020/5/16 13:10
 **/
public interface PortalWebService {

    /**
     * 加载Index页面数据
     * @return
     */
    IndexVO loadIndexData();

    PageResult<List<Map<String, Object>>> loadSearchGoodsByPage(String keyword, Long categoryId, Integer configType,String order, Integer page, Integer limit);

    Map<String, Object> loadGoodsData(Long goodsId);

    List<Map<String, Object>> loadCart();
}
