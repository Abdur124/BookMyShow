package com.lld.bms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.lld.bms.dto.TicketReqDTO;
import com.lld.bms.exceptions.ShowSeatsNotAvailableException;
import com.lld.bms.exceptions.TicketNotFoundException;
import com.lld.bms.models.ShowSeat;
import com.lld.bms.models.ShowSeatstatus;
import com.lld.bms.models.Ticket;
import com.lld.bms.models.TicketStatus;
import com.lld.bms.models.User;
import com.lld.bms.repository.TicketRepository;

public class TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShowSeatService showSeatService;
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public Ticket saveTicket(TicketReqDTO ticketReqDTO) {
		
		List<ShowSeat> showSeats = new ArrayList<>();
		int userId = ticketReqDTO.getUserId();
		List<Integer> showSeatIDs = ticketReqDTO.getShowSeatIDs();
		
		for(Integer showSeatID: showSeatIDs) {
			showSeats.add(showSeatService.getShowSeatById(showSeatID));
		}
		
		for(ShowSeat showSeat: showSeats) {
			if(!showSeat.getStatus().equals(ShowSeatstatus.AVAILABLE)) {
				throw new ShowSeatsNotAvailableException("Selected Seats Not Available");
			}
		}
		
		for(ShowSeat showSeat: showSeats) {
			showSeat.setStatus(ShowSeatstatus.LOCKED);
			showSeatService.updateShowSeat(showSeat);
		}
		
		return generateTicket(userId, showSeats);
	}
	
	private Ticket generateTicket(int userId, List<ShowSeat> showSeats) {
		// Payment Processing block
		
		User user = userService.getUserById(userId);
		for(ShowSeat showSeat: showSeats) {
			showSeat.setStatus(ShowSeatstatus.BOOKED);
			showSeatService.updateShowSeat(showSeat);
		}
		
		Ticket ticket = new Ticket();
		ticket.setShowSeats(showSeats);
		ticket.setTicketStatus(TicketStatus.BOOKED);
		ticket.setUser(user);
		ticket.setShow(showSeats.get(0).getShow());
		ticket = ticketRepository.save(ticket);
		return ticket;
	}

	public List<Ticket> getTickets() {
		return ticketRepository.findAll();
	}
	
	public Ticket getTicketById(int TicketId) {
		
		return ticketRepository.findById(TicketId).orElseThrow(() -> new TicketNotFoundException("Ticket with id " +TicketId+ " not found"));
	}
	
	public void deleteTicketById(int TicketId) {
		ticketRepository.deleteById(TicketId);
	}

}
