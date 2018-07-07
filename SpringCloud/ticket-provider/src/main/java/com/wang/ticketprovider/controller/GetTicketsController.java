package com.wang.ticketprovider.controller;

import com.google.gson.Gson;
import com.wang.ticketprovider.dao.GetTickets;
import com.wang.ticketprovider.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GetTicketsController {
    @Autowired
    public GetTickets getTickets;
    @GetMapping("/ticket")
    public String getTicketsController(HttpSession session){
        List<Ticket> list = getTickets.getAllTickets();
        session.setAttribute("list",list);
        return "ticket";
    }
    @ResponseBody
    @GetMapping("/alltickets")
    public String getAllTickets(){
        Gson gson = new Gson();
        String json = gson.toJson(getTickets.getAllTickets());
        return json;
    }
}
