package com.lld.bms.service;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lld.bms.dto.TicketResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
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

@Service
public class TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShowSeatService showSeatService;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private ObjectMapper mapper;
	
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
		
		Ticket ticket = generateTicket(userId, showSeats);
		sendTicketAsEmail(ticket);
		return ticket;
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

	public void sendTicketAsEmail(Ticket ticket) {

		String ticketResDTOString;
		mapper = new ObjectMapper();
		mapper.registerModule(
				new JavaTimeModule()
		);

		TicketResDTO ticketResDTO = new TicketResDTO();
		ticketResDTO.setUsername(ticket.getUser().getName());
		ticketResDTO.setEmail(ticket.getUser().getEmail());
		ticketResDTO.setTotalTickets(ticket.getShowSeats().size());
		ticketResDTO.setTicketStatus(ticket.getTicketStatus().toString());
		ticketResDTO.setMovieName(ticket.getShow().getMovie().getName());
		ticketResDTO.setTheatreName(ticket.getShow().getAuditorium().getTheatre().getName());
		ticketResDTO.setAudiName(ticket.getShow().getAuditorium().getName());
		ticketResDTO.setShowTime(ticket.getShow().getStartTime());

		try {
			ticketResDTOString = mapper.writeValueAsString(ticketResDTO);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		kafkaTemplate.send("movie_ticket", ticketResDTOString);

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
