package com.wang.recruitwebsite.controller;

import com.wang.recruitwebsite.repository.AdministratorRepository;
import com.wang.recruitwebsite.repository.EnterpriseRepository;
import com.wang.recruitwebsite.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.wang.recruitwebsite.entity.*;
@Controller
public class SignupController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    EnterpriseRepository enterpriseRepository;
    @Autowired
    AdministratorRepository administratorRepository;
    @GetMapping("/goSignup")
    public String goSignup(){
        return "signup";
    }
    @RequestMapping("/studentSignup")
    public String studentSignup(@RequestParam(name = "id") String id,@RequestParam(name = "name") String name,
                                @RequestParam(name = "grade") String grade,
                                @RequestParam(name = "sex") String sex, @RequestParam(name = "password") String password){
        Student student = new Student(id,name,grade,sex,password);
        studentRepository.saveAndFlush(student);
        return "index";
    }
    @RequestMapping("/enterpriseSignup")
    public String enterpriseSignup(@RequestParam(name = "code") String id,@RequestParam(name = "enterpriseName") String name,
                                   @RequestParam(name = "address") String address,
                                   @RequestParam(name = "HR") String HR,@RequestParam(name = "Epassword") String password){
        Enterprise enterprise = new Enterprise(id,name,address,HR,password);
        enterpriseRepository.saveAndFlush(enterprise);
        return "index";
    }
}
