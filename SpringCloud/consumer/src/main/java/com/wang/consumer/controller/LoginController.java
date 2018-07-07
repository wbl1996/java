package com.wang.consumer.controller;

import com.google.gson.Gson;
import com.wang.consumer.entities.Ticket;
import com.wang.consumer.entities.User;
import com.wang.consumer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;
    @PostMapping(value = "/usr/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        Optional<User> optional = userRepository.findById(username);
        if(optional.isPresent()){
            if(optional.get().getPassword().equals(password)) {
                session.setAttribute("loginuser", username);
                return "redirect:/main.html";
            }else{
                map.put("msg","用户密码错误");
                return "signin";
            }
        }else{
            map.put("msg","用户密码错误");
            return "signin";
        }
    }
    @GetMapping("/main.html")
    public String toMain(HttpSession session){
        Gson gson = new Gson();
        System.out.println("this is json");
        String json = restTemplate.getForObject("http://PROVIDER-TICKET/alltickets",String.class);
        System.out.println("json is:"+json);
        List<Ticket> list = gson.fromJson(json,new TypeToken<ArrayList<Ticket>>(){}.getType());
        session.setAttribute("list",list);
        return "ticket";
    }
}
