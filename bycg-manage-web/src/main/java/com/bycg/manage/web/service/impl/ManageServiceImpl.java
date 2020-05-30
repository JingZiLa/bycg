package com.bycg.manage.web.service.impl;

import com.bycg.item.pojo.GoodsCategory;
import com.bycg.manage.web.client.GoodsCategoryClient;
import com.bycg.manage.web.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @className:
 * @Author: Mirror
 * @CreateDate: 2020/5/10 17:43
 **/
@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private GoodsCategoryClient goodsCategoryClient;


    /**
     * 获取所有分类信息
     * @return
     */
    @Override
    public Map<String, List<GoodsCategory>> getAllCategory() {
        Map<String, List<GoodsCategory>> allCategory = this.goodsCategoryClient.findAllCategory();
        return allCategory;
    }
}
