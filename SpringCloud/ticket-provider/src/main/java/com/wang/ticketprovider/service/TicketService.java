package com.wang.ticketprovider.service;

import com.wang.ticketprovider.entities.Ticket;
import com.wang.ticketprovider.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }
}
