package com.wang.springbootwebdemo.controller;
import com.wang.springbootwebdemo.entities.User;
import com.wang.springbootwebdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/user/{username}")
    public User getUser(@PathVariable("username")String username){
        User user = new User();
        user.setUsername(username);
        Example<User> example = Example.of(user);
        return userRepository.findOne(example).get();
    }
    @GetMapping("/user")
    public User insertUser(User user){
        User save = userRepository.save(user);
        return save;
    }

}
