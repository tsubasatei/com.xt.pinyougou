package com.xt.pinyougou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录验证
 */
@Controller
public class LoginController {

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Map<String, Object> map) {
        if (!StringUtils.isEmpty(username) && password.equals("123456")) {
            session.setAttribute("loginUser", username);
            // 登录成功，防止表单重复提交，可以重定向到主页
//            return "admin/index";
            // 登录成功，防止表单重复提交，可以重定向到主页
            return "redirect:/admin/index";
        }
        map.put("msg", "用户名或密码不正确");
        return "login";
    }
}
