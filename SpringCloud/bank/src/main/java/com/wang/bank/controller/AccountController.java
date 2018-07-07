package com.wang.bank.controller;

import com.google.gson.Gson;
import com.wang.bank.entities.Account;
import com.wang.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @GetMapping("/account")
    public String getTestAccount(HttpSession session){
        Account account = new Account();
        account = accountRepository.getOne("wbl");
        session.setAttribute("account",account);
        return "account";
    }
    @ResponseBody
    @GetMapping("/getAccount")
    public String getAccount(String accountname){
        Gson gson = new Gson();
        Optional<Account> optional = accountRepository.findById(accountname);
        if(optional.isPresent()){
            Account account = optional.get();
            String json = gson.toJson(account);
            return json;
        }else{
            return "用户密码有误";
        }
    }
    @ResponseBody
    @GetMapping("/update")
    public String update(@Param("accountname") String accountname,@Param("password")String password,@Param("price") String price){
        Optional<Account> optional = accountRepository.findById(accountname);
        if(optional.isPresent()){
            Account account = new Account(accountname,password,optional.get().getBalance()-Integer.parseInt(price));
            accountRepository.save(account);
            return "支付成功";
        }else{
            return "账号或密码错误，支付失败";
        }

    }
}
