package com.bycg.user.controller;

import com.bycg.conmmon.pojo.PageResult;
import com.bycg.conmmon.utils.CodeUtils;
import com.bycg.conmmon.utils.CookieUtils;
import com.bycg.user.bo.UserBo;
import com.bycg.user.pojo.User;
import com.bycg.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Description: 用户控制层
 * @className: UserController
 * @Author: Mirror
 * @CreateDate: 2020/5/13 17:13
 **/
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户（列表，分页）
     * @param order
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("list")
    public ResponseEntity<PageResult<UserBo>> queryVipUserByPage(
            @RequestParam(value = "order", defaultValue = "asc") String order,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam("isAU") Boolean isAU) {

        PageResult<UserBo> pageResult = this.userService.queryUserByPage(order,page,limit,isAU);

        if (!CollectionUtils.isEmpty(pageResult.getList())) {
            return ResponseEntity.ok(pageResult);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * 根据用户ID修改用户状态
     * @param ids
     * @param lockStatus
     * @param isAU
     * @return
     */
    @PostMapping("lock/{lockStatus}/{isAU}")
    public ResponseEntity<Void> editLockStatus (@RequestBody Long[] ids, @PathVariable("lockStatus") Integer lockStatus,@PathVariable("isAU") Boolean isAU) {
        if (!(lockStatus >= 1)) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        this.userService.editLockStatus(ids,lockStatus,isAU);

        return ResponseEntity.noContent().build();
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<Void> userRegister(@Valid User user) {
        System.out.println(user);
        this.userService.userRegister(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("verifyCode")
    public ResponseEntity<byte []> verifyCode(HttpServletRequest request,HttpServletResponse response)  {
        //初始化验证码工具类
       CodeUtils codeUtils = new CodeUtils();

        //获取验证码图片
        BufferedImage image = codeUtils.getImage();
        //获取验证码文本
        String codeText = codeUtils.getText();
        //把验证码设置到Cookie,有效时间10分钟
        CookieUtils.setCookie(request,response,"verifyCode",codeText,60*10);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            codeUtils.output(image,baos);
            baos.flush();
            //使用toByteArray()方法转换成字节数组
            byte[] imageInByte = baos.toByteArray();
            baos.close();

            return ResponseEntity.ok(imageInByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/query")
    public ResponseEntity<User> queryUser(@RequestParam("username")String username,@RequestParam("password")String password) {
        User user = this.userService.queryUser(username,password);
        if (user == null) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }
}
