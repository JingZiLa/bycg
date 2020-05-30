package com.bycg.manage.web.service;

import com.bycg.item.pojo.GoodsCategory;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @className:
 * @Author: Mirror
 * @CreateDate: 2020/5/10 17:43
 **/
public interface ManageService {
    Map<String, List<GoodsCategory>> getAllCategory();
}
