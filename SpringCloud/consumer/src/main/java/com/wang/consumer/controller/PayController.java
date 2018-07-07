package com.wang.consumer.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PayController {
    @GetMapping("/ensure")
    public String getResult(@Param("price") String price,HttpSession session){
        session.setAttribute("lastPrice",price);
        return "paypage";
    }
    @GetMapping("/cancel")
    public String goback(){
        return "ticket";
    }
}
