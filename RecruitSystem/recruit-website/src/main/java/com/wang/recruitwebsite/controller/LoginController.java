package com.wang.recruitwebsite.controller;

import com.wang.recruitwebsite.entity.Administrator;
import com.wang.recruitwebsite.entity.Enterprise;
import com.wang.recruitwebsite.entity.Student;
import com.wang.recruitwebsite.repository.AdministratorRepository;
import com.wang.recruitwebsite.repository.EnterpriseRepository;
import com.wang.recruitwebsite.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    EnterpriseRepository enterpriseRepository;
    @Autowired
    AdministratorRepository administratorRepository;
    @PostMapping(value = "/login")
    public String signin(@RequestParam(name="username") String username, @RequestParam(name="password")  String password,
                         @RequestParam(name="optionsRadios") String identity, HttpSession session){

        if(identity.equals("student")){
            Optional<Student> optional = studentRepository.findById(username);
            if(optional.isPresent()){
                if(optional.get().getPassword().equals(password)){
                    Student student = optional.get();
                    session.setAttribute("student",student);
                    return "studentPage";
                }else{
                    return "index";
                }
            }else{
                return "index";
            }
        }else if(identity.equals("enterprise")){
            Optional<Enterprise> optional = enterpriseRepository.findById(username);
            if(optional.isPresent()){
                if(optional.get().getPassword().equals(password)){
                    Enterprise enterprise = optional.get();
                    session.setAttribute("enterprise",enterprise);
                    return "enterprisePage";
                }else{
                    return "index";
                }
            }else{
                return "index";
            }
        }else{
            Optional<Administrator> optional = administratorRepository.findById(username);
            if(optional.isPresent()){
                if(optional.get().getPassword().equals(password)){
                    Administrator administrator = optional.get();
                    session.setAttribute("administrator",administrator);
                    return "administratorPage";
                }else{
                    return "index";
                }
            }else{
                return "index";
            }

        }
    }
}
