package com.wang.consumer.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ResultController {
    @Autowired
    RestTemplate restTemplate;
    @PostMapping(value = "/pay")
    public String success(HttpServletRequest request, HttpSession session){
        String accountname = request.getParameter("accountname");
        String password = request.getParameter("password");
        //String price = request.getParameter("price");
        String p = (String)session.getAttribute("price");
        String info = restTemplate.getForObject("http://BANK/update?accountname="+accountname+"&password="+password+"&price="+p,String.class);
        if(info.equals("支付成功")){
            return "result";
        }else{
            return "paypage";
        }
    }
    @GetMapping("/reset")
    public String reset(){
        return "ticket";
    }
}
