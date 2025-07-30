package com.lld.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.bms.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
