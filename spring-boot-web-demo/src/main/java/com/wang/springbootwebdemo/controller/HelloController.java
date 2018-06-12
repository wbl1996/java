package com.wang.springbootwebdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("HelloWorld");
        return "HelloWorld";
    }
    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","你好");
        return "success";
    }
//    @RequestMapping("/login")
//    public String login(){
//        return "login";
//    }
}
