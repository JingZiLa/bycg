package com.bycg.portal.web.controller;

import com.bycg.auth.pojo.UserInfo;
import com.bycg.auth.utils.JwtUtils;
import com.bycg.cart.vo.CartVo;
import com.bycg.conmmon.pojo.PageResult;
import com.bycg.conmmon.utils.CookieUtils;
import com.bycg.portal.web.config.JwtProperties;
import com.bycg.portal.web.service.PortalWebService;
import com.bycg.portal.web.vo.IndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description: 前台系统页面控制层
 * @className: PortalController
 * @Author: Mirror
 * @CreateDate: 2020/5/15 15:39
 **/
@Controller
@EnableConfigurationProperties(JwtProperties.class) //启用JWT的配置类
public class PortalWebController {

    @Autowired
    private PortalWebService portalWebService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 前台门户主页面数据及页面处理
     * @return
     */
    @GetMapping({"","index"})
    public ModelAndView toIndexView() {
        //初始化
        ModelAndView modelAndView = new ModelAndView();
        //获取页面所需数据
        IndexVO indexVO = this.portalWebService.loadIndexData();

        if (indexVO != null ) {
            //设置数据
            modelAndView.addObject(indexVO);
        }
        //设置视图
        modelAndView.setViewName("mall/index");
        return modelAndView;
    }

    /**
     * 商品搜索数据处理及跳转页面
     * @param keyword
     * @param categoryId
     * @param configType
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("search")
    public ModelAndView toSearchView (@RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestParam(value = "categoryId", required = false) Long categoryId,
                                    @RequestParam(value = "configType", required = false) Integer configType,
                                    @RequestParam(value = "order", defaultValue = "desc") String order,
                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "limit", defaultValue = "20") Integer limit
                                    ) {

        ModelAndView modelAndView = new ModelAndView();

        PageResult<List<Map<String,Object>>> pageResult = this.portalWebService.loadSearchGoodsByPage(keyword,categoryId,configType,order,page,limit);

        if (!CollectionUtils.isEmpty(pageResult.getMapList())) {
            modelAndView.addObject("order",order);
            modelAndView.addObject("pageResult",pageResult);
        }
        modelAndView.setViewName("mall/search");
        return modelAndView;
    }


    @GetMapping("goods/detail/{goodsId}")
    public ModelAndView toGoodsPage(@PathVariable("goodsId") Long goodsId) {
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> map = this.portalWebService.loadGoodsData(goodsId);

        if (!CollectionUtils.isEmpty(map)) {
            modelAndView.addObject("goodsDetail",map);

        }

        modelAndView.setViewName("mall/detail");
        return modelAndView;
    }

    /**
     * 用户注册页面跳转
     * @return
     */
    @GetMapping("register")
    public String toUserRegisterView (){
        return "mall/register";
    }

    /**
     * 用户登陆页面跳转
     * @return
     */
    @GetMapping("login")
    public String toLoginView() {
        return "mall/login";
    }


    /**
     * 购物车页面跳转
     * @return
     */
    @PostMapping("cart")
    public ModelAndView toCartView(@RequestBody List<CartVo> cartVos){
        System.out.println(cartVos);
        ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("myShoppingCartItems", cartVos);
        modelAndView.setViewName("mall/cart");
        return modelAndView;
    }
    private UserInfo userVerify(String token,HttpServletRequest request,HttpServletResponse response) {

        try {
            //使用公钥解析jwt
            UserInfo   user = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());

            if (user == null) {
                return null;
            }

            //刷新jwt有效时间
            JwtUtils.generateToken(user,jwtProperties.getPrivateKey(),jwtProperties.getExpire());
            //刷新cookie有效时间
            CookieUtils.setCookie(request,response,jwtProperties.getCookieName(),token,jwtProperties.getExpire()*60);

            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
