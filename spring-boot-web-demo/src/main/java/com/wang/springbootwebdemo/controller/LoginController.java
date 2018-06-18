package com.wang.springbootwebdemo.controller;
import com.wang.springbootwebdemo.entities.User;
import com.wang.springbootwebdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;
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
                return "signin";
            }
        }else{
            map.put("msg","用户密码错误");
            return "signin";
        }
    }
}
