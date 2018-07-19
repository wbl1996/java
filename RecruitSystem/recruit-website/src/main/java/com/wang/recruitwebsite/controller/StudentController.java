package com.wang.recruitwebsite.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.google.gson.JsonObject;
import com.wang.recruitwebsite.entity.*;
import com.wang.recruitwebsite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AllRecruitRepository allRecruitRepository;
    @Autowired
    RecruitInfoRepository recruitInfoRepository;
    @Autowired
    AllApplyRepository allApplyRepository;

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        return "index";
    }

    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> getInfo(HttpSession session)  {
        Student student = (Student) session.getAttribute("student");
        Map map = new HashMap();
        map.put("id",student.getId());
        map.put("name",student.getName());
        map.put("sex", student.getSex());
        map.put("grade",student.getGrade());
        return map;
    }
    @RequestMapping(value = "/setResume",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> setResume(@RequestBody JSONObject object){
        String data = object.toJSONString();
        Resume resume = JSON.parseObject(data, Resume.class);
        resumeRepository.saveAndFlush(resume);
        Map map = new HashMap();
        map.put("flag","1");
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/queryResume",method = RequestMethod.POST)
    public Map<String,String> queryResume(HttpSession session){
        Student student = (Student)session.getAttribute("student");
        String id = student.getId();
        Optional<Resume> optional = resumeRepository.findById(id);
        Map map = new HashMap();
        if(optional.isPresent()){
            map.put("id",optional.get().getId());
            map.put("name",optional.get().getName());
            map.put("major",optional.get().getMajor());
            map.put("post",optional.get().getPost());
            map.put("language",optional.get().getLanguage());
            map.put("skill",optional.get().getSkill());
            map.put("evaluation",optional.get().getEvaluation());
            map.put("experience",optional.get().getExperience());
            map.put("honor",optional.get().getHonor());
            return map;
        }else{
            map.put("flag","0");
            return map;
        }
    }
    @RequestMapping(value = "/deleteResume",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> deleteResume(HttpSession session){
        Student student = (Student)session.getAttribute("student");
        String id = student.getId();
        resumeRepository.deleteById(id);
        Map map = new HashMap();
        map.put("flag","1");
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    public String getList(){
        List<AllRecruit> list = allRecruitRepository.findAll();
        String json = JSON.toJSON(list).toString();
        return json;
    }
    @ResponseBody
    @RequestMapping(value = "getDetail",method = RequestMethod.POST)
    public Map<String,String> getDetail(@RequestBody JSONObject jsonObject){
        String id = jsonObject.getString("id");
        Optional<RecruitInfo> optional = recruitInfoRepository.findById(id);
        Map map = new HashMap();
        if(optional.isPresent()){
            map.put("id",optional.get().getId());
            map.put("name",optional.get().getName());
            map.put("post",optional.get().getPost());
            map.put("address",optional.get().getAddress());
            map.put("language",optional.get().getLanguage());
            map.put("skill",optional.get().getSkill());
            map.put("experience",optional.get().getExperience());
            map.put("education",optional.get().getEducation());
            map.put("salary",optional.get().getSalary());
            map.put("extra",optional.get().getExtra());
            return map;
        }else{
            map.put("flag","0");
            return map;
        }
    }
    @ResponseBody
    @RequestMapping(value = "/uploadResume",method = RequestMethod.POST)
    public Map<String,String> uploadResume(@RequestBody JSONObject jsonObject,HttpSession session){
        String e_name = jsonObject.getString("e_name");
        String post = jsonObject.getString("post");
        Student student = (Student)session.getAttribute("student");
        AllApply allApply = new AllApply(0,student.getId(),student.getName(),e_name,post,false);
        allApplyRepository.saveAndFlush(allApply);
        Map map = new HashMap();
        map.put("flag","1");
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/getAppliedInfo",method = RequestMethod.POST)
    public String getAppliedInfo(HttpSession session){
        Student student = (Student)session.getAttribute("student");
        String id = student.getId();
        List<AllApply> list = allApplyRepository.findByStudentId(id);
        String json = JSON.toJSON(list).toString();
        return json;
    }
}
