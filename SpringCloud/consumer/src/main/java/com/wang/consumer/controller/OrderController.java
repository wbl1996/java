package com.wang.consumer.controller;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/order")
    public String getOrderPage(@Param("place")String place,@Param("time")String time,@Param("price")String price,HttpSession session){
        session.setAttribute("place",place);
        session.setAttribute("time",time);
        session.setAttribute("price",price);
        return "order";
    }
}
