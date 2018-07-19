package com.wang.recruitwebsite.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wang.recruitwebsite.entity.AllApply;
import com.wang.recruitwebsite.entity.AllRecruit;
import com.wang.recruitwebsite.entity.Enterprise;
import com.wang.recruitwebsite.entity.Student;
import com.wang.recruitwebsite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdministratorController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    EnterpriseRepository enterpriseRepository;
    @Autowired
    AllRecruitRepository allRecruitRepository;
    @Autowired
    RecruitInfoRepository recruitInfoRepository;
    @Autowired
    AllApplyRepository allApplyRepository;
    @ResponseBody
    @RequestMapping(value = "/getStudentInfo",method = RequestMethod.POST)
    public String getStudentInfo(){
        List<Student> list = studentRepository.findAll();
        String json = JSON.toJSON(list).toString();
        return json;
    }
    @ResponseBody
    @RequestMapping(value = "/getEnterpriseInformation",method = RequestMethod.POST)
    public String getEnterpriseInfo(){
        List<Enterprise> list = enterpriseRepository.findAll();
        String json = JSON.toJSON(list).toString();
        return json;
    }
    @ResponseBody
    @RequestMapping(value = "/getListOfRecruit",method = RequestMethod.POST)
    public String getListOfRecruit(){
        List<AllRecruit> list = allRecruitRepository.findAll();
        String json = JSON.toJSON(list).toString();
        return json;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteStudent",method = RequestMethod.POST)
    public Map<String,String> deleteStudent(@RequestBody JSONObject jsonObject){
        String id = jsonObject.getString("id");
        studentRepository.deleteById(id);
        Map map = new HashMap();
        map.put("flag","1");
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteFromList",method = RequestMethod.POST)
    public Map<String,String> deleteFromList(@RequestBody JSONObject jsonObject){
        String id = jsonObject.getString("id");
        recruitInfoRepository.deleteById(id);
        allRecruitRepository.deleteById(id);
        Map map = new HashMap();
        map.put("flag","1");
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/getAllApplyInfo",method = RequestMethod.POST)
    public String getAllApplyInfo(){
        List<AllApply> list = allApplyRepository.findAll();
        String json = JSON.toJSON(list).toString();
        return json;
    }
}
