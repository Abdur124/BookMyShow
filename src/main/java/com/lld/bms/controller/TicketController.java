package com.lld.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lld.bms.dto.TicketReqDTO;
import com.lld.bms.models.Ticket;
import com.lld.bms.service.TicketService;

@RestController
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@PostMapping("/ticket")
	public ResponseEntity<Ticket> createTicket(@RequestBody TicketReqDTO ticketReqDTO) {
		
		Ticket savedTicket = ticketService.saveTicket(ticketReqDTO);
		return ResponseEntity.ok(savedTicket);
	}
	
	@GetMapping("/tickets")
	public ResponseEntity<List<Ticket>> getAllTickets() {
		List<Ticket> tickets = ticketService.getTickets();
		return ResponseEntity.ok(tickets);
	}
	
	@GetMapping("ticket/{id}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable("id") int ticketId) {
		Ticket Ticket = ticketService.getTicketById(ticketId);
		return ResponseEntity.ok(Ticket);
	}
	
	@DeleteMapping("ticket/{id}")
	public ResponseEntity<String> deleteTicketById(@PathVariable("id") int ticketId) {
		ticketService.deleteTicketById(ticketId);
		return ResponseEntity.ok("Ticket Deleted");
	}

}
