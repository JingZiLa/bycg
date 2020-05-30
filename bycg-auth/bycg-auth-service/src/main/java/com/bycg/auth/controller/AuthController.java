package com.bycg.auth.controller;

import com.bycg.auth.config.JwtProperties;
import com.bycg.auth.service.AuthService;
import com.bycg.auth.pojo.UserInfo;
import com.bycg.auth.utils.JwtUtils;
import com.bycg.conmmon.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 授权中心控制层
 * @className: AuthController
 * @Author: Mirror
 * @CreateDate: 2020/5/21 16:25
 **/
@Controller
@EnableConfigurationProperties(JwtProperties.class) //启用JWT的配置类
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户登陆方法
     * @param loginName
     * @param password
     * @return
     */
    @PostMapping("/user/login")
    public ResponseEntity<Void> login(
            @RequestParam("loginName")String loginName,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response) {
      String token = this.authService.login(loginName,password);

      if (StringUtils.isBlank(token)) {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }
        CookieUtils.setCookie(request,response,jwtProperties.getCookieName(),token,jwtProperties.getExpire()*60);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 验证登陆用户信息
     * @param token
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/user/verify")
    public ResponseEntity<UserInfo> userVerify(
            @CookieValue("BYCG_TOKEN")String token,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            //使用公钥解析jwt
            UserInfo   user = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());

            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            //刷新jwt有效时间
            JwtUtils.generateToken(user,jwtProperties.getPrivateKey(),jwtProperties.getExpire());
            //刷新cookie有效时间
            CookieUtils.setCookie(request,response,jwtProperties.getCookieName(),token,jwtProperties.getExpire()*60);

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
