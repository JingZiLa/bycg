package com.bycg.manage.web.controller;

import com.bycg.item.pojo.GoodsCategory;
import com.bycg.manage.web.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Description: 后台管理页面控制类
 * @className: GoodsWebController
 * @Author: Mirror
 * @CreateDate: 2020/5/10 15:22
 **/
@Controller
public class ManageController {

    @Autowired
    private ManageService manageService;

    /**
     * 商品管理页面跳转
     * @return
     */
    @GetMapping("goodsWeb/listView")
    public String goodsListView() {
        return "admin/goods";
    }
    /**
     * 分类管理页面跳转
     * @return
     */
    @GetMapping("category/listView")
    public String categoryListView() {
        return "admin/category";
    }
    /**
     * 主页面跳转
     * @return
     */
    @GetMapping
    public String manageMainView() {
        return "index";
    }

    /**
     * 添加商品页面跳转
     * @return
     */
    @GetMapping("goodsWeb/addView")
    public String goodsAddView(Model model) {
        Map<String, List<GoodsCategory>> allCategory = this.manageService.getAllCategory();
        model.addAllAttributes(allCategory);
        model.addAttribute("path","goods-edit");
        return "admin/goods_edit";
    }

    /**
     * 修改商品页面跳转
     * @return
     */
    @GetMapping("goodsWeb/editView")
    public String goodsEditView() {
        return "admin/goods_edit";
    }

    /**
     * 会员管理页面跳转
     * @return
     */
    @GetMapping("userWeb/vipUser/listView")
    public String vipUser() {
        return "admin/user";
    }

    /**
     * 管理员管理页面跳转
     * @return
     */
    @GetMapping("userWeb/adminUser/listView")
    public String adminUser() {
        return "admin/admin_user";
    }

    /**
     * 订单页面跳转
     * @return
     */
    @GetMapping("orderWeb/listView")
    public String orderView() {
        return "admin/order";
    }

    /**
     * 首页配置页面跳转
     * @param configType
     * @return
     */
    @GetMapping("goodsWeb/homeView")
    public ModelAndView homeView(@RequestParam("configType") Integer configType) {
        ModelAndView modelAndView = new ModelAndView();
        if (configType > 5 || configType < 2) {
            modelAndView.setViewName("admin/error/error_400");
            return modelAndView;
        } else if (configType == 2 ) {
            modelAndView.setViewName("admin/carousel");
        } else {
            modelAndView.addObject("configType",configType);
            modelAndView.setViewName("admin/index_config");
        }
        return modelAndView;
    }
}
