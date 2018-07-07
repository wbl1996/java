package com.wang.ticketprovider.repository;

import com.wang.ticketprovider.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {

}
