package com.wang.recruitwebsite.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wang.recruitwebsite.entity.AllApply;
import com.wang.recruitwebsite.entity.AllRecruit;
import com.wang.recruitwebsite.entity.Enterprise;
import com.wang.recruitwebsite.entity.RecruitInfo;
import com.wang.recruitwebsite.repository.AllApplyRepository;
import com.wang.recruitwebsite.repository.AllRecruitRepository;
import com.wang.recruitwebsite.repository.EnterpriseRepository;
import com.wang.recruitwebsite.repository.RecruitInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class EnterpriseController {
    @Autowired
    EnterpriseRepository enterpriseRepository;
    @Autowired
    RecruitInfoRepository recruitInfoRepository;
    @Autowired
    AllRecruitRepository allRecruitRepository;
    @Autowired
    AllApplyRepository allApplyRepository;
    @ResponseBody
    @RequestMapping(value = "/getEnterpriseInfo",method = RequestMethod.POST)
    public Map<String,String> getEnterpriseInfo(HttpSession session){
        Enterprise enterprise = (Enterprise)session.getAttribute("enterprise");
        Map map = new HashMap();
        map.put("id",enterprise.getId());
        map.put("name",enterprise.getName());
        map.put("address",enterprise.getAddress());
        map.put("HR",enterprise.getHR());
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/queryRecruit",method = RequestMethod.POST)
    public Map<String,String> queryRecruit(HttpSession session){
        Enterprise enterprise = (Enterprise)session.getAttribute("enterprise");
        String id = enterprise.getId();
        Optional<RecruitInfo> optional = recruitInfoRepository.findById(id);
        Map map = new HashMap();
        if(optional.isPresent()){
            map.put("id",optional.get().getId());
            map.put("name",optional.get().getName());
            map.put("address",optional.get().getAddress());
            map.put("post",optional.get().getPost());
            map.put("salary",optional.get().getSalary());
            map.put("language",optional.get().getLanguage());
            map.put("skill",optional.get().getSkill());
            map.put("experience",optional.get().getExperience());
            map.put("education",optional.get().getEducation());
            map.put("extra",optional.get().getExtra());
            return map;
        }else{
            map.put("flag","0");
            return map;
        }
    }
    @ResponseBody
    @RequestMapping(value = "/setRecruit",method = RequestMethod.POST,consumes = "application/json")
    public Map<String,String> setRecruit(@RequestBody JSONObject object){
        String data = object.toJSONString();
        JSONObject jsonObject = JSON.parseObject(data);
        String id = jsonObject.getString("id");
        String name=  jsonObject.getString("name");
        String address = jsonObject.getString("address");
        String post = jsonObject.getString("post");
        String salary = jsonObject.getString("salary");
        AllRecruit allRecruit = new AllRecruit(id,name,post,salary,address);
        allRecruitRepository.saveAndFlush(allRecruit);
        RecruitInfo recruitInfo = JSON.parseObject(data,RecruitInfo.class);
        recruitInfoRepository.saveAndFlush(recruitInfo);
        Map map = new HashMap();
        map.put("flag","1");
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteRecruit",method = RequestMethod.POST)
    public Map<String,String> deleteRecruit(HttpSession session){
        Enterprise enterprise = (Enterprise)session.getAttribute("enterprise");
        String id = enterprise.getId();
        recruitInfoRepository.deleteById(id);
        allRecruitRepository.deleteById(id);
        Map map = new HashMap();
        map.put("flag","1");
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/getApplyList",method = RequestMethod.POST)
    public String getApplyList(HttpSession session){
        Enterprise enterprise = (Enterprise)session.getAttribute("enterprise");
        String e_name = enterprise.getName();
        List<AllApply> list = allApplyRepository.findByEnterpriseName(e_name);
        String json = JSON.toJSON(list).toString();
        return json;
    }
    @ResponseBody
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    public Map<String,String> apply(@RequestBody JSONObject jsonObject){
        String s_id = jsonObject.getString("s_id");
        allApplyRepository.updateApply(s_id);
        Map map = new HashMap();
        map.put("flag","1");
        return map;
    }
}
