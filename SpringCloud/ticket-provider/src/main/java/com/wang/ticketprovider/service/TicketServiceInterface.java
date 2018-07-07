package com.wang.ticketprovider.service;

import com.wang.ticketprovider.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketServiceInterface {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketService ticketService;

}
