package com.wang.springbootwebdemo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Map;

@Controller
public class LoginController {
    @PostMapping(value = "/usr/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map){
        if(!StringUtils.isEmpty(username)&&"123".equals(password)){
            return "dashboard";
        }else{
            map.put("msg","用户密码错误");
            return "login";
        }
    }
}
