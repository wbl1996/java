package com.wang.ticketprovider.dao;

import com.wang.ticketprovider.entities.Ticket;
import com.wang.ticketprovider.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class GetTickets {
    @Autowired
    TicketRepository ticketRepository;
    public List<Ticket> getAllTickets(){
        List<Ticket> list = ticketRepository.findAll();
        return list;
    }
}
